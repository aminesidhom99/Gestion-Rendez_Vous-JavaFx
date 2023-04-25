/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author amine
 */
public class User {
    private String name,email,username ; 
    private int id ;

    public User(String name, String email, String username, int id) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.id = id;
    }

    public User(int id) {
        this.id = id;
    }

    public User(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }
  
     @Override
    public String toString() {
        return  "id=" + id + ", email=" + email + ", name=" + name + ", username =" + username + '}';
    }
}
