
package GUI;

import Entities.Typeappoinment;
import Services.ServicetypeRDV;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ABCController implements Initializable {

  
   

 
   

    @FXML
    private ChoiceBox<Typeappoinment> type;

    private List<Typeappoinment> types = new ArrayList<>();
    @FXML
    private ChoiceBox<String> categorie;
    @FXML
    private DatePicker datefin;
    @FXML
    private DatePicker datedebut;
  

public void initialize() {
   
    
       List<String> modes = new ArrayList<>();
modes.add("En ligne");
modes.add("Présentielle");
categorie.getItems().addAll(modes);

categorie.setOnAction(event -> {
    String selectedMode = categorie.getSelectionModel().getSelectedItem();
    System.out.println("Mode sélectionné : " + selectedMode);
});
        // Récupération de tous les types d'appointment depuis la base de données
        ServicetypeRDV serviceTypeRDV = new ServicetypeRDV();

        try {
            types = serviceTypeRDV.readAll_reservation_cov();
        } catch (SQLException ex) {
            Logger.getLogger(ChoixBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Ajout des noms des types dans la choiceBox
        type.getItems().addAll(types);

        // Définition de la façon dont les noms des types seront affichés dans la choiceBox
        type.setConverter(new StringConverter<Typeappoinment>() {
            public String toString(Typeappoinment type) {
                return type.getNomtype();
            }

            public Typeappoinment fromString(String string) {
                return type.getItems().stream().filter(type ->
                        type.getNomtype().equals(string)).findFirst().orElse(null);
            }
        });

        // Définition de l'action à effectuer lorsque l'utilisateur sélectionne un type
        type.setOnAction(event -> {
            Typeappoinment selectedType = type.getSelectionModel().getSelectedItem();
            int selectedTypeId = selectedType.getId();
            System.out.println("Type sélectionné : " + selectedType.getNomtype() + ", id : " + selectedTypeId);
        });

    
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    

 

}
