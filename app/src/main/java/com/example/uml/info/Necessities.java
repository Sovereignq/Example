package com.example.uml.info;

import android.graphics.Bitmap;

public class Necessities {
    public  String name;
    public Bitmap image;

    public Necessities(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
