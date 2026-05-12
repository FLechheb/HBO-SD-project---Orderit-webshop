package com.example.orderit;

import com.example.orderit.model.Fruit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class MainViewController {

    @FXML
    private GridPane fruitGrid;

    @FXML
    private ListView<String> cartListView;

    private double total = 0;

    private List<Fruit> fruitList = new ArrayList<>();

    @FXML
    private TextField searchField;


    @FXML
    public void initialize() {
        //Fruitsoorten aanmaken en toevoegen aan arrayList

        fruitList.add(new Fruit("Banaan", 1.20));
        fruitList.add(new Fruit("Appel", 1.50));
        fruitList.add(new Fruit("Perzik", 2.00));
        fruitList.add(new Fruit("Mango", 2.80));
        fruitList.add(new Fruit("Aardbei", 3.00));
        fruitList.add(new Fruit("Kiwi", 1.70));
        fruitList.add(new Fruit("Ananas", 3.50));
        fruitList.add(new Fruit("Watermeloen", 4.20));
        fruitList.add(new Fruit("Druiven", 2.40));
        fruitList.add(new Fruit("Sinaasappel", 1.90));
        fruitList.add(new Fruit("Citroen", 1.10));
        fruitList.add(new Fruit("Limoen", 1.30));
        fruitList.add(new Fruit("Kers", 3.80));
        fruitList.add(new Fruit("Framboos", 4.00));
        fruitList.add(new Fruit("Blauwe bes", 4.50));
        fruitList.add(new Fruit("Peer", 1.60));
        fruitList.add(new Fruit("Pruim", 2.10));
        fruitList.add(new Fruit("Papaja", 3.90));
        fruitList.add(new Fruit("Kokosnoot", 5.00));
        fruitList.add(new Fruit("Passievrucht", 2.70));

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

    //info van de fruit realiseren
    private VBox createFruitInfoBox(Fruit fruit) {
        Label name = new Label(fruit.getName());
        name.getStyleClass().add("fruit-name");
        Label price = new Label("€" + fruit.getPrice());

        Button addButton = new Button("Voeg toe");
        name.getStyleClass().add("add-button");


        addButton.setOnAction(e -> {
            cartListView.getItems().add(fruit.getName() + " - €" + fruit.getPrice());
            total += fruit.getPrice();
            System.out.println("Totaal: €" + total); // later label maken
        });

        return new VBox(6, name, price, addButton);
    }


    @FXML
    private void filterFruit() {
        String searchText = searchField.getText().toLowerCase();


    }

}

