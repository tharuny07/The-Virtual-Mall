package com.example.ecommerce;

import java.sql.*;

public class DatabaseConnection {
    Connection con=null;
    String SQLURL="jdbc:mysql://localhost:3306/ecommerce?useSSL=false";
    String username ="root";
    String password="Mallesham@5";

    DatabaseConnection() throws SQLException {
        con = DriverManager.getConnection(SQLURL, username, password);
        if(con!=null){
         System.out.println("our connection is established");
        }

    }
    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement= con.createStatement();
        ResultSet result =statement.executeQuery(query);
        return result;
    }
    public int executeUpdate(String query) throws SQLException {
        int row=0;
        Statement statement=con.createStatement();
       row= statement.executeUpdate(query);
       return row;
    }
}
