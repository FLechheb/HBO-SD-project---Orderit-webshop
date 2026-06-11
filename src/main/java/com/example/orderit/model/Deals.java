package com.example.orderit.model;
import java.util.List;

public class Deals extends Fruit {


    private List<Fruit> fruits;

    public Deals(String name, List<Fruit> fruits) {
        this.name = name;
        this.fruits = fruits;
    }

    @Override
    public double getPrice() {

        double totaal = 0;

        for (Fruit fruit : fruits) {
            totaal += fruit.getPrice();
        }

        return totaal * 0.8; // 20% korting
    }
    @Override
    public String getDisplayTitle() {
        return name;
    }

    @Override
    public String getDisplaySubtitle() {
        return "20% korting";
    }

    @Override
    public String getCategory() {
        return "Aanbiedingen";
    }

    @Override
    public String getHerkomst() {
        return "Meerdere landen";
    }

    @Override
    public String getUnit() {
        return "Per pakket";
    }

    @Override
    public String getVerhaal() {
        return getDisplayDescription();
    }

    @Override
    public Producent getProducent() {
        return new Producent(
                "OrderIt",
                "Fruitpakket",
                "",
                "Speciaal samengesteld pakket met 20% korting."
        );
    }

    @Override
    public String getDisplayDescription() {

        StringBuilder tekst = new StringBuilder();

        for (Fruit fruit : fruits) {
            tekst.append(fruit.getName()).append("\n");
        }

        return tekst.toString();
    }
    @Override
    public String getVerhaalLabel() {
        return "Inhoud van het pakket:";
    }

    @Override
    public String getBerichtLabel() {
        return "Voordeel van deze aanbieding:";
    }

    @Override
    public String getBoerLabel() {
        return "Aanbieding:";
    }

    @Override
    public String getBoerderijLabel() {
        return "Pakkettype:";
    }
}