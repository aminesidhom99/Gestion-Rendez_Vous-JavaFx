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
import Services.ServiceDoctor;
import Services.ServiceRednezVous;
import Services.ServicetypeRDV;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ChoixBoxController implements Initializable {
     private int idDocteur;

    @FXML
    private ChoiceBox<Typeappoinment> choixType;

    private List<Typeappoinment> types = new ArrayList<>();
    @FXML
    private ChoiceBox<String> choixcat;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceDoctor serviceDoctor = new ServiceDoctor();
Doctor doctor = serviceDoctor.GetDoctorById(idDocteur);
setIdDocteur(doctor.getId());
        List<String> modes = new ArrayList<>();
modes.add("En ligne");
modes.add("Présentielle");
choixcat.getItems().addAll(modes);

choixcat.setOnAction(event -> {
    String selectedMode = choixcat.getSelectionModel().getSelectedItem();
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

        // Définition de l'action à effectuer lorsque l'utilisateur sélectionne un type
        choixType.setOnAction(event -> {
            Typeappoinment selectedType = choixType.getSelectionModel().getSelectedItem();
            int selectedTypeId = selectedType.getId();
            System.out.println("Type sélectionné : " + selectedType.getNomtype() + ", id : " + selectedTypeId);
        });
    }
     public void setIdDocteur(int id) {
        this.idDocteur = id;
        // utiliser l'id du docteur pour effectuer des opérations nécessaires
    }

 @FXML
     private void ajouterRendezVous(ActionEvent event) throws SQLException {
       /*  Appointment p = new Appointment();
         ServiceDoctor serviceDocteur = new ServiceDoctor();
       Doctor doctor =serviceDocteur.GetDoctorById(idDocteur);
        p.setAppointment_date(datedebut.getValue());
         Date date_cours = Date.valueOf(localDate.toString());
        
        ServicetypeRDV sp =new ServicetypeRDV();
        sp.ajouter_reservation_cov(p);*/
        Typeappoinment selectedType = choixType.getSelectionModel().getSelectedItem();
    String selectedMode = choixcat.getSelectionModel().getSelectedItem();
    Date selectedStartDate = Date.valueOf(datedebut.getValue());
    Date selectedEndDate = Date.valueOf(datefin.getValue());
     Appointment appointment = new Appointment();
     User user = new User("nom", "prenom", "abbes525@gmail.com", 2);
     appointment.setUser(user);
     System.out.println(idDocteur);
    appointment.setDoctor(new Doctor(idDocteur));
    appointment.setType(selectedType);
    appointment.setCategorie(selectedMode);
    appointment.setAppointment_date(selectedStartDate);
    appointment.setDatefin(selectedEndDate);
    appointment.setApproved(false);
     ServiceRednezVous service = new ServiceRednezVous();
   // service.ajouter(appointment);
   // System.out.println("Le rendez-vous a été ajouté avec succès !");
     String recipient = "mohamedamine.sidhom@esprit.tn";
                 try {
                 utils.Mail.envoyer(recipient);
                 System.out.println("Le message a été envoyé avec succès.");
                 } catch (Exception e) {
                 System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
                 e.printStackTrace();}
             service.ajouter(appointment);
            
            JOptionPane.showMessageDialog(null,"Succés De Création ");
        
        }
    
    
    }

    

