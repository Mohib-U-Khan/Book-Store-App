/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CustomerStartScreen {

    Button buy, redeemBuy, logout;
    Label welcomeMessage;
    Scene startScreen;
    double totalPrice = 0;
    private ArrayList<Book> booksToBuy = new ArrayList<Book>();
    private TableView<Book> selectBook = new TableView();

    public void start(Stage primaryStage) {
        Owner admin = Owner.getInstance("admin", "admin");

        ObservableList<Book> bookData = FXCollections.observableArrayList(admin.getBooks());

        TableColumn<Book, String> bookCol = new TableColumn("Book Name");
        TableColumn<Book, Double> priceCol = new TableColumn("Price");
        TableColumn<Book, CheckBox> select = new TableColumn("Select");

        bookCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        select.setCellValueFactory(new PropertyValueFactory<>("select"));

        selectBook.getColumns().setAll(bookCol, priceCol, select);
        selectBook.setItems(bookData);

        buy = new Button("Buy");
        redeemBuy = new Button("Redeem Points and Buy");
        logout = new Button("Logout");

        welcomeMessage = new Label(admin.getCurrentCustomer().toString());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        HBox bottomRow = new HBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        bottomRow.getChildren().addAll(buy, redeemBuy, logout);
        layout.getChildren().addAll(welcomeMessage, selectBook, bottomRow);

        startScreen = new Scene(layout, 550, 300);

        bookCol.setMinWidth(300);
        priceCol.setMinWidth(125);
        select.setMinWidth(100);
        bookCol.setStyle("-fx-background-color:  #222831;");
        priceCol.setStyle("-fx-background-color:  #222831; -fx-alignment: CENTER");
        select.setStyle("-fx-background-color:  #222831; -fx-alignment: CENTER");
        startScreen.getStylesheets().add(this.getClass().getResource("customer.css").toExternalForm());

        CustomerCostScreen bought = new CustomerCostScreen();

        buy.setOnAction(e -> {
            for (Book b : bookData) {
                if (b.getSelect().isSelected()) {
                    booksToBuy.add(b);
                    totalPrice += b.getPrice();
                    admin.getCurrentCustomer().setTotalPrice(totalPrice);
                    admin.getCurrentCustomer().buy(b);
                    admin.deleteBook(b);
                }
            }
            booksToBuy.clear();
            bought.start(primaryStage);
        });

        redeemBuy.setOnAction(e -> {
            for (Book b : bookData) {
                if (b.getSelect().isSelected()) {
                    booksToBuy.add(b);
                    totalPrice += b.getPrice();
                    admin.getCurrentCustomer().setTotalPrice(totalPrice);
                }
            }

            admin.getCurrentCustomer().pointsbuy(booksToBuy);

            for (Book b : booksToBuy) {
                admin.deleteBook(b);
            }

            booksToBuy.clear();
            bought.start(primaryStage);
        });

        logout.setOnAction(e -> {
            try {
                LoginScreen loggedOut = new LoginScreen();
                loggedOut.start(primaryStage);
            } catch (IOException ex) {
                Logger.getLogger(CustomerStartScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        primaryStage.setTitle("Booksmith");
        primaryStage.setScene(startScreen);
        primaryStage.show();
    }
}
