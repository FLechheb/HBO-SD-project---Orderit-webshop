package com.example.orderit;

import com.example.orderit.model.Fruit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.control.ChoiceBox;
import java.util.ArrayList;
import java.util.List;

public class MainViewController {

    @FXML
    private GridPane fruitGrid;


    private List<Fruit> fruitList = new ArrayList<>();

    @FXML
    private ListView<String> winkelmandList;

    private double totaalPrijs = 0;

    @FXML
    private Label totaalLabel;

    private void addOne(Fruit fruit) {
        String naam = fruit.getName();

        for (int i = 0; i < winkelmandList.getItems().size(); i++) {
            String item = winkelmandList.getItems().get(i);

            if (item.startsWith(naam)) {
                int aantal = Integer.parseInt(item.split("x")[1].split(" - ")[0]);
                double prijs = (aantal + 1) * fruit.getPrice();

                winkelmandList.getItems().set(
                        i,
                        naam + " x" + (aantal + 1) + " - €" + String.format("%.2f", prijs)
                );
                totaalPrijs += fruit.getPrice();
                totaalLabel.setText("Totaal: €" + String.format("%.2f", totaalPrijs));
                return;
            }
        }

        winkelmandList.getItems().add(
                naam + " x1 - €" + String.format("%.2f", fruit.getPrice())
        );

        totaalPrijs += fruit.getPrice();
        totaalLabel.setText("Totaal: €" + String.format("%.2f", totaalPrijs));
    }

    private void removeOne(String item) {
        for (int i = 0; i < winkelmandList.getItems().size(); i++) {

            String current = winkelmandList.getItems().get(i);

            if (current.equals(item)) {

                String naam = item.split(" x")[0];
                int aantal = Integer.parseInt(item.split("x")[1].split(" - ")[0]);

                Fruit gekozenFruit = null;

                for (Fruit fruit : fruitList) {
                    if (fruit.getName().equals(naam)) {
                        gekozenFruit = fruit;
                        break;
                    }
                }

                if (gekozenFruit != null) {
                    totaalPrijs -= gekozenFruit.getPrice();
                }

                if (aantal <= 1) {
                    winkelmandList.getItems().remove(i);
                } else {
                    double prijs = (aantal - 1) * gekozenFruit.getPrice();

                    winkelmandList.getItems().set(
                            i,
                            naam + " x" + (aantal - 1) + " - €" + String.format("%.2f", prijs)
                    );
                }

                totaalLabel.setText("Totaal: €" + String.format("%.2f", totaalPrijs));

                break;
            }
        }
    }

    @FXML
    protected void clearMand() {
        winkelmandList.getItems().clear();
        totaalPrijs = 0;
        totaalLabel.setText("Totaal: €0.00");
    }

    @FXML
    public void initialize() {
        //Fruitsoorten aanmaken
        Fruit fruit1 = new Fruit("Banaan", 1.20);
        Fruit fruit2 = new Fruit("Appel", 1.50);
        Fruit fruit4 = new Fruit("Perzik", 2.00);
        Fruit fruit5 = new Fruit("Mango", 2.80);
        Fruit fruit7 = new Fruit("Aardbeien", 3.00);
        Fruit fruit8 = new Fruit("Kiwi", 2.75);
        Fruit fruit9 = new Fruit("Avocado", 3.50);
        Fruit fruit10 = new Fruit("Sinaasappel", 1.50);
        Fruit fruit11 = new Fruit("Meloen", 1.25);
        Fruit fruit12 = new Fruit("Citroen", 2.20);
        Fruit fruit13 = new Fruit("Limoen", 2.00);
        Fruit fruit14 = new Fruit("Blauwe bessen", 4.00);
        Fruit fruit15 = new Fruit("Abrikoos", 2.75);
        Fruit fruit16 = new Fruit("Peer", 1.75);
        Fruit fruit17 = new Fruit("Ananas", 2.50);

        //Fruitsoorten toevoegen aan arrayList
        fruitList.add(fruit1);
        fruitList.add(fruit2);
        fruitList.add(fruit4);
        fruitList.add(fruit5);
        fruitList.add(fruit7);
        fruitList.add(fruit8);
        fruitList.add(fruit9);
        fruitList.add(fruit10);
        fruitList.add(fruit11);
        fruitList.add(fruit12);
        fruitList.add(fruit13);
        fruitList.add(fruit14);
        fruitList.add(fruit15);
        fruitList.add(fruit16);
        fruitList.add(fruit17);

        //Blok aanmaken met fruitsoort
        createFruitCards(fruitList);


        winkelmandList.setCellFactory(param -> new javafx.scene.control.ListCell<>() {

            private final Button plusButton = new Button("+");
            private final Button minButton = new Button("-");

            private final HBox box = new HBox(10);

            {
                box.getChildren().addAll(minButton, plusButton);

                plusButton.setOnAction(e -> {
                    String item = getItem();

                    if (item != null) {
                        String naam = item.split(" x")[0];

                        for (Fruit fruit : fruitList) {
                            if (fruit.getName().equals(naam)) {
                                addOne(fruit);
                                break;
                            }
                        }
                    }
                });

                minButton.setOnAction(e -> {
                    removeOne(getItem());
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    Label label = new Label(item);

                    HBox layout = new HBox(10, label, minButton, plusButton);

                    setGraphic(layout);
                }
            }
        });
    }


    private void createFruitCards(List<Fruit> fruitlijst) {
        int col = 0, row = 1;

        //Voor elke fruitsoort uit de array een fruitCard maken en toevoegen aan de Grid
        //Max 3 items per rij
        for (Fruit fruit : fruitlijst) {
            VBox card = createFruitCard(fruit);
            fruitGrid.add(card, col, row);
            col++;
            if (col > 2) {
                col = 0;
                row++;
            }
        }
    }

    //Fruitcard maken met de style card
    private VBox createFruitCard(Fruit fruit) {
        VBox card = new VBox(8);
        card.getStyleClass().add("card");
        card.prefWidthProperty().bind(fruitGrid.widthProperty().divide(4).subtract(20));

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
            addOne(fruit);
        });

        return new VBox(6, name, price, addButton);
    }


}

