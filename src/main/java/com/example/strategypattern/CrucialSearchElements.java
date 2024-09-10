package com.example.strategypattern;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CrucialSearchElements {
    Image image;
    String title;
    Integer year;
    Integer rank;
    String actors;
    String style;

    public CrucialSearchElements(D item) {
        this.title = item.l;
        this.year = item.y;
        this.rank = item.rank;
        this.actors = item.s;
        this.style = item.q;

        try {
            URL url = new URL(item.i.imageUrl);
            InputStream inputStream = url.openStream();

            this.image = new Image(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}