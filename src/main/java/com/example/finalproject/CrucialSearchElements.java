package com.example.finalproject;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.example.finalproject.gsondatastructures.D;

public class CrucialSearchElements {
    public String id;
    public String imageUrl;
    public Image image;
    public String title;
    public Integer year;
    public Integer rank; // Grade
    public String actors;
    public String style;

    public CrucialSearchElements(D item) {
        this.id = item.id;
        this.imageUrl = item.i.imageUrl;
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