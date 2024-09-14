package com.example.strategypattern;

import com.google.gson.Gson;

import java.io.*;
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
                            || item.q == null || item.id == null){
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
                //TODO
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getRecommendations(List<CrucialSearchElements> movies){
        //TODO
    }

    public static void addWatched(int grade, CrucialSearchElements crucialSearchElement){
        String newLine = crucialSearchElement.title + ";" + crucialSearchElement.imageUrl + ";" +
                Integer.toString(grade) + ";" + crucialSearchElement.id;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("watched.txt", true))){
            writer.write(newLine);
            writer.newLine();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void getWatched(){
        try(BufferedReader reader = new BufferedReader(new FileReader("watched.txt"))){
            List<CrucialSearchElements> result = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null){
                int first_semicolon = line.indexOf(';');
                String tittle = line.substring(0, first_semicolon);

                String imageUrl = "";
                for(int i = first_semicolon + 1; i < line.length(); ++i){
                    if(line.charAt(i) == ';'){
                        imageUrl = line.substring(first_semicolon + 1, i);
                        break;
                    }
                }

                int last_semicolon = line.lastIndexOf(';');
                String id = line.substring(last_semicolon+1);

                int grade = Character.getNumericValue(line.charAt(last_semicolon-1));

                D item = new D();
                item.id = id;
                item.l = tittle;
                item.rank = grade;
                item.i = new I();
                item.i.imageUrl = imageUrl;

                result.add(new CrucialSearchElements(item));
            }

            MovieViewFX.watchedScene(result);

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void removeMovie(String id, String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            StringBuffer inputBuffer = new StringBuffer();
            while((line = reader.readLine()) != null){
                if(line.contains(id)){
                    //Empty Line
                    inputBuffer.append('\n');
                    break;
                }else{
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
            }
            reader.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("notes.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void addFavorite(CrucialSearchElements crucialSearchElement) {
        String newLine = crucialSearchElement.title + ";" + crucialSearchElement.imageUrl +
                                 ";" + crucialSearchElement.id;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("favorites.txt", true))){
            writer.write(newLine);
            writer.newLine();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void getFavorites(){
        try(BufferedReader reader = new BufferedReader(new FileReader("favorites.txt"))){
            List<CrucialSearchElements> result = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null){
                int first_semicolon = line.indexOf(';');
                String tittle = line.substring(0, first_semicolon);

                String imageUrl = "";
                for(int i = first_semicolon + 1; i < line.length(); ++i){
                    if(line.charAt(i) == ';'){
                        imageUrl = line.substring(first_semicolon + 1, i);
                        break;
                    }
                }

                int last_semicolon = line.lastIndexOf(';');
                String id = line.substring(last_semicolon+1);

                D item = new D();
                item.id = id;
                item.l = tittle;
                item.i = new I();
                item.i.imageUrl = imageUrl;

                result.add(new CrucialSearchElements(item));
            }

            MovieViewFX.favoriteScene(result);

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
