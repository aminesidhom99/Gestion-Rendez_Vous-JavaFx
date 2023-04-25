/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui2;

//import com.mysql.cj.protocol.Message;
import edu.entities.Consultation;
import edu.entities.Ordonnance;
import edu.services.ConsultationCrud;
import edu.services.OrdonnanceCrud;
import edu.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.DriverManager;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * FXML Controller class
 *
 * @author SAHLI
 */
public class AjouterOrdonnanceController implements Initializable {

    @FXML
    private Button btnValider;
    @FXML
    private TextField tfDose;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfFrequence;
    @FXML
    private DatePicker tfDateCreation;
    @FXML
    private TableView<Ordonnance> testajout;
    @FXML
    private TableColumn<Ordonnance, String> tbNo;
    @FXML
    private TableColumn<Ordonnance, String> tbdos;
    @FXML
    private TableColumn<Ordonnance, String> tbFrequence;
    @FXML
    private TableColumn<Ordonnance, Date> tbDate;
    @FXML
    private Label LBShow;
    @FXML
    private ComboBox<String> tfIdConsultation;
    
    @FXML
    private TextField numeroSMS;

    /**
     * Initializes the controller class.
     */
    ConsultationCrud cs = new ConsultationCrud();
    List<Consultation> consultation = cs.getAll();
    private int id_consultation_id = -1;
    @FXML
    private Button btnValider1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for(Consultation c : consultation){
            tfIdConsultation.getItems().add(c.getEtat_Consultation());
            valuesMap.put(c.getEtat_Consultation(),c.getId());
        }
        
        tfIdConsultation.setOnAction(event ->{
            String SelectedOption = null;
            SelectedOption = tfIdConsultation.getValue();
            int SelectedValue = 0;
            SelectedValue = valuesMap.get(SelectedOption);
            id_consultation_id = SelectedValue;
        });
    }
private static List<Ordonnance> ordonnances = new ArrayList<>();

    @FXML
private void SaveOrdonnance(ActionEvent event) {
    String dose = tfDose.getText();
    String frequence = tfFrequence.getText();
    String Nom_Medicament = tfNom.getText();
    Date date_creation = null;
    try {
        LocalDate localDate = tfDateCreation.getValue();
        if (localDate != null) {
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            date_creation = Date.from(instant);
        }
    }catch (Exception e) {
        e.printStackTrace();
    }

   if (tfDose.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(" Veuillez saisir une valeur pour la dose. Ce champ ne peut pas être laissé vide! ");
    alert.showAndWait();
} else if (tfNom.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Entrez les noms des médicaments. Ce champ est obligatoire!");
    alert.showAndWait();
} else if (tfFrequence.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(" Veuillez saisir une valeur pour la frequence. Ce champ ne peut pas être laissé vide! ");
    alert.showAndWait();
} else if (tfDateCreation.getValue().getYear() < 2023) {
    Alert al2 = new Alert(Alert.AlertType.ERROR);
    al2.setHeaderText(null);
    al2.setContentText("Veuillez choisir une date courante");
    al2.showAndWait();
} else {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succées");
    alert.setHeaderText(null);
    alert.setContentText("Ajouter le medicament suivant svp");
    alert.showAndWait();
    Ordonnance o = new Ordonnance(id_consultation_id, frequence, dose, Nom_Medicament, date_creation);
ordonnances.add(o);

    tfDose.setText("");
    tfFrequence.setText("");
    tfNom.setText("");
   

}
}
@FXML
private void valider(ActionEvent event) {
    MyConnection myConnection = MyConnection.getInstance();
    Connection connection = myConnection.getCnx();

    // Créer une instance d'Ordonnance qui contiendra toutes les informations saisies pour chaque ordonnance
    Ordonnance ordonnance = new Ordonnance(id_consultation_id, "", "", "", null);

    for (Ordonnance o : ordonnances) {
        // Ajouter les informations de chaque ordonnance à l'instance créée
        ordonnance.setFrquence(ordonnance.getFrquence() + o.getFrquence() + "\n");
        ordonnance.setDose(ordonnance.getDose() + o.getDose() + "\n");
        ordonnance.setNom_Medicament(ordonnance.getNom_Medicament() + o.getNom_Medicament() + "\n");
        ordonnance.setDate_creation(o.getDate_creation());
    }

    PreparedStatement stmt = null;
    try {
        connection.setAutoCommit(false);
        stmt = connection.prepareStatement("INSERT INTO `ordonnance` (`id_consultation_id`, `frequence`, `dose`, `Nom_Medicament`, `date_creation`) VALUES (?, ?, ?, ?, ?)");

        // Insérer l'instance d'Ordonnance dans la base de données
        stmt.setInt(1, ordonnance.getId_consultation_id());
        stmt.setString(2, ordonnance.getFrquence());
        stmt.setString(3, ordonnance.getDose());
        stmt.setString(4, ordonnance.getNom_Medicament());
        stmt.setDate(5, new java.sql.Date(ordonnance.getDate_creation().getTime()));
        stmt.executeUpdate();

        connection.commit();
        ordonnances.clear(); // pour vider la liste après l'insertion à la base de données
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException ex2) {
            System.err.println(ex2.getMessage());
        }
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
                        send_SMS();

}





    @FXML
    private void ModifierPub(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateOrdonnance.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void showAllPersonnefilBDbyhaithem(ActionEvent event) {
        OrdonnanceCrud oc = new OrdonnanceCrud();

        LBShow.setText(oc.getAll().toString());

    }

    @FXML
    private void Supprimer(ActionEvent event) {
        OrdonnanceCrud oc = new OrdonnanceCrud();
        Ordonnance o = testajout.getSelectionModel().getSelectedItem();
        //    o.getId();
        int ordonnanceId = o.getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText(" VOULEZ VOUS SUPPRIMER UNE Publication ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            oc.supprimer(ordonnanceId);

            JOptionPane.showMessageDialog(null, " publication SUPPRIME ");
        } else {
            JOptionPane.showMessageDialog(null, " publication NON SUPPRIME ");
        }

        oc.getAll();
    }

    private void OnClickedPrint(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.testajout;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }
        }
    }

    @FXML
    private void AfficherPub(ActionEvent event) {

        OrdonnanceCrud oc = new OrdonnanceCrud();

        ObservableList<Ordonnance> liste = oc.getAll();

        tbNo.setCellValueFactory(new PropertyValueFactory<>("Nom_Medicament"));
        tbdos.setCellValueFactory(new PropertyValueFactory<>("dose"));
        tbFrequence.setCellValueFactory(new PropertyValueFactory<>("frequence"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
//tbaction.cellValueFactoryProperty(new PropertyValueFactory<>("supprimer()"));
        testajout.setItems(liste);

    }
//    public List<Ordonnance> afficher() throws SQLException {
//        Connection cnx = null;
//        Statement st = null;
//        ResultSet rs = null;
//        ObservableList<Ordonnance> liste = FXCollections.observableArrayList();
//        String requette = "SELECT * FROM Ordonnance";
//
//        try {
//            cnx = MyConnection.getInstance().getCnx();
//            st = cnx.createStatement();
//            rs = st.executeQuery(requette);
//            Ordonnance testajout;
//              
//while (rs.next()) {
//    testajout  = new Ordonnance(rs.getInt("dose"), rs.getString("frequence"), rs.getString("Nom_Medicament"), rs.getTimestamp("date_creation"));
//    liste.add(testajout);
//}
//
//            
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    /* Ignored */
//                }
//            }
//            if (st != null) {
//                st.close();
//                /* Ignored */
//            }
//        }
//        return liste;
//
//    }
//     private ObservableList<Ordonnance> getData() {
//        OrdonnanceCrud cp = new OrdonnanceCrud();
//        ObservableList<Ordonnance> myProds = cp.getAll();
//        return myProds;
//    }
//     
//    private void select(MouseEvent event) {
//        Ordonnance M = testajout.getSelectionModel().getSelectedItem();
//
//        tbNo.setText(M.getNom_Medicament());
//        tbdos.setText(M.getDose());
//        tbFrequence.setText(M.getFrquence());
//       // tbDate.setText(M.getDate_creation());
//tbDate.setText(M.getDate_creation().toString());
//
//    }
    void send_SMS (){
        
        String ACCOUNT_SID = "ACf6d2d23f0bd5d98998d28c3c8be3e17b";
        String AUTH_TOKEN = "1d85b0b025db167336a4a849b6df27e0";
             
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            String recipientNumber = "+216" + numeroSMS.getText();
            String message = "Bonjour ,\n"
                    + "Nous sommes ravis de vous informer qu'une ordonnance a été ajoutée.\n "
                    + "Veuillez contactez l'administration pour plus de details.\n "
                    + "Merci de votre fidélité et à bientôt .\n"
                    + "Cordialement,\n" 
                     + "Doc 2023";
                    
                
            Message twilioMessage = Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber("++15746525201"),message).create();
                
            System.out.println("SMS envoyé : " + twilioMessage.getSid());
          

        
         
     }
}
