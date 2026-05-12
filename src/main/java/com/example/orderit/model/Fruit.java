package com.example.orderit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fruit {
    @JsonProperty("naam")
    private String name;

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
