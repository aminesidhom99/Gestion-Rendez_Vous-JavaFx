/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.reclamation;
import Entities.type_reclamation;
import Entities.user;
import Services.ServiceReclamation;
import Services.ServiceTypeRec;
import Services.ServiceUser;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author EMNA
 */
public class FXMLAjoutReclamationController implements Initializable {

    @FXML
    private TextField ajoutTel;
    @FXML
    private TextField ajoutEmail;
    
@FXML
private Label errorTel;
@FXML
private Label errorEmail;
    
    @FXML
    private ChoiceBox<String> ajoutTypeRec;
    @FXML
    private ChoiceBox<String> ajoutUser;
    @FXML
    private TextArea ajoutCmnt;
    @FXML
    private Button btnType1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
// Ajout de choix d'utilisateur dans la ComboBox ajoutUser
ServiceUser sp1 = new ServiceUser();
ObservableList<user> obsListUser = FXCollections.observableArrayList(sp1.afficher());
List<String> u=sp1.getallnames();
ObservableList<String> lu = FXCollections.observableArrayList(u);
ajoutUser.setItems(lu);
// Ajout de choix de type_reclamation dans la ComboBox ajoutTypeRec
ServiceTypeRec sp2 = new ServiceTypeRec();
ObservableList<type_reclamation> obsListTypeRec = FXCollections.observableArrayList(sp2.afficher());
List<String> l=sp2.getallnames();
ObservableList<String> l2 = FXCollections.observableArrayList(l);
ajoutTypeRec.setItems(l2);

// Sélection par défaut
ajoutTypeRec.getSelectionModel().selectFirst();        
ajoutUser.getSelectionModel().selectFirst();   

// Contrôles de saisie pour le champ de téléphone
     ajoutTel.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            ajoutTel.setText(oldValue);
            errorTel.setText("Num Tel doit contenir uniquement des chiffres!");
        } else {
            errorTel.setText("");
        }
    });

    }
        
    @FXML
    private void saveAjout(ActionEvent event) {
        // Vérification des champs
    if (!validateChamps()) {
        return;
    }
        reclamation r = new reclamation();
        ServiceTypeRec sp = new ServiceTypeRec();
        ServiceReclamation re =new ServiceReclamation();
        ServiceUser spp = new ServiceUser();
        type_reclamation i=new type_reclamation();
        List<type_reclamation> lr=sp.afficher();
        for(type_reclamation t:lr){
            String n=t.getNom();
            if(n.equals(ajoutTypeRec.getValue())){
            i=t;
            break;
            }
        }
        user p=new user();
        List<user> m=spp.afficher();
        for(user n:m){
            String j=n.getNom();
            String k=n.getPrenom();
            String ch=j+" "+k;
            if(ch.equals(ajoutUser.getValue())){
            p=n;
            break;
            }
        }
        r.setId_tr_id(i);
        r.setId_user_id(p);
        r.setDate(r.getCurrentTimestamp());
        r.setEmail(ajoutEmail.getText());
        r.setTelephone(Integer.parseInt(ajoutTel.getText()));
        r.setCmnt(ajoutCmnt.getText());
        r.setEtat("traitement en cours");
        re.ajouter(r);
            ObservableList<reclamation> obsListType = FXCollections.observableArrayList(re.afficher());
            ajoutEmail.setText("");
            ajoutTel.setText("");
            ajoutCmnt.setText("");
    }
private boolean validateChamps() {
    boolean isValid = true;

    // Vérification du champ email
    if (!ajoutEmail.getText().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
        errorEmail.setText("L'adresse e-mail n'est pas valide!");
        isValid = false;
    } else {
        errorEmail.setText("");
    }

    return isValid;
}
    
}
