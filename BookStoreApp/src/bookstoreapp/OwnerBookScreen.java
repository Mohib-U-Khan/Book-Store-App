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
public class OwnerBookScreen {

    Button add, delete, back;
    TextField bookName, bookPrice;
    Scene bookScreen;

    private TableView<Book> allBooks = new TableView();

    public void start(Stage primaryStage) {
        Owner admin = Owner.getInstance("admin", "admin");

        ObservableList<Book> bookData = FXCollections.observableArrayList(admin.getBooks());

        TableColumn<Book, String> bookNameCol = new TableColumn("Book Name");
        TableColumn<Book, Double> bookPriceCol = new TableColumn("Price");

        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bookPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        allBooks.getColumns().setAll(bookNameCol, bookPriceCol);
        allBooks.setItems(bookData);

        add = new Button("Add");
        delete = new Button("Delete");
        back = new Button("Back");

        bookName = new TextField();
        bookPrice = new TextField();
        bookName.setPromptText("Name of Book");
        bookPrice.setPromptText("Price ($CAD)");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        GridPane middle = new GridPane();
        middle.setHgap(10);
        middle.setVgap(10);
        middle.setPadding(new Insets(10, 10, 10, 10));

        middle.add(bookName, 0, 0);
        middle.add(bookPrice, 1, 0);
        middle.add(add, 2, 0);
        middle.add(delete, 0, 1);
        middle.add(back, 1, 1);

        layout.getChildren().addAll(allBooks, middle);

        bookScreen = new Scene(layout, 550, 300);

        bookScreen.getStylesheets().add(this.getClass().getResource("owner.css").toExternalForm());
        bookNameCol.setStyle("-fx-background-color:  #222831;");
        bookPriceCol.setStyle("-fx-background-color:  #222831;");
        bookNameCol.setMinWidth(350);
        bookPriceCol.setMinWidth(175);

        bookName.setStyle("-fx-background-color:  #eeeeee; -fx-font-family: Helvetica, sans-serif; -fx-text-fill: #393e46; -fx-prompt-text-fill: #393e46;");
        bookPrice.setStyle("-fx-background-color:  #eeeeee; -fx-font-family: Helvetica, sans-serif; -fx-text-fill: #393e46; -fx-prompt-text-fill: #393e46;");

        add.setOnAction(e -> {
            if (admin.addBook(bookName.getText(), Double.parseDouble(bookPrice.getText()))) {
                if (Double.parseDouble(bookPrice.getText()) < 0) {
                    //Don't add book
                } else {
                    Book newBook = new Book(bookName.getText(), Double.parseDouble(bookPrice.getText()));
                    admin.books.add(newBook);
                    allBooks.getItems().add(newBook);
                }
            }
        });

        delete.setOnAction(e -> {
            admin.deleteBook(allBooks.getSelectionModel().getSelectedItem());
            bookData.remove(allBooks.getSelectionModel().getSelectedItem());
        });

        back.setOnAction(e -> {
            OwnerStartScreen startScreen = new OwnerStartScreen();
            startScreen.start(primaryStage);
        });

        primaryStage.setTitle("Booksmith");
        primaryStage.setScene(bookScreen);
        primaryStage.show();
    }
}
