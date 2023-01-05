package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {

    @FXML
    public void initialize(){
        if(!HelloApplication.emailID.equals("")){
            loginbutton.setOpacity(0);
            email.setText(HelloApplication.emailID);
        }
    }
    @FXML
    Button loginbutton,logoutbutton;
    @FXML
    Label email;
    @FXML
    TextField searchtext;

    @FXML
    public void login(MouseEvent e) throws IOException {
        AnchorPane loginpage=FXMLLoader.load(getClass().getResource("Loginpage.fxml"));
        HelloApplication.root.getChildren().add(loginpage);
    }
    @FXML
    public void search(MouseEvent e) throws IOException, SQLException {
        ProductPage productPage=new ProductPage();

        Header header=new Header();
        AnchorPane productPane=new AnchorPane();
        productPane.getChildren().add(productPage.productsbySearch(searchtext.getText()));
        productPane.setLayoutX(130);
        productPane.setLayoutY(100);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,productPane);
    }
    @FXML
    public void logoutappear(MouseEvent e){
        if(logoutbutton.getOpacity()==0){
            logoutbutton.setOpacity(1);

        }else{
            logoutbutton.setOpacity(0);
        }
    }
    @FXML
    public void logout(MouseEvent e) throws IOException {
        if(logoutbutton.getOpacity()==1) {
            HelloApplication.emailID = "";
            logoutbutton.setOpacity(0);

            Header header = new Header();
            HelloApplication.root.getChildren().add(header.root);
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Logout successful");
            dialog.showAndWait();
        }
    }
    }
