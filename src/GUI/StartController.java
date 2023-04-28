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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
     * @param url
     * @param rb
     */
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
    @FXML
    public void loginuser(ActionEvent event) {
            crud crud = new crud();
    String email = this.emailField.getText();
    String password = this.passwordField.getText();
    boolean isAuth = crud.login(password, email);
   if (isAuth) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("conected.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
} else {
    // Afficher un message d'erreur ou une notification indiquant que les informations d'identification sont incorrectes
}
    }
    
}

