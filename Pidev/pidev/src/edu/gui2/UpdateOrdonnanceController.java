/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui2;

import edu.entities.Ordonnance;
import edu.services.OrdonnanceCrud;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SAHLI
 */
public class UpdateOrdonnanceController implements Initializable {

    @FXML
    private TextField tfDose1;
    @FXML
    private TextField tfNom1;
    @FXML
    private TextField tfFrequence1;
    @FXML
    private DatePicker tfDateCreation1;

    @FXML
    private TableColumn<Ordonnance, String> tbNo1;
    @FXML
    private TableColumn<Ordonnance, String> tbdos1;
    @FXML
    private TableColumn<Ordonnance, String> tbFrequence1;
    @FXML
    private TableColumn<Ordonnance, LocalDate> tbDate1;
    @FXML
    private TableView<Ordonnance> testajout1;
    @FXML
    private Button btnz;
    @FXML
    private TextField tfId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Afficherord(ActionEvent event) {
        OrdonnanceCrud pc = new OrdonnanceCrud();

        ObservableList<Ordonnance> liste = pc.getAll();

        tbNo1.setCellValueFactory(new PropertyValueFactory<>("Nom_Medicament"));
        tbdos1.setCellValueFactory(new PropertyValueFactory<>("dose"));
        tbFrequence1.setCellValueFactory(new PropertyValueFactory<>("frequence"));
        tbDate1.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
//tbaction.cellValueFactoryProperty(new PropertyValueFactory<>("supprimer()"));
        testajout1.setItems(liste);

    }

    @FXML
    private void Modifordo(ActionEvent event) {
        if (tfDose1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" Veuillez saisir une valeur pour la dose. Ce champ ne peut pas être laissé vide! ");
            alert.showAndWait();
        } else if (tfNom1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" Entrez les noms des médicaments. Ce champ est obligatoire! ");
            alert.showAndWait();
        } else if (tfFrequence1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir une valeur pour la frequence. Ce champ ne peut pas être laissé vide! ");
            alert.showAndWait();
        } else if (tfDateCreation1.getValue().getYear() > 2023) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("vérifier votre date ");
            alert.showAndWait();
        } else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("La modification d'un new Ordonnance a été effectué avec succées");
            alert.showAndWait();
            OrdonnanceCrud sa = new OrdonnanceCrud();
            int id = Integer.parseInt(tfId.getText());

            String Nom_Medicament = tfNom1.getText();
            String dose = tfDose1.getText();
            String frequence = tfFrequence1.getText();
//Timestamp date_creation = tfDateCreation1.getValue();
            LocalDate date = tfDateCreation1.getValue();
            LocalDateTime dateTime = date.atStartOfDay();
            Timestamp date_creation = Timestamp.valueOf(dateTime);

            Ordonnance re;
            re = (Ordonnance) testajout1.getSelectionModel().getSelectedItem();
            re.setId(id);

            re.setNom_Medicament(Nom_Medicament);
            re.setDose(dose);
            re.setFrquence(frequence);
            re.setDate_creation(date_creation);

            sa.modifier(re);
            sa.getAll();

        }

//    private void Modifordo(ActionEvent event) {
//        if (tfDose1.getText().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText(" Veuillez saisir une valeur pour la dose. Ce champ ne peut pas être laissé vide! ");
//            alert.showAndWait();
//        } else if (tfNom1.getText().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText(" Entrez les noms des médicaments. Ce champ est obligatoire! ");
//            alert.showAndWait();
//        } else if (tfFrequence1.getText().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("Veuillez saisir une valeur pour la frequence. Ce champ ne peut pas être laissé vide! ");
//            alert.showAndWait();
//        } else if (tfDateCreation1.getValue().getYear() > 2023) {
//
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("vérifier votre date ");
//            alert.showAndWait();
//        } else {
//
//            OrdonnanceCrud sa = new OrdonnanceCrud();
//
//            String Nom_Medicament = tfNom1.getText();
//                        String dose = tfDose1.getText();
//            String frequence = tfFrequence1.getText();
//
//            LocalDate date = tfDateCreation1.getValue();
//            LocalDateTime dateTime = date.atStartOfDay();
//            Timestamp date_creation = Timestamp.valueOf(dateTime);
//
//            Ordonnance re;
//            re = (Ordonnance) testajout1.getSelectionModel().getSelectedItem();
//            re.setNom_Medicament(Nom_Medicament);
//            re.setDose(dose);
//            re.setFrquence(frequence);
//            re.setDate_creation(date_creation);
//
//            sa.modifier(re);
//            sa.getAll();
////            Stage stage = (Stage) btnValider1.getScene().getWindow();
////    stage.setResult(ordonnance);
////    stage.close();
//        }
//
//    }
    }
}
