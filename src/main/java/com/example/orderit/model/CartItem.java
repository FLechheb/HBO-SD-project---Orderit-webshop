package com.example.orderit.model;

public class CartItem {

    private Fruit fruit;
    private int aantal;
    private String unit;

    public CartItem(Fruit fruit, int aantal) {
        this.fruit = fruit;
        this.aantal = aantal;
        this.unit = unit;
    }

    public String getProduct() {
        return fruit.getName();
    }

    public int getAantal() {
        return aantal;
    }

    public double getPrijs() {
        return aantal * fruit.getPrice();
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
}
