/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author HP
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Myconn {
      private String url = "jdbc:mysql://localhost:3306/pijava";
      private String login = "root";
      private String pwd ="";
       private static Myconn instance ;
         Connection cnx;
      
      public Myconn() {
          try {
              cnx =DriverManager.getConnection(url, login, pwd);
                System.out.println("Connexion etablie");
          } catch (SQLException ex) {
            System.err.println(ex.getMessage());
          }
         
}
       public static Myconn getInstance (){
        if (instance == null)
            instance =new Myconn();
        return instance;
    }
       public Connection getcnx (){
          return cnx;
          }
        }
  
