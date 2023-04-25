/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Appointment;
import Entities.Doctor;
import Entities.Typeappoinment;
import Entities.User;
import static GUI.SupprimerReservationController.ACCOUNT_SID;
import static GUI.SupprimerReservationController.AUTH_TOKEN;
import Services.ServiceRednezVous;
import Services.ServicetypeRDV;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class UpdateRDVController implements Initializable {

   @FXML
    private ChoiceBox<Typeappoinment> choixType;

    private List<Typeappoinment> types = new ArrayList<>();
    @FXML
    private ChoiceBox<String> choixcat;
       private static int a;
       Doctor c ; 
       User b ; 
    @FXML
    
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private VBox vb;



    /**
     * Initializes the controller class.
     */
    
     public static final String ACCOUNT_SID = "AC29f89cff9b5786c7eeb6ea8ed4bb7487";
  // Your Auth Token from twilio.com/console
  public static final String AUTH_TOKEN = "f66e989f775e5d08aceafda1d4c730ae";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         vb.getChildren().clear();
           List<String> modes = new ArrayList<>();
modes.add("En ligne");
modes.add("Présentielle");
choixcat.getItems().addAll(modes);

 ServicetypeRDV serviceTypeRDV = new ServicetypeRDV();

        try {
            types = serviceTypeRDV.readAll_reservation_cov();
        } catch (SQLException ex) {
            Logger.getLogger(ChoixBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Ajout des noms des types dans la choiceBox
        choixType.getItems().addAll(types);

        // Définition de la façon dont les noms des types seront affichés dans la choiceBox
        choixType.setConverter(new StringConverter<Typeappoinment>() {
            public String toString(Typeappoinment type) {
                return type.getNomtype();
            }

            public Typeappoinment fromString(String string) {
                return choixType.getItems().stream().filter(type ->
                        type.getNomtype().equals(string)).findFirst().orElse(null);
            }
        });
        
        
          List<Appointment> l = new ArrayList<Appointment>();
          ServiceRednezVous ser = new ServiceRednezVous();
          try {
            l = ser.readAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
           vb.setSpacing(20);
            for (Appointment d : l) {

        
            HBox hbox = new HBox();
            Label label1 = new Label(d.getUser().getName());
            Label label2 = new Label(d.getDoctor().getName());
            Label label3 = new Label(d.getAppointment_date().toString());
            Label label4 = new Label(d.getDatefin().toString());
            
Label label5 = new Label(String.valueOf(d.getType().getNomtype()));
       //     label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

/////////////////////////////////////////////////
          
            Label label7 = new Label(d.getCategorie());
           hbox.setStyle("  -fx-background-color: #67e860;  -fx-padding:20;  -fx-spacing:17 -fx-border-color: black -fx-border-width: 2px;    -fx-border-style: solid; ");
          //  label2.setStyle("-fx-font-size: 16px; -fx-text-fill: black; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
         //   label1.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
           //             label3.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            //label5.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");

         //   label2.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true; -fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
       //     label4.setStyle("-fx-font-size: 17px; -fx-text-fill: black; -fx-font-weight: bold;-fx-wrap-text: true;-fx-alignment: center;-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 0 1 0 0; -fx-border-style: solid;-fx-padding: 0 10 0 0;");
            
                      Button button = new Button("modifier !");
                                     button.setStyle("-fx-background-color: #87CEEB; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 12px; ");

  button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);
                 a = d.getId();
                 b = d.getUser() ; 
                 c = d.getDoctor() ; 
                  datedebut.setValue(d.getAppointment_date().toLocalDate());
             datefin.setValue(d.getDatefin().toLocalDate());
   
  

     choixType.setOnAction(o -> {
            Typeappoinment selectedType = choixType.getSelectionModel().getSelectedItem();
            int selectedTypeId = selectedType.getId();
            System.out.println("Type sélectionné : " + selectedType.getNomtype() + ", id : " + selectedTypeId);
        });
// tfnomtype.setText(String.valueOf(d.getNomtype()));
   choixcat.setOnAction(o -> {
    String selectedMode = choixcat.getSelectionModel().getSelectedItem();
    System.out.println("Mode sélectionné : " + selectedMode);
});


                 });
  
  
    Button button2 = new Button("Supprimer !");
                                     button2.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 12px; ");

button2.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("info evenement ");
                alert.setHeaderText(null);


try {
                     ser.supprimer(d.getId());
                      sendSms( "52505517","salut Votre  rendez vous chez "+ d.getDoctor().getName() +  " est annulé !") ;
                     JOptionPane.showMessageDialog(null, "type supprimé");
                 } catch (SQLException ex) {
                     Logger.getLogger(AffichageTypeController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                     hbox.getChildren().remove(hbox);
                     
                                   // Actualiser();

});
                
            hbox.getChildren().addAll(label1, label2,label3,label4,label5,label7, button , button2);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vb.getChildren().add(hbox);
        }
               vb.setStyle("fx-spacing: 10; fx-padding: 10; fx-alignment: center;");


    
    }    

    @FXML
    private void modifier(ActionEvent event) {
        System.out.println("GUI.UpdateRDVController.modifier()");
   int idDocteur =7;
        ServiceRednezVous ser = new ServiceRednezVous();

         Typeappoinment selectedType = choixType.getSelectionModel().getSelectedItem();
    String selectedMode = choixcat.getSelectionModel().getSelectedItem();
    Date selectedStartDate = Date.valueOf(datedebut.getValue());
    Date selectedEndDate = Date.valueOf(datefin.getValue());
    
    
    
       
        

if ( a == 0 ) { 
     Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("s'il vous plaît selectionner un type à modifier!");
            alert.showAndWait();
} else 

        if (selectedType == null || selectedMode.isEmpty() || selectedStartDate == null ||selectedEndDate== null  ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("s'il vous plaît il faut remplir tous les champs ! ");
            alert.showAndWait();
        } else  {
          
    
   
            Appointment p = new Appointment(a,b,c,selectedType, selectedStartDate,selectedEndDate, selectedMode,false);

            try {
                ser.update(p);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
       

            //Actualiser();
            JOptionPane.showMessageDialog(null, "bien modifie");
            //tftype.clear();
                       // tfdesc.clear();
         
    
        }
    }
     public static void sendSms(String recipient, String messageBody) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(
            new PhoneNumber("+216"+recipient), // To number
            new PhoneNumber("++16813233822"), // From number
            messageBody) // SMS body
        .create();

    System.out.println("Message sent: " + message.getSid());
  }

   
    
}