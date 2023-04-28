package GUI;

import java.io.IOException;
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
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class ConectedController implements Initializable {

    private Label LBShow;
  //  private HttpSession session;
    @FXML
    private Button resetPasswordButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
 public void showAll(ActionEvent event)
{
       
    }
    @FXML
  public void handleResetPasswordButton(ActionEvent event) {
      
       try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RP.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
 }
   // public void setSession(HttpSession session) {
     //   this.session = session;
    }

   

