/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Annisa Anggraini
 */
public class DB_connection {
    private Connection conn;
    private Statement st;
    public static void main (String[] args){
        DB_connection obj_DB_connection=new DB_connection();
        System.out.println(obj_DB_connection.get_connection());
    }
    
    public Connection get_connection(){
        Connection connection=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas3sc3", "root", "");
        } catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }
    
}
