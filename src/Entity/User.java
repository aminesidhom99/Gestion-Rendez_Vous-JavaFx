/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author HP
 */
public class User {
        private int id;
    private String firstName;
    private String lastName;
    private String email;
     private String adresse;
    private int age;
    private int cin;
    private String password;
    private String roleperm;
    public static User Current_User;

        public User(int id, String firstName, String lastName, int cin, String email, String adresse, int age, String password, String roleperm) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
          this.cin = cin;
        this.adresse = adresse;
        this.age = age;
        this.password = password;
        this.roleperm = "Patient";  
}
     public User() {
    
         }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
      
    }
     public static User getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(User Current_User) {
        User.Current_User = Current_User;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

   

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleperm() {
        return roleperm;
    }

    public void setRoleperm(String roleperm) {
        this.roleperm = roleperm;
    }
    @Override
    public String toString() {
                return "User{" + "id=" + id + ", firstname=" + firstName + ", lastname=" + lastName + ", cin=" + cin + ", email=" + email + ", adresse=" + adresse + ", age=" + age + ", password=" + password + ", roleperm=" + roleperm + '}';

       }
  
}
