/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nh.p.m.n.cnpm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class Connector {
    private static String DB_URL = "jdbc:mysql://localhost:3306/nmcnpm";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    
    public Connector() {
        
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        if(conn != null)
            System.out.println("Connect Succesfully");
    
        return conn;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connector conn = new Connector();
        Connection a = conn.getConnection();
    }
}
