package com.example.ecommerce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
   public static DatabaseConnection connection;
   public static Group root;
   public static String emailID;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        emailID="";
        connection=new DatabaseConnection();

       root=new Group();
       Header header=new Header();
       ProductPage productPage=new ProductPage();
        AnchorPane productPane=new AnchorPane();
        productPane.getChildren().add(productPage.products());
        productPane.setLayoutX(130);
        productPane.setLayoutY(100);

        root.getChildren().addAll(header.root,productPane);
       Scene scene = new Scene(root, 500, 500);
        stage.setTitle("The Virtual Mall");
        stage.setScene(scene);
        stage.show();
         stage.setOnCloseRequest(e ->{
            try{ connection.con.close();}
            catch(SQLException ex){
                ex.printStackTrace();
            }
         });
    }

    public static void main(String[] args) {
        launch();
    }
}