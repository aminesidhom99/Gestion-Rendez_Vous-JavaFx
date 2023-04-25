/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Appointment;
import Entities.Doctor;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amine
 */
public class ServiceDoctor implements IServiceDoctors{
    
     Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;
    private Appointment p;

    public ServiceDoctor() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
       public List<Doctor> readAlldoctors() throws SQLException {
    List<Entities.Doctor> doctors = new ArrayList<>();
    String req = "SELECT * FROM Doctor";
    ResultSet rs = ste.executeQuery(req);
    while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String username = rs.getString("username");
        String email = rs.getString("email");
        Entities.Doctor doctor = new Entities.Doctor(id, name, username, email);
        doctors.add(doctor);
    }
    return doctors;
       }
        public boolean supprime_reservation_cov(int id ) throws SQLException {
        String req = "DELETE FROM `Doctor` WHERE id = " + id + ";";
     String req2 = "UPDATE covoiturage SET nbrplace= nbrplace+1 WHERE id_cov =" + ";";
        PreparedStatement pre = con.prepareStatement(req2);

        if ((ste.executeUpdate(req)) == 1) {
                    pre.executeUpdate(); 

            return true;
                

        }

        return false;
    }
    public Doctor GetDoctorById(int Id) {
     Doctor h =new Doctor();
     String req = "SELECT * FROM Doctor where `id` ="+Id+";";
     try {
      
       ResultSet rs = ste.executeQuery(req); 
       while(rs.next()){
            h.setId(rs.getInt(1));
              h.setName(rs.getString(2));
              h.setUsername(rs.getString(3));
              h.setEmail(rs.getString(4));
             
         }
       } catch (SQLException ex) {
           Logger.getLogger(ServiceDoctor.class.getName()).log(Level.SEVERE, null, ex);
       }
    return h ;
    }
}




