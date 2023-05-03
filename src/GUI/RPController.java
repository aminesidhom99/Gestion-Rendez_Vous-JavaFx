package GUI;

import Entity.User;
import Entity.doctor;
import service.crud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class RPController implements Initializable{
    
    @FXML
    private Button btnreset;
      @FXML
    private Button btnresett;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private PasswordField passwordField;
    @FXML
private TextField emailField;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public void updatep(String email) {
    crud US = new crud();
    User u = US.getUser(email);
    btnreset.setOnAction(e -> {
        String newPassword = passwordField.getText();
        u.setPassword(newPassword);
        US.updatemdp(u);
        System.out.println("Password updated");
        retour(e);
    });
}
    @FXML
void updatePassword(ActionEvent event) {
    String email = emailField.getText();
    updatep(email);
    retour(event);
}


     
          @FXML
  void retour(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("FP.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                System.out.println("no no");
            }

    }
}
          