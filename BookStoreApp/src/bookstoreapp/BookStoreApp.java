/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.stage.StageStyle;

/**
 *
 * @author Peter Cai
 */
public class BookStoreApp extends Application {

    Stage window;
    Scene owner_start_screen, owner_books_screen, owner_customers_screen;
    Scene customer_start_screen, customer_cost_screen;

    Button logIn, logOut;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Owner admin = Owner.getInstance("admin", "admin");
        admin.readBooks();
        admin.readCustomers();

        window = primaryStage;
        LoginScreen login_screen = new LoginScreen();
        primaryStage.setResizable(false);
        login_screen.start(primaryStage);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
