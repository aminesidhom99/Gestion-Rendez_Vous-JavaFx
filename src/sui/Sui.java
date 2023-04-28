/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sui;

import Entity.User;
import java.util.List;
import service.crud;
import utils.Myconn;

/**
 *
 * @author HP
 */
public class Sui {
     public static void main(String[] args) {
         Myconn mc = new Myconn();
           crud c = new crud();

    /* Add user 1
    User user1 = new User();
    user1.setFirstName("belha");
    user1.setLastName("zah");
    user1.setEmail("belha@zah");
    c.ajouter(user1);

    // Add user 2
    User user2 = new User();
    user2.setFirstName("test");
    user2.setLastName("tet");
    user2.setEmail("test@tet");
    c.ajouter(user2);

    // Display all users
    List<User> users = c.afficher();
        for (User u : users) {
        System.out.println(u.getId() + ", " + u.getFirstName() + ", " + u.getLastName() + ", " + u.getEmail());
    }

    // Delete user 2
    c.supprimer(2);*/
}
     }

