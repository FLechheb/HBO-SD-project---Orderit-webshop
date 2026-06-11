package com.example.orderit.model;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Producent {
    public Producent() {
    }
    public Producent(
            String boer,
            String boerderij,
            String provincie_of_streek,
            String boer_bericht) {

        this.boer = boer;
        this.boerderij = boerderij;
        this.provincie_of_streek = provincie_of_streek;
        this.boer_bericht = boer_bericht;
    }

    @JsonProperty("boer")
    private String boer;

    @JsonProperty("boerderij")
    private String boerderij;

    @JsonProperty("provincie_of_streek")
    private String provincie_of_streek;

    @JsonProperty("boer_bericht")
    private String boer_bericht;



    public String getBoer() {
        return boer;
    }

    public String getBoerderij() {
        return boerderij;
    }

    public String getProvincieOfStreek() {
        return provincie_of_streek;
    }

    public String getBoerBericht() {
        return boer_bericht;
    }
}
