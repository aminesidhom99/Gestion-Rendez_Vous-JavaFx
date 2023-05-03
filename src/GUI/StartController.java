/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Entity.User;
import Entity.doctor;
import java.io.IOException;
import service.crud;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Myconn;
import utils.SessionManager;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class StartController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button resetPasswordButton;
    @FXML
    private Button signupButton;
    @FXML
    private Button loginButton;
  
   

     /**
     * Initializes the controller class.
     */
    
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   


    @FXML
    public void handleuserButton(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("who.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
           
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
    @FXML
  public void handleResetPasswordButton(ActionEvent event) {
      
       try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FP.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
 }
 
   public void login() throws IOException {
    String email = emailField.getText();
    String password = passwordField.getText();
    String pageUrl = "/GUI/conected.fxml";
    
    
    
    // Check if the email and password belong to a user
    String queryUser = "SELECT * FROM user WHERE email=? AND password=?";
    cnx = Myconn.getInstance().getcnx();
    try {
        PreparedStatement smt = cnx.prepareStatement(queryUser);
        smt.setString(1, email);
        smt.setString(2, password);
        ResultSet rs = smt.executeQuery();
        if (rs.next()) {
            User user = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("cin"), rs.getString("email"), rs.getString("adresse"), rs.getInt("age"), rs.getString("password"), rs.getString("Roleperm"));
            User.setCurrent_User(user);
            user.setRoleperm(rs.getString("Roleperm"));
            SessionManager.getInstace(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("cin"), rs.getString("email"), rs.getString("adresse"), rs.getInt("age"), rs.getString("password"), rs.getString("Roleperm"));

            

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Bienvenue " + user.getFirstName());
            alert.showAndWait();

            Stage loginStage = (Stage) loginButton.getScene().getWindow();
            loginStage.close();
            String rolePerm = user.getRoleperm();
            System.out.println("Role: " + rolePerm);
                 if(rolePerm.equals("Admin")){
                       Parent root = FXMLLoader.load(getClass().getResource("/GUI/AdminDash.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("DOC");
            stage.setScene(scene);
            stage.show();
        }
                       else{
                          
                       Parent root = FXMLLoader.load(getClass().getResource("/GUI/conected.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("DOC");
            stage.setScene(scene);
            stage.show();
                       
        } 
            /*if (rolePerm.equals("Admin")) {
                pageUrl = "/GUI/AdminDash.fxml";
            }*/
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Email ou Mot de passe incorrect !");
            alert.showAndWait();
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    
   
    
    // Check if the email and password belong to a doctor
    String queryDoctor = "SELECT * FROM doctor WHERE email=? AND password=?";
    try {
        PreparedStatement smt = cnx.prepareStatement(queryDoctor);
        smt.setString(1, email);
        smt.setString(2, password);
        ResultSet rs = smt.executeQuery();
        if (rs.next()) {
            doctor doctor = new doctor(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("cin"), rs.getString("email"), rs.getString("Address"), rs.getInt("age"), rs.getString("password"), rs.getString("Roleperm"), rs.getString("diplome"), rs.getString("specialite"));
            doctor.setCurrent_doctor(doctor);
            SessionManager.getInstaced(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("cin"), rs.getString("email"), rs.getString("Address"), rs.getInt("age"), rs.getString("password"), rs.getString("Roleperm"), rs.getString("diplome"), rs.getString("specialite"));
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Bienvenue Docteur " + doctor.getFirstName());
            alert.showAndWait();
            
            loginButton.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Doconnect.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("DOC");
            stage.setScene(scene);
            stage.show();
            
          
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

             }     
 
}
    


