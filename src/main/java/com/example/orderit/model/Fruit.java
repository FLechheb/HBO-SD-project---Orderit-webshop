package com.example.orderit.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fruit {

    @JsonProperty("naam")
    private String name;

    @JsonProperty("price")
    private double price;


    @JsonProperty("unit")
    private String unit;

    @JsonProperty("categorie")
    private String category;

    @JsonProperty("herkomst")
    private String herkomst;
    private String imagePath;


    public Fruit() {
    }

    public Fruit(String name, double price, String unit, String category, String herkomst) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.category = category;
        this.herkomst = herkomst;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
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