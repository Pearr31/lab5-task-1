/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab5Task1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 6245576
 */
public class Lab5Task1 extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Label bagSelectionLabel = new Label("Select Bag type: ");
        ListView<String> bagTypesListView = new ListView<>();
        bagTypesListView.setPrefSize(220, 150);
        bagTypesListView.getItems().addAll("Full Decorative", "Beaded",
                "Pirate Design", "Leather", "Plain");
        VBox bagBox = new VBox(5, bagSelectionLabel, bagTypesListView);

        ComboBox<Integer> quantitiesCB = new ComboBox();
        quantitiesCB.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        quantitiesCB.setPrefWidth(60);
        Label quantitiesLabel = new Label("Select Quantity: ");
        VBox quantityBox = new VBox(5, quantitiesLabel, quantitiesCB);

        RadioButton small = new RadioButton("Small");
        RadioButton medium = new RadioButton("Medium");
        RadioButton large = new RadioButton("Large");
        ToggleGroup sizeGroup = new ToggleGroup();
        small.setToggleGroup(sizeGroup);
        medium.setToggleGroup(sizeGroup);
        large.setToggleGroup(sizeGroup);
        VBox sizeGroupVB = new VBox(5, new Label("Select size: "), small, medium, large);

        Button orderButton = new Button("Place Order");
        Button clearButton = new Button("Clear Selections");
        HBox orderBtns = new HBox(10, orderButton, clearButton);
        orderBtns.setAlignment(Pos.CENTER);

        Label resultLabel = new Label();
        resultLabel.setWrapText(true);

        HBox topBox = new HBox(30, bagBox, quantityBox, sizeGroupVB);
        topBox.setPadding(new Insets(20));

        VBox root = new VBox(15, topBox, orderBtns, resultLabel);
        root.setPadding(new Insets(20));
        root.setPrefWidth(500);

        orderButton.setOnAction(e -> {
            String selectedBag = bagTypesListView.getSelectionModel().getSelectedItem();
            Integer quantity = quantitiesCB.getValue();
            RadioButton selectedSize = (RadioButton) sizeGroup.getSelectedToggle();
            if (selectedBag == null || quantity == null || selectedSize == null) {
                resultLabel.setText("️ Please select bag style, quantity, and size.");
            } else {
                String bagText = quantity > 1 ? "Bags" : "Bag";
                resultLabel.setText("You ordered " + quantity + " " + 
                        selectedSize.getText() + " " + selectedBag + " " + bagText + ".");
            }
        });
        clearButton.setOnAction(e -> {
            bagTypesListView.getSelectionModel().clearSelection();
            quantitiesCB.getSelectionModel().clearSelection();
            sizeGroup.selectToggle(null);
            resultLabel.setText("");
        });

        Scene scene = new Scene(root, 920, 480);
        stage.setScene(scene);
        stage.show();
    }

}
