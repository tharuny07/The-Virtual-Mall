package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductPage {
    ListView<HBox> products;

    ListView<HBox> productsbySearch(String search) throws SQLException {
        products=new ListView<>();
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("select * from Product");
        while(res.next()) {
            if (res.getString("productName").toLowerCase().contains(search.toLowerCase())) {
                Label name = new Label();
                Label productID = new Label();
                Label price = new Label();
                Button buy = new Button();
                name.setMinWidth(50);
                name.setMinHeight(25);
                productID.setMinWidth(50);
                productID.setMinHeight(25);
                price.setMinWidth(75);
                price.setMinHeight(25);
                buy.setText("Buy");
                buy.setMinWidth(50);

                HBox productDetails = new HBox();
                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailID.equals("")) {
                            Dialog<String> dialog=new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.setContentText("Please Login first");
                            dialog.showAndWait();
                        } else {
                            System.out.println("You are Logged in with " + HelloApplication.emailID);
                            Order order = new Order();
                            try {
                                order.placeOrder(productID.getText());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });

                name.setText(res.getString("productName"));
                productID.setText(res.getString("productId"));
                price.setText(res.getString("price"));

                productDetails.getChildren().addAll(productID, name, price, buy);
                productList.add(productDetails);
            }
        }
        products.setItems(productList);
        return products;
    }

    ListView<HBox> products() throws SQLException {
        products=new ListView<>();
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("select * from Product");
        while(res.next()) {
            Label name = new Label();
            Label productID = new Label();
            Label price = new Label();
            Button buy = new Button();
            name.setMinWidth(50);
            name.setMinHeight(25);
            productID.setMinWidth(50);
            productID.setMinHeight(25);
            price.setMinWidth(75);
            price.setMinHeight(25);
            buy.setText("Buy");
            buy.setMinWidth(50);

            HBox productDetails = new HBox();
            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                if(HelloApplication.emailID.equals("")){
                    Dialog<String> dialog=new Dialog<>();
                    dialog.setTitle("Login");
                    ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().add(type);
                    dialog.setContentText("Please Login First");
                    dialog.showAndWait();
                }else{
                    System.out.println("You are Logged in with "+HelloApplication.emailID);
                    Order order=new Order();
                    try {
                        order.placeOrder(productID.getText());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                }
            });

            name.setText(res.getString("productName"));
            productID.setText(res.getString("productId"));
            price.setText(res.getString("price"));

            productDetails.getChildren().addAll(productID, name, price, buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return products;
    }
}
