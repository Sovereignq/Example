package com.example.uml.info;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.ArrayList;

public class AnimalInfoClass {
    private final String name;
    private int age;
    private final boolean sex;
    private ArrayList<Bitmap> imagesOfAnimal = new ArrayList<>();
    private String description;
    private final String kind;
    private final String breed;
    ArrayList<String> necessities = new ArrayList<>();
    public AnimalInfoClass(String kind, String breed, boolean sex, String name, int age, String description, ArrayList<Bitmap> imagesOfAnimal) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.sex = sex;
        this.kind = kind;
        this.breed = breed;
        this.imagesOfAnimal = imagesOfAnimal;
    }

    public ArrayList<Bitmap> getImagesOfAnimal() {
        return imagesOfAnimal;
    }

    public String getDescription() {
        return description;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public boolean isSex() {
        return sex;
    }

    public String getKind() {
        return kind;
    }

    public String getBreed() {
        return breed;
    }
}
