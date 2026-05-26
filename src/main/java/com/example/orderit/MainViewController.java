package com.example.orderit;


import com.example.orderit.model.Fruit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class MainViewController {

    @FXML
    private GridPane fruitGrid;

    private List<Fruit> fruitList = new ArrayList<>();

    @FXML
    private TableView<CartItem> winkelmandTable;

    @FXML
    private TableColumn<CartItem, String> productColumn;

    @FXML
    private TableColumn<CartItem, Integer> aantalColumn;

    @FXML
    private TableColumn<CartItem, Double> prijsColumn;

    private double totaalPrijs = 0;

    @FXML
    private Label totaalLabel;

    @FXML
    private TextField searchField;

    public static class CartItem {

        private String product;
        private int aantal;
        private double prijs;

        public CartItem(String product, int aantal, double prijs) {
            this.product = product;
            this.aantal = aantal;
            this.prijs = prijs;
        }

        public String getProduct() {
            return product;
        }

        public int getAantal() {
            return aantal;
        }

        public double getPrijs() {
            return prijs;
        }

        public void setAantal(int aantal) {
            this.aantal = aantal;
        }

        public void setPrijs(double prijs) {
            this.prijs = prijs;
        }
    }

    @FXML
    private TableColumn<CartItem, Void> plusColumn;

    @FXML
    private TableColumn<CartItem, Void> minColumn;



    @FXML
    private void plusOne() {

        CartItem item = winkelmandTable.getSelectionModel().getSelectedItem();

        if (item != null) {

            for (Fruit fruit : fruitList) {

                if (fruit.getName().equals(item.getProduct())) {
                    addOne(fruit);
                    break;
                }
            }
        }
    }

    private void addOne(Fruit fruit) {

        for (CartItem item : winkelmandTable.getItems()) {

            if (item.getProduct().equals(fruit.getName())) {

                item.setAantal(item.getAantal() + 1);
                item.setPrijs(item.getAantal() * fruit.getPrice());

                winkelmandTable.refresh();

                totaalPrijs += fruit.getPrice();
                totaalLabel.setText("Totaal: €" + String.format("%.2f", totaalPrijs));

                return;
            }
        }

        winkelmandTable.getItems().add(
                new CartItem(fruit.getName(), 1, fruit.getPrice())
        );

        totaalPrijs += fruit.getPrice();
        totaalLabel.setText("Totaal: €" + String.format("%.2f", totaalPrijs));
    }

    private void removeOne(CartItem item) {

        for (Fruit fruit : fruitList) {

            if (fruit.getName().equals(item.getProduct())) {

                totaalPrijs -= fruit.getPrice();

                if (item.getAantal() <= 1) {
                    winkelmandTable.getItems().remove(item);

                } else {

                    item.setAantal(item.getAantal() - 1);
                    item.setPrijs(item.getAantal() * fruit.getPrice());

                    winkelmandTable.refresh();
                }

                totaalLabel.setText("Totaal: €" + String.format("%.2f", totaalPrijs));

                break;
            }
        }
    }

    @FXML
    protected void clearMand() {
        winkelmandTable.getItems().clear();
        totaalPrijs = 0;
        totaalLabel.setText("Totaal: €0.00");
    }


    @FXML
    public void initialize() {

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

        createFruitCards(fruitList);

        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        aantalColumn.setCellValueFactory(new PropertyValueFactory<>("aantal"));
        prijsColumn.setCellValueFactory(new PropertyValueFactory<>("prijs"));
        prijsColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : "€" + String.format("%.2f", item));
            }
        });
        minColumn.setCellFactory(param -> new TableCell<>() {

            private final Button button = new Button("-");

            {
                button.setOnAction(e -> {

                    CartItem item = getTableView().getItems().get(getIndex());

                    removeOne(item);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });
        plusColumn.setCellFactory(param -> new TableCell<>() {

            private final Button button = new Button("+");

            {
                button.setOnAction(e -> {

                    CartItem item = getTableView().getItems().get(getIndex());

                    for (Fruit fruit : fruitList) {

                        if (fruit.getName().equals(item.getProduct())) {
                            addOne(fruit);
                            break;
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });
    }

    private void createFruitCards(List<Fruit> fruitlijst) {
        int col = 0, row = 1;

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

    private VBox createFruitCard(Fruit fruit) {
        VBox card = new VBox(8);
        card.getStyleClass().add("card");
        card.prefWidthProperty().bind(fruitGrid.widthProperty().divide(4).subtract(20));

        VBox infoBox = createFruitInfoBox(fruit);
        card.getChildren().addAll(infoBox.getChildren());

        return card;
    }

    private VBox createFruitInfoBox(Fruit fruit) {

        Label name = new Label(fruit.getName());
        name.getStyleClass().add("fruit-name");

        Label price = new Label("€" + fruit.getPrice());

        Button addButton = new Button("Voeg toe");

        addButton.setOnAction(e -> addOne(fruit));

        return new VBox(6, name, price, addButton);
    }

    @FXML
    private void filterFruit() {

        String searchText = searchField.getText().toLowerCase();

        List<Fruit> filteredList = new ArrayList<>();

        for (Fruit fruit : fruitList) {

            if (fruit.getName().toLowerCase().contains(searchText)) {
                filteredList.add(fruit);
            }
        }

        fruitGrid.getChildren().clear();

        createFruitCards(filteredList);
    }
}