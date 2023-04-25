
package Services;

import Entities.Appointment;
import Entities.Doctor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author amine
 *
 */
public interface IServiceDoctors {

  //  boolean ajouter(Appointment t) throws SQLException;

 //   void update(Appointment t) throws SQLException;
 //  List<Appointment> sortbydate() throws SQLException;


   // boolean supprime(int t) throws SQLException;
    List<Doctor> readAlldoctors() throws SQLException;

    //List<Appointment> findById(int id) throws SQLException;
    
}
