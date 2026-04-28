package com.example.orderit;

import com.example.orderit.model.Fruit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class MainViewController {

    @FXML
    private GridPane fruitGrid;

    private List<Fruit> fruitList = new ArrayList<>();


    @FXML
    public void initialize() {
        //Fruitsoorten aanmaken
        Fruit fruit1 = new Fruit("Banaan");
        Fruit fruit2 = new Fruit("Appel");
        Fruit fruit3 = new Fruit("Sinaasappel");
        Fruit fruit4 = new Fruit("Perzik");
        Fruit fruit5 = new Fruit("Mango");
        Fruit fruit6 = new Fruit("Ananas");
        Fruit fruit7 = new Fruit("Aardbei");
        Fruit fruit8 = new Fruit("Kiwi");

        //Fruitsoorten toevoegen aan arrayList
        fruitList.add(fruit1);
        fruitList.add(fruit2);
        fruitList.add(fruit3);
        fruitList.add(fruit4);
        fruitList.add(fruit5);
        fruitList.add(fruit6);
        fruitList.add(fruit7);
        fruitList.add(fruit8);

        //Blok aanmaken met fruitsoort
        createFruitCards(fruitList);
    }

    private void createFruitCards(List<Fruit> fruitlijst) {
        int col = 0, row = 1;

        //Voor elke fruitsoort uit de array een fruitCard maken en toevoegen aan de Grid
        //Max 3 items per rij
        for (Fruit fruit : fruitlijst) {
            VBox card = createFruitCard(fruit);
            fruitGrid.add(card, col, row);
            col++;
            if (col > 2) { col = 0; row++; }
        }
    }

    //Fruitcard maken met de style card
    private VBox createFruitCard(Fruit fruit) {
        VBox card = new VBox(8);
        card.getStyleClass().add("card");
        card.prefWidthProperty().bind(fruitGrid.widthProperty().divide(3).subtract(20));

        //Naam toevoegen (in een VBox zodat andere info toevoegen later makkelijker is)
        VBox infoBox = createFruitInfoBox(fruit);
        card.getChildren().addAll(infoBox.getChildren());

        return card;
    }

    // De naam van de item in een VBox stoppen
    private VBox createFruitInfoBox(Fruit fruit) {
        Label name = new Label(fruit.getName());
        name.getStyleClass().add("fruit-name");

        return new VBox(4, name);
    }
}

