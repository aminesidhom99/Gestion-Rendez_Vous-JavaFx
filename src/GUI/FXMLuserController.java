/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.User;
import java.io.IOException;
import service.crud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLuserController implements Initializable {

      @FXML
    private TextField TFNom;
    @FXML
    private TextField TFPrenom;
    @FXML
    private TextField TFEmail;
    private Label LBShow;
@FXML
    private TextField TFAdresse;
@FXML
private TextField TFAge;
@FXML
private TextField TFCin;
@FXML
private TextField TFPassword;


    /**
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TFNom.setText("");
    TFPrenom.setText("");
    TFEmail.setText("");
    TFAdresse.setText("");
    TFAge.setText("");
    TFCin.setText("");
    TFPassword.setText("");
   
    }    
     @FXML
  
public void ajouter(ActionEvent event) throws IOException {
    User p = new User();
    p.setFirstName(TFNom.getText());
    p.setLastName(TFPrenom.getText());
    p.setEmail(TFEmail.getText());
    p.setAdresse(TFAdresse.getText());
    p.setAge(Integer.parseInt(TFAge.getText()));
    p.setCin(Integer.parseInt(TFCin.getText()));
    p.setPassword(TFPassword.getText());
    p.setRoleperm("Patient");

    crud sp = new crud();
    sp.ajouter(p);

    Parent root = FXMLLoader.load(getClass().getResource("conected.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
    private void showAll(ActionEvent event) {
         crud sp =new crud();
       LBShow.setText(sp.afficher().toString());
        
    }   
}
