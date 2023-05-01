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
 * @author amine
 */
public final class SessionManager {
 
    private static SessionManager instance;
 
     private static int id;

    private static String UserName;
        private static String Name;
    private static String Email;
    private static String Adresse;
   

   
  //SessionManager.getInstace(rs.getInt("id"),rs.getInt("cin"),rs.getString("user_name"),rs.getInt("numero"),rs.getString("email"),rs.getString("adresse"),rs.getString("roles"));
    private SessionManager(int id , String name , String username ,  String email  ) {
    SessionManager.id=id;
    SessionManager.UserName=username;
        SessionManager.Name=username;

    SessionManager.Email=email;
    }
 
    /**
    *
    * @param userName
    * @param employeeId
    * @param privileges
    * @return
    */
    public static SessionManager getInstace(int id , String name , String user_name ,  String email) {
        if(instance == null) {
            instance = new SessionManager( id ,name ,  user_name ,  email );
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

  

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String Name) {
        SessionManager.Name = Name;
    }

   public static String getName() {
        return Name;
    }

    public static void setName(String Name) {
        SessionManager.Name = Name;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String Email) {
        SessionManager.Email = Email;
    }

   

    
    public static void cleanUserSession() {
    id=0;
    Name="";
     UserName="";
     Email="";
    
    }
 
    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + UserName + '\'' +
                "email='" + Email + '\'' +
             
                "id = '" + id + '\'' +
           
                
            '}';
    }
 
    
    static class cleanUserSession {
 
        public cleanUserSession() {
          id=0;
         Name="";
     UserName="";
     
     Email="";

        }
    }
}