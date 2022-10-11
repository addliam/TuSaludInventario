package com.farmacia.backend;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Liam
 */

public class DbConfig {
    public Connection conn;
    public DbConfig(){
        try {
            String usernameConnectionString = "";
            String passwordConnectionString = "";
            Dotenv dotenv = null;
            try {
                dotenv = Dotenv.configure().load();  
                // un archivo .env debe contener las variables MYSQL_USERNAME y MYSQL_PASSWORD con las credenciales
                usernameConnectionString = dotenv.get("MYSQL_USERNAME");
                passwordConnectionString = dotenv.get("MYSQL_PASSWORD");                
            } catch (DotenvException e) {
                System.out.println("DOT ENV package error"+e);
            }
            String urlConnectionString = "jdbc:mysql://localhost:3306/inventario_farmacia";
            conn = DriverManager.getConnection(urlConnectionString, usernameConnectionString, passwordConnectionString);
        } catch (SQLException ex) {
            System.out.println("An error ocurred while connection to database MySql.");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } 
    }
    
    public Connection getConn() {
        return conn;
    }
    
    public int closeConnection() {
        try {
            conn.close();
            return 0;
        } catch (Exception e) {
            System.out.println("ERROR CLOSSING CONNECTION");
            return 1;
        }
    }
}
