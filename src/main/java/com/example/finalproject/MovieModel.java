package com.example.finalproject;

import com.example.finalproject.gsondatastructures.D;
import com.example.finalproject.gsondatastructures.I;
import com.example.finalproject.gsondatastructures.RespRec;
import com.example.finalproject.gsondatastructures.Root;
import com.google.gson.Gson;

import javafx.scene.control.Alert.AlertType;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieModel {

    private static List<CrucialSearchElements> watchedMovies = null;
    private static List<CrucialSearchElements> favoriteMovies = null;
    private static List<CrucialSearchElements> interestsMovies = null;
    private static List<CrucialSearchElements> lastSearchMovies = null;
    private static List<CrucialSearchElements> lastRecommendationsMovies = null;

    public static List<CrucialSearchElements> getWatchedMovies () {

        return watchedMovies;

    }

    public static List<CrucialSearchElements> getFavoritesMovies () {

        return favoriteMovies;

    }

    public static List<CrucialSearchElements> getInterestsMovies () {

        return interestsMovies;

    }

    public static List<CrucialSearchElements> getLastSearchMovies () {

        return lastSearchMovies;

    }

    public static List<CrucialSearchElements> getLastRecommendationsMovies () {

        return lastRecommendationsMovies;

    }

    public static void downloadMovies () {

        watchedMovies = downloadWatchedMovies();
        favoriteMovies = downloadFavoriteMovies();
        interestsMovies = downloadInterestsMovies();

    }

    public static List<CrucialSearchElements> autoComplete(String userInput) {
        String tmp = "https://online-movie-database.p.rapidapi.com/auto-complete?q=";
        String uri = tmp.concat(userInput);

        System.out.println(uri);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("x-rapidapi-key", "974c81fbd5mshbcab637a2a9ac35p143d1djsne324609cc13d")
                .header("x-rapidapi-host", "online-movie-database.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String response_body = response.body();

                // JSON
                Gson gson = new Gson();
                Root root = gson.fromJson(response_body, Root.class);

                List<CrucialSearchElements> result = new ArrayList<>();
                for (int i = 0; i < root.d.size(); ++i) {
                    D item = root.d.get(i);
                    if (item.i == null || item.l == null || item.y == null
                            || item.rank == null || item.s == null
                            || item.q == null || item.id == null) {
                        continue;
                    }
                    result.add(new CrucialSearchElements(item));
                }

                for (CrucialSearchElements item : result) {
                    System.out.println(item);
                }

                // MovieViewFX.searchedScene(result);
                lastSearchMovies = result;
                return result;
            } else {
                System.out.println("xiiii");
                return null;
            }
        } catch (FileNotFoundException e) {

            MovieViewFX.showAlert("Você não possui filmes marcados como assistido!", AlertType.INFORMATION);
            return null;

        } catch (Exception e) {

            MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
            e.printStackTrace();
            return null;

        }
    }

    public static List<CrucialSearchElements> getRecommendations() {
        try (BufferedReader reader = new BufferedReader(new FileReader("favorites.txt"))) {
            List<CrucialSearchElements> result = new ArrayList<>();

            int counter_lines = 0;
            String line;
            while ((line = reader.readLine()) != null && counter_lines != 5) {
                int first_semicolon = line.indexOf(';');
                String tittle = line.substring(0, first_semicolon);

                String imageUrl = "";

                for (int i = first_semicolon + 1; i < line.length(); ++i) {
                    if (line.charAt(i) == ';') {
                        imageUrl = line.substring(first_semicolon + 1, i);
                        break;
                    }
                }

                int last_semicolon = line.lastIndexOf(';');
                String id = line.substring(last_semicolon + 1);

                D item = new D();
                item.id = id;
                item.l = tittle;
                item.i = new I();
                item.i.imageUrl = imageUrl;

                result.add(new CrucialSearchElements(item));
                counter_lines++;
            }

            List<CrucialSearchElements> res = new ArrayList<>();

            for (CrucialSearchElements crucialSearchElements : result) {
                String id = crucialSearchElements.id;

                String uri = "https://online-movie-database.p.rapidapi.com/title/v2/get-related-interests?tconst=";
                uri = uri.concat(id);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(uri))
                        .header("x-rapidapi-key", "974c81fbd5mshbcab637a2a9ac35p143d1djsne324609cc13d")
                        .header("x-rapidapi-host", "online-movie-database.p.rapidapi.com")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();

                try (HttpClient client = HttpClient.newHttpClient()) {
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    if (response.statusCode() == 200) {
                        String response_body = response.body();

                        // JSON
                        Gson gson = new Gson();
                        RespRec respRec = gson.fromJson(response_body, RespRec.class);

                        Random random = new Random();
                        int index = random.nextInt(3);
                        String title = respRec.data.title.relatedInterests.edges.get(index).node.primaryImage.titles
                                .getFirst().titleText.text;
                        String imageUrl = respRec.data.title.relatedInterests.edges.get(index).node.primaryImage.titles
                                .getFirst().primaryImage.url;

                        D item = new D();
                        item.l = title;
                        item.i = new I();
                        item.i.imageUrl = imageUrl;

                        res.add(new CrucialSearchElements(item));

                        /*
                         * System.out.println("Title: " +
                         * respRec.data.title.relatedInterests.edges.getFirst().node.primaryImage.titles
                         * .getFirst().titleText.text);
                         * System.out.println("URL: " +
                         * respRec.data.title.relatedInterests.edges.getFirst().node.primaryImage.titles
                         * .getFirst().primaryImage.url);
                         */

                    } else {
                        System.out.println("xiiii");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // MovieViewFX.recommendationScene(res);
            for (CrucialSearchElements item : res) {
                System.out.println("Recomendado: " + item);
            }
            lastRecommendationsMovies = res;
            return res;
        } catch (FileNotFoundException e) {

            MovieViewFX.showAlert("Você não possui nenhum filme marcado como favorito, logo não é possível recomendar!", AlertType.INFORMATION);
            return null;

        } catch (Exception e) {

            MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
            e.printStackTrace();
            return null;

        }

    }

    public static void addWatched(int grade, CrucialSearchElements crucialSearchElement) {
        String newLine = crucialSearchElement.title + ";" + crucialSearchElement.imageUrl + ";" +
                Integer.toString(grade) + ";" + crucialSearchElement.id;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("watched.txt", true))) {
            writer.write(newLine);
            writer.newLine();
            watchedMovies.add(crucialSearchElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<CrucialSearchElements> downloadWatchedMovies() {
        try (BufferedReader reader = new BufferedReader(new FileReader("watched.txt"))) {
            List<CrucialSearchElements> result = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                int first_semicolon = line.indexOf(';');
                String tittle = line.substring(0, first_semicolon);

                String imageUrl = "";
                for (int i = first_semicolon + 1; i < line.length(); ++i) {
                    if (line.charAt(i) == ';') {
                        imageUrl = line.substring(first_semicolon + 1, i);
                        break;
                    }
                }

                int last_semicolon = line.lastIndexOf(';');
                String id = line.substring(last_semicolon + 1);

                int grade = 0;
                if (line.charAt(last_semicolon - 2) == ';') {
                    grade = Character.getNumericValue(line.charAt(last_semicolon - 1));
                } else {
                    String g = "" + line.charAt(last_semicolon - 2) + line.charAt(last_semicolon - 1);
                    grade = Integer.parseInt(g);
                }

                D item = new D();
                item.id = id;
                item.l = tittle;
                item.rank = grade;
                item.i = new I();
                item.i.imageUrl = imageUrl;

                result.add(new CrucialSearchElements(item));
                // watchedMovies.add(new CrucialSearchElements(item));
            }

            // MovieViewFX.watchedScene(result);
            return result;

        } catch (FileNotFoundException e) {

            MovieViewFX.showAlert("Você não possui filmes marcados como assistido!", AlertType.INFORMATION);
            return null;

        } catch (Exception e) {

            MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
            e.printStackTrace();
            return null;

        }
    }

    public static void removeMovie(String id, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuffer inputBuffer = new StringBuffer();
            boolean movieFound = false;
            
            while ((line = reader.readLine()) != null) {
                // Se a linha contém o ID do filme, não a adicionamos no buffer
                if (line.contains(id)) {
                    movieFound = true;
                    // Pulamos essa linha, então não a adicionamos no inputBuffer
                    continue;
                } 
                // Adiciona todas as outras linhas (não correspondentes ao ID) no buffer
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }

            reader.close();

            // Reescreve o conteúdo do arquivo apenas se o filme foi encontrado
            if (movieFound) {
                FileOutputStream fileOut = new FileOutputStream(fileName);
                fileOut.write(inputBuffer.toString().getBytes());
                fileOut.close();
            }

        } catch (FileNotFoundException ignore) {

            System.out.println("Arquivo " + fileName + " não existe!");

        } catch (Exception e) {

            MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
            e.printStackTrace();

        }
    }

    public static void addFavorite(CrucialSearchElements crucialSearchElement) {
        String newLine = crucialSearchElement.title + ";" + crucialSearchElement.imageUrl +
                ";" + crucialSearchElement.id;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("favorites.txt", true))) {
            writer.write(newLine);
            writer.newLine();
            favoriteMovies.add(crucialSearchElement);
        } catch (Exception e) {

            MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
            e.printStackTrace();

        }
    }

    public static List<CrucialSearchElements> downloadFavoriteMovies() {
        try (BufferedReader reader = new BufferedReader(new FileReader("favorites.txt"))) {
            List<CrucialSearchElements> result = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                int first_semicolon = line.indexOf(';');
                String tittle = line.substring(0, first_semicolon);

                String imageUrl = "";
                for (int i = first_semicolon + 1; i < line.length(); ++i) {
                    if (line.charAt(i) == ';') {
                        imageUrl = line.substring(first_semicolon + 1, i);
                        break;
                    }
                }

                int last_semicolon = line.lastIndexOf(';');
                String id = line.substring(last_semicolon + 1);

                D item = new D();
                item.id = id;
                item.l = tittle;
                item.i = new I();
                item.i.imageUrl = imageUrl;

                result.add(new CrucialSearchElements(item));
            }

            // MovieViewFX.favoriteScene(result);
            return result;

        } catch (FileNotFoundException e) {

            MovieViewFX.showAlert("Você não possui filmes marcados como favorito!", AlertType.INFORMATION);
            return null;

        } catch (Exception e) {

            MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
            e.printStackTrace();
            return null;

        }
    }

    public static void addInterest(CrucialSearchElements crucialSearchElement) {
        String newLine = crucialSearchElement.title + ";" + crucialSearchElement.imageUrl +
                ";" + crucialSearchElement.id;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("interests.txt", true))) {
            writer.write(newLine);
            writer.newLine();
            interestsMovies.add(crucialSearchElement);
        } catch (Exception e) {

            MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
            e.printStackTrace();

        }
    }

    public static List<CrucialSearchElements> downloadInterestsMovies() {
        try (BufferedReader reader = new BufferedReader(new FileReader("interests.txt"))) {
            List<CrucialSearchElements> result = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                int first_semicolon = line.indexOf(';');
                String tittle = line.substring(0, first_semicolon);

                String imageUrl = "";
                for (int i = first_semicolon + 1; i < line.length(); ++i) {
                    if (line.charAt(i) == ';') {
                        imageUrl = line.substring(first_semicolon + 1, i);
                        break;
                    }
                }

                int last_semicolon = line.lastIndexOf(';');
                String id = line.substring(last_semicolon + 1);

                D item = new D();
                item.id = id;
                item.l = tittle;
                item.i = new I();
                item.i.imageUrl = imageUrl;

                result.add(new CrucialSearchElements(item));
            }

            return result;

        } catch (FileNotFoundException e) {

            MovieViewFX.showAlert("Você não possui filmes na sua lista de interesses!", AlertType.INFORMATION);
            return null;

        } catch (Exception e) {

            MovieViewFX.showAlert("Um erro inesperado ocorreu!", AlertType.ERROR);
            e.printStackTrace();
            return null;

        }
    }

}
