
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Appointment;
import Entities.Typeappoinment;
import Entities.Doctor;
import Entities.User;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author amine
 */
public class ServiceRednezVous implements IServiceRendezVous<Appointment>{
 
    Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;
    private Appointment p;

    public ServiceRednezVous() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
  
    
    
    // ajouter un rendez vous 
    public boolean ajouter(Appointment c) throws SQLException {
    boolean verif = true;

    try {
        String req = "INSERT INTO `Appointment`(`id`, `user_id`, `doctor_id`, `appointment_date`, `datefin`, `type_id`, `categorie`, `approved`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, c.getId());
        pre.setInt(2, c.getUser().getId());
        pre.setInt(3, c.getDoctor().getId());
        pre.setDate(4,c.getAppointment_date());
        pre.setDate(5,c.getDatefin());
      
        pre.setInt(6, c.getType().getId());
        pre.setString(7, c.getCategorie());
        pre.setBoolean(8, c.isApproved());

        int rowsInserted = pre.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Appointment ajouté avec succès !");
        }
    } catch (SQLException e) {
        System.out.println(e);
        verif = false;
    }

    return verif;
}
    
    //// afficher la liste de tous les rendez vous 
    public List<Appointment> readAll() throws SQLException {
    List<Appointment> appointmentList = new ArrayList<>();
    String req = "SELECT * FROM Appointment";
    ResultSet res = ste.executeQuery(req);
    while (res.next()) {
        Appointment appointment = new Appointment();
      appointment.setId(res.getInt("id"));
      appointment.setAppointment_date(res.getDate("appointment_date"));
        appointment.setDatefin(res.getDate("datefin"));
        appointment.setCategorie(res.getString("categorie"));
        appointment.setApproved(false);
       
        int userId = res.getInt("user_id");
        int doctorId = res.getInt("doctor_id");
        int typeId = res.getInt("type_id");
    

        ServiceDoctor coursServices = new ServiceDoctor();
            Doctor cours = coursServices.GetDoctorById(doctorId);
            ServicetypeRDV typeServices = new ServicetypeRDV();
            Typeappoinment typee = typeServices.GetTypeById(typeId);
            ServiceUser userServices = new ServiceUser();
            User userr = userServices.GetUserById(userId);
        //Appointment appointment = new Appointment(id, new User(userId), new Doctor(doctorId), new Typeappoinment(typeId), appointmentDate, dateFin, category, approved);
       appointment.setUser(userr);
       appointment.setDoctor(cours);
       appointment.setType(typee);
        appointmentList.add(appointment);
    }
    return appointmentList;
}
    
    
    
    
    
    
    
    
    
    
////// update tekhdem baad jointure 
public void update(Appointment c) throws SQLException {
    String req="UPDATE `Appointment` SET `user_id`=?,`doctor_id`=?,`appointment_date`=?,`datefin`=?,`type_id`=?,`categorie`=? ,`approved`=? where `id` ="+c.getId()+"";

    PreparedStatement pre=con.prepareStatement(req);

    pre.setInt(1, c.getUser().getId());
    pre.setInt(2, c.getDoctor().getId());
    pre.setDate(3,c.getAppointment_date());
        pre.setDate(4,c.getDatefin());
    pre.setInt(5, c.getType().getId());
    pre.setString(6, c.getCategorie());
    pre.setBoolean(7, c.isApproved());

    pre.executeUpdate();
    System.out.println("rendez vous modifié !");
}
    
    
    public boolean supprimer(int id) throws SQLException {
    String req = "DELETE FROM `Appointment` WHERE id = ?;";
    PreparedStatement pre = con.prepareStatement(req);
    pre.setInt(1, id);

    int rowsDeleted = pre.executeUpdate();
    return rowsDeleted > 0;
}
    
    
    
    
    




  











   /* @Override
    public List<Appointment> findById(int id) throws SQLException {
        ArrayList<Appointment> listper = new ArrayList<>();
        String req = "select * FROM Appointment where user_id = " + id + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
      int idr = res.getInt(1);
            int user_id = res.getInt(2);
            int doctor_id = res.getInt(3);
           LocalDateTime appointment_date = res.getTimestamp(4).toLocalDateTime();
            LocalDateTime datefin = res.getTimestamp(5).toLocalDateTime();
            int type_id = res.getInt(6);
            String categorie = res.getString(7);
            Boolean approved = res.getBoolean(8);
       Appointment c = new Appointment(idr, user_id, doctor_id , appointment_date, datefin ,type_id , categorie , approved );
  listper.add(c);
    }
        
        return listper;
    }*/
    
    
    /*  public List<Appointment> sortbydate() throws SQLException {
        ArrayList<Appointment> listper = new ArrayList<>();

        String req = "select * from Appointment ORDER by appointment_date";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
          int idr = res.getInt(1);
            int user_id = res.getInt(2);
            int doctor_id = res.getInt(3);
            LocalDateTime appointment_date = res.getTimestamp(4).toLocalDateTime();
            LocalDateTime datefin = res.getTimestamp(5).toLocalDateTime();
            int type_id = res.getInt(6);
            String categorie = res.getString(7);
            Boolean approved = res.getBoolean(8);
             Appointment c = new Appointment(idr, user_id, doctor_id , appointment_date, datefin ,type_id , categorie , approved );
  listper.add(c);
        }
        return listper;
    }*/

   
    
}

