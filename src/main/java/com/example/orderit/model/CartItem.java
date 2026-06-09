package com.example.orderit.model;

public class CartItem {

    private Fruit fruit;
    private int aantal;

    public CartItem(Fruit fruit, int aantal) {
        this.fruit = fruit;
        this.aantal = aantal;
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
