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

    @JsonProperty("verhaal")
    private String verhaal;

    @JsonProperty("producent")
    private Producent producent;


    public Fruit() {
    }

    public Fruit(String unit, String category, String herkomst) {
        this.unit = unit;
        this.category = category;
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

    public String getVerhaal() {
        return verhaal;
    }

    public Producent getProducent() {
        return producent;
    }

    @Override
    public String getDisplayTitle() {
        return getName();
    }

    @Override
    public String getDisplaySubtitle() {
        return "Uit " + herkomst;
    }

    public String getVerhaalLabel() {
        return "Verhaal:";
    }

    public String getBerichtLabel() {
        return "Bericht van de boer:";
    }

    public String getBoerLabel() {
        return "Boer:";
    }

    public String getBoerderijLabel() {
        return "Boerderij:";
    }
}