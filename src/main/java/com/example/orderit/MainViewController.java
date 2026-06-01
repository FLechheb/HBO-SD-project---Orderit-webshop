package com.example.orderit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.example.orderit.model.Fruit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.orderit.model.CartItem;
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

    @FXML
    private TableColumn<CartItem, Void> plusColumn;

    @FXML
    private TableColumn<CartItem, Void> minColumn;

    private double totaalPrijs = 0;

    @FXML
    private Label totaalLabel;

    @FXML
    private TextField searchField;

    @FXML
    private ChoiceBox<String> categoryBox;


// plus en min in winkelmandje
    private void addOne(Fruit fruit) {

        for (CartItem item : winkelmandTable.getItems()) {

            if (item.getProduct().equals(fruit.getName())) {

                item.setAantal(item.getAantal() + 1);

                winkelmandTable.refresh();

                totaalPrijs += fruit.getPrice();
                totaalLabel.setText("Totaal: €" + String.format("%.2f", totaalPrijs));

                return;
            }
        }

        winkelmandTable.getItems().add(
                new CartItem(fruit, 1)
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

    //categorie box
    @FXML
    private void filterCategory() {

        String selected = categoryBox.getValue();

        List<Fruit> filtered = new ArrayList<>();

        for (Fruit fruit : fruitList) {

            if (selected.equals("Alle")
                    || fruit.getCategory().equals(selected)) {

                filtered.add(fruit);
            }
          }


        fruitGrid.getChildren().clear();
        createFruitCards(filtered);
    }

    @FXML
    public void initialize() {

        categoryBox.getItems().add("Alle");
        categoryBox.getItems().add("Citrusvruchten");
        categoryBox.getItems().add("Tropisch fruit");
        categoryBox.getItems().add("Bessen");
        categoryBox.getItems().add("Steenvruchten");
        categoryBox.getItems().add("Pitfruit");

        categoryBox.setValue("Categorie");

        try {

            ObjectMapper mapper = new ObjectMapper();

            fruitList = mapper.readValue(
                    getClass().getResourceAsStream("/view/vruchtenlijst.json"),
                    new TypeReference<List<Fruit>>() {}
            );

            System.out.println(
                    getClass().getResource("/view/vruchtenlijst.json")
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        //productenpagina sorteren op categorie

        fruitList.sort((f1, f2) ->
                f1.getCategory().compareTo(f2.getCategory()));
        createFruitCards(fruitList);

        //winklemandje
        productColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getProduct()
                )
        );

        aantalColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getAantal()
                ).asObject()
        );

        prijsColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(
                        cellData.getValue().getPrijs()
                ).asObject()
        );
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

//producten pagina
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

        Label herkomst = new Label("Uit " + fruit.getHerkomst());
        herkomst.getStyleClass().add("fruit-herkomst");

        Label price = new Label("€" + String.format("%.2f", fruit.getPrice()));

        Label unit = new Label(fruit.getUnit());
        unit.getStyleClass().add("fruit-unit");

        Label category = new Label(fruit.getCategory());
        category.getStyleClass().add("fruit-category");

        Button addButton = new Button("Voeg toe");

        addButton.setOnAction(e -> addOne(fruit));

        return new VBox(6, name, herkomst, price, unit, category, addButton);
    }

//zoekbalk
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