package com.example.ecommerce;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Order {
    void placeOrder(String productID) throws SQLException {
        ResultSet res = HelloApplication.connection.executeQuery("select max(OrderID) as OrderID from orders");
        int orderID = 1;
        if (res.next()) {
            orderID = res.getInt("orderID") + 1;
        }
        Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());
        String query = String.format("insert into Orders values(%s,%s,'%s','%s')", orderID, productID, HelloApplication.emailID, ts);
        int response = HelloApplication.connection.executeUpdate(query);
        if (response > 0) {
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Your Order is Placed");
            dialog.showAndWait();
        }else{
            System.out.println("order is not placed");
        }

    }
}

