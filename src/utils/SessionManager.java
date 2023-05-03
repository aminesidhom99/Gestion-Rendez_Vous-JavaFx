/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;



/**
 *
 * @author Khalil
 */
public final class SessionManager {
 
    private static SessionManager instance;
 
     private static int id;
    private static int cin;
    private static String firstName;
    private static String lastName;
    private static int age;
    private static String email;
    private static String adresse;
    private static String password;
    private static String roleperm;
         private static String specialite;
               private static String diplome;
  
   
     
   
   
   
   

   
  //                     SessionManager.getInstace(rs.getInt("id"),rs.getString("firstName"),rs.getString("lastName"),rs.getInt("cin"),rs.getString("email"),rs.getString("adresse"),rs.getInt("age"),rs.getString("password"),rs.getString("Roleperm"));

    private SessionManager(int id, String firstName, String lastName, int cin, String email, String adresse, int age, String password, String roleperm) {
    SessionManager.id=id;
    SessionManager.cin=cin;
    SessionManager.firstName=firstName;
    SessionManager.age=age;
    SessionManager.email=email;
    SessionManager.adresse=adresse;
     SessionManager.lastName=lastName;
              SessionManager.password=password;
              SessionManager.roleperm=roleperm;
    }
    private SessionManager(int id, int cin, String firstName, String lastName, String email, String adresse, int age, String password, String roleperm ,String specialite,String diplome) {
    SessionManager.id=id;
    SessionManager.cin=cin;
    SessionManager.firstName=firstName;
    SessionManager.age=age;
    SessionManager.email=email;
    SessionManager.adresse=adresse;
     SessionManager.lastName=lastName;
              SessionManager.password=password;
              SessionManager.roleperm=roleperm;
              SessionManager.specialite=specialite;
              SessionManager.diplome=diplome;
    }
 
    /**
    *
    * @param userName
    * @param employeeId
    * @param privileges
    * @return
    */

    /**
     *
     * @param id
     * @param userName
     * @param employeeId
     * @param privileges
     * @return
     */

    /**
     *
     * @param id
     * @param firstName
     * @param userName
     * @param employeeId
     * @param privileges
     * @return
     */

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param userName
     * @param employeeId
     * @param privileges
     * @return
     */

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param cin
     * @param userName
     * @param employeeId
     * @param privileges
     * @return
     */

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param cin
     * @param email
     * @param userName
     * @param employeeId
     * @param privileges
     * @return
     */

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param cin
     * @param email
     * @param adresse
     * @param userName
     * @param employeeId
     * @param privileges
     * @return
     */

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param cin
     * @param email
     * @param adresse
     * @param age
     * @param userName
     * @param employeeId
     * @param privileges
     * @return
     */

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param cin
     * @param email
     * @param adresse
     * @param age
     * @param password
     * @param userName
     * @param employeeId
     * @param privileges
     * @return
     */

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param cin
     * @param email
     * @param adresse
     * @param age
     * @param password
     * @param roleperm
     * @param userName
     * @param employeeId
     * @param privileges
     * @return
     */
    public static SessionManager getInstace(int id , String firstName , String lastName , int cin ,String email , String adresse , int age , String password , String roleperm ) {
        if(instance == null) {
            instance = new SessionManager( id ,  firstName ,  lastName ,cin ,  email ,adresse , age ,password , roleperm);
        }
        return instance;
    }
    public static SessionManager getInstaced(int id , String firstName , String lastName , int cin ,String email , String adresse , int age , String password , String roleperm ,String specialite,String diplome ) {
        if(instance == null) {
            instance = new SessionManager( id , cin ,  firstName ,  lastName ,  email ,adresse , age ,password , roleperm, specialite, diplome);
        }
        return instance;
    }

    public static SessionManager getInstance() {
        return instance;
    }

    public static void setInstance(SessionManager instance) {
        SessionManager.instance = instance;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        SessionManager.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        SessionManager.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        SessionManager.email = email;
    }
     public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        SessionManager.cin = cin;
    }

   

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        SessionManager.adresse = adresse;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        SessionManager.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        SessionManager.password = password;
    }

    public String getRoleperm() {
        return roleperm;
    }

    public void setRoleperm(String roleperm) {
        SessionManager.roleperm = roleperm;
    }
     public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        SessionManager.diplome = diplome;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        SessionManager.specialite = specialite;
    }
    
    public static void cleanUserSession() {
    id=0;
    cin=0;
     firstName="";
     age=0;
     email="";
     adresse="";
    }
 
    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + firstName + '\'' +
                "email='" + email + '\'' +
               
                "id = '" + id + '\'' +
           
                
            '}';
    }
 
    
    static class cleanUserSession {
 
        public cleanUserSession() {
         id=0;
    cin=0;
     firstName="";
     age=0;
     email="";
     adresse="";
        }
    }
}