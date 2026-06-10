package com.example.orderit.model;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Producent {

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
