/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Peter Cai
 */
public class OwnerCustomersScreen {

    Button add, delete, back;
    TextField usernameInput, passwordInput;
    Scene customerScreen;
    private TableView<Customer> allCustomers = new TableView();

    public void start(Stage primaryStage) {
        Owner admin = Owner.getInstance("admin", "admin");

        ObservableList<Customer> customerData = FXCollections.observableArrayList(admin.getCustomers());

        TableColumn<Customer, String> usernameCol = new TableColumn<>("Username");
        TableColumn<Customer, String> passwordCol = new TableColumn<>("Password");
        TableColumn<Customer, Integer> pointsCol = new TableColumn<>("Points");

        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        allCustomers.getColumns().setAll(usernameCol, passwordCol, pointsCol);
        allCustomers.setItems(customerData);

        add = new Button("Add");
        delete = new Button("Delete");
        back = new Button("Back");

        usernameInput = new TextField();
        passwordInput = new TextField();
        usernameInput.setPromptText("Username:");
        passwordInput.setPromptText("Password:");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        GridPane interactions = new GridPane();
        interactions.setHgap(10);
        interactions.setVgap(10);
        interactions.setPadding(new Insets(10, 10, 10, 10));

        interactions.add(usernameInput, 0, 0);
        interactions.add(passwordInput, 1, 0);
        interactions.add(add, 2, 0);
        interactions.add(delete, 0, 1);
        interactions.add(back, 1, 1);

        layout.getChildren().addAll(allCustomers, interactions);

        customerScreen = new Scene(layout, 550, 300);
        
        usernameCol.setMinWidth(200);
        passwordCol.setMinWidth(200);
        pointsCol.setMinWidth(125);

        usernameCol.setStyle("-fx-background-color:  #222831;");
        passwordCol.setStyle("-fx-background-color:  #222831;");
        pointsCol.setStyle("-fx-background-color:  #222831;");
        usernameInput.setStyle("-fx-background-color:  #eeeeee; -fx-font-family: Helvetica, sans-serif; -fx-text-fill: #393e46; -fx-prompt-text-fill: #393e46;");
        passwordInput.setStyle("-fx-background-color:  #eeeeee; -fx-font-family: Helvetica, sans-serif; -fx-text-fill: #393e46; -fx-prompt-text-fill: #393e46;");
        customerScreen.getStylesheets().add(this.getClass().getResource("owner.css").toExternalForm());

        add.setOnAction(e -> {
            if (admin.addCustomer(usernameInput.getText(), passwordInput.getText())) {
                Customer newUser = new Customer(usernameInput.getText(), passwordInput.getText());
                admin.customers.add(newUser);
                allCustomers.getItems().add(newUser);
            }
        });

        delete.setOnAction(e -> {
            admin.deleteCustomer(allCustomers.getSelectionModel().getSelectedItem());
            customerData.remove(allCustomers.getSelectionModel().getSelectedItem());
        });

        back.setOnAction(e -> {
            OwnerStartScreen startScreen = new OwnerStartScreen();
            startScreen.start(primaryStage);
        });

        primaryStage.setTitle("Booksmith");
        primaryStage.setScene(customerScreen);
        primaryStage.show();
    }
}
