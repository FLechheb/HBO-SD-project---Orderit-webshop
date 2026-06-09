package com.example.orderit.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fruit extends Product {


    @JsonProperty("unit")
    private String unit;

    @JsonProperty("categorie")
    private String category;

    @JsonProperty("herkomst")
    private String herkomst;
    private String imagePath;


    public Fruit() {
    }

    public Fruit(String name, double price, String unit, String herkomst) {
        super(name, price);
        this.unit = unit;
        this.herkomst = herkomst;
    }


    public String getUnit() {
        return unit;
    }


        public String getCategory() {
            return category;
    }

    public String getHerkomst() {
        return herkomst;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}