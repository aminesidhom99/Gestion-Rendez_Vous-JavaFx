
package GUI;
import javafx.scene.control.Button;
import Entities.Appointment;
import Entities.Typeappoinment;
import Services.ServicetypeRDV;
import Services.ServiceRednezVous;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * FXML Controller class
 *
 * @author amine
 */


public class SupprimerReservationController implements Initializable {

    @FXML
    private VBox vb;

    /**
     * Initializes the controller class.
     */
    
    
    
    public static final String ACCOUNT_SID = "AC2cf3b48480710e75f659adfb8932f0f2";
  // Your Auth Token from twilio.com/console
  public static final String AUTH_TOKEN = "830a31796d5e9844b56c19cb24d4ea09";
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    vb.getChildren().clear();
        List<Appointment> l = new ArrayList<Appointment>();
        ServiceRednezVous ser = new ServiceRednezVous();
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (Appointment d : l) {

        
            HBox hbox = new HBox();
            Label label1 = new Label(d.getUser().getName());
            Label label2 = new Label(d.getDoctor().getName());
            Label label3 = new Label(d.getAppointment_date().toString());
            Label label4 = new Label(d.getDatefin().toString());
            
Label label5 = new Label(String.valueOf(d.getType().getNomtype()));
      


          
            Label label7 = new Label(d.getCategorie());
               hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
          label7.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            Button button = new Button("Supprimer !");
                                     button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


                 try {
                     ser.supprimer(d.getId());
                     
                     //////
                     //d.getTelephone()
                     sendSms( "24076282","salut"  +  "vous avez une nouvelle reservation sur votre covoiturage") ;
                     /////////////
                     JOptionPane.showMessageDialog(null, "covoiturage supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(AffichageTypeController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     
                                    actualiser();

});
            hbox.getChildren().addAll(label1, label2, label3, label4 , label5,label7, button);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
       

    }
    
    private void actualiser() {
        vb.getChildren().clear();
        List<Appointment> l = new ArrayList<Appointment>();
        ServiceRednezVous ser = new ServiceRednezVous();
        try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        ///////////////////////////////////////////////user
        vb.setSpacing(20);
        for (Appointment d : l) {

        
            HBox hbox = new HBox();
           Label label1 = new Label(d.getUser().getName());
            Label label2 = new Label(d.getDoctor().getName());
            Label label3 = new Label(d.getAppointment_date().toString());
            Label label4 = new Label(d.getDatefin().toString());
            
Label label5 = new Label(String.valueOf(d.getType().getNomtype()));
      


          
            Label label7 = new Label(d.getCategorie());
               hbox.setStyle("  -fx-background-color: #f5eeeee5;  -fx-padding:17;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
            label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
                        label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
 label7.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
         Button button = new Button("Supprimer !");
                                     button.setStyle("-fx-background-color: #3f51b5; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 10px; ");

button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


try {
                     ser.supprimer(d.getId());
                     JOptionPane.showMessageDialog(null, "covoiturage supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(AffichageTypeController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     

});
            
            hbox.getChildren().addAll(label1, label2, label3, label4 , label5,label7, button);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
        
 
    } 
    
    
    
    
    
    @FXML
    private void goaffi(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/GUI/RendezVousAffiche.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goajout(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/ajoutrendezVous.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
 @FXML
    private void gomodif(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/GUI/UpdateRDV.fxml")) ; 
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
    public static void sendSms(String recipient, String messageBody) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(
            new PhoneNumber("+216"+recipient), // To number
            new PhoneNumber("++15673716202"), // From number
            messageBody) // SMS body
        .create();

    System.out.println("Message sent: " + message.getSid());
  }
    
}



