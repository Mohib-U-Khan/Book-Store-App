/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;

/**
 *
 * @author Peter Cai
 */
public class CustomerCostScreen {

    Button logout;
    Label totalCost, pointsAndStatus;
    Scene costScreen;

    public void start(Stage primaryStage) {
        Owner admin = Owner.getInstance("admin", "admin");
        String amount = String.format("%.2f", admin.getCurrentCustomer().getTotalPrice());

        logout = new Button("Logout");

        totalCost = new Label("Total Cost: $" + amount);
        pointsAndStatus = new Label("Points: " + admin.getCurrentCustomer().getPoints() + " Status: " + admin.getCurrentCustomer().getStatus());

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));

        root.getChildren().addAll(totalCost, pointsAndStatus, logout);

        costScreen = new Scene(root, 550, 300);
        costScreen.getStylesheets().add(this.getClass().getResource("cost.css").toExternalForm());

        logout.setOnAction(e -> {
            try {
                LoginScreen loggedOut = new LoginScreen();
                loggedOut.start(primaryStage);
            } catch (IOException ex) {
                Logger.getLogger(CustomerCostScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        admin.getCurrentCustomer().setTotalPrice(0.0);

        primaryStage.setTitle("Booksmith");
        primaryStage.setScene(costScreen);
        primaryStage.show();
    }
}
