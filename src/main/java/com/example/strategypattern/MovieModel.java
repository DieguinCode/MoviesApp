package com.example.strategypattern;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class MovieModel{

    public static void autoComplete(String userInput){
        String tmp = "https://online-movie-database.p.rapidapi.com/auto-complete?q=";
        String uri = tmp.concat(userInput);

        System.out.println(uri);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("x-rapidapi-key", "974c81fbd5mshbcab637a2a9ac35p143d1djsne324609cc13d")
                .header("x-rapidapi-host", "online-movie-database.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try(HttpClient client = HttpClient.newHttpClient()){
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                String response_body = response.body();

                //JSON
                Gson gson = new Gson();
                Root root = gson.fromJson(response_body, Root.class);

                List<CrucialSearchElements> crucialElementsList = new ArrayList<>();
                for(int i = 0; i < root.d.size(); ++i){
                    D item = root.d.get(i);
                    if(item.i == null || item.l == null || item.y == null || item.rank == null || item.s == null
                            || item.q == null){
                        continue;
                    }
                    crucialElementsList.add(new CrucialSearchElements(item));
                }

                for(CrucialSearchElements item: crucialElementsList){
                    System.out.println(item);
                }

                //Call View
               try{
                   MovieViewFX.searchedScene(crucialElementsList);
               }catch (IOException e){
                   throw new RuntimeException(e);
               }
            }else{
                System.out.println("XABUUUU");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
