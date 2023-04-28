/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import Entity.User;
import Entity.doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Myconn;
/**
 *
 * @author HP
 */
public class crud {
     Connection cnx;
     
      public  crud(){
        cnx =Myconn.getInstance().getcnx();
    }
    
    public void ajouter(User t) {
    try {
        String qry = "INSERT INTO `user`( `FirstName`, `LastName`, `Email`, `Adresse`, `Age`, `Cin`, `Password`, `Roleperm`) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(qry);
        ps.setString(1, t.getFirstName());
        ps.setString(2, t.getLastName());
        ps.setString(3, t.getEmail());
        ps.setString(4, t.getAdresse());
        ps.setInt(5, t.getAge());
        ps.setInt(6, t.getCin());
        ps.setString(7, t.getPassword());
        ps.setString(8, t.getRoleperm());

        ps.executeUpdate();
        System.out.println("créé avec succès ");
    } catch (SQLException ex) {
        System.out.println("merci de vérifier et valider vos coordonnées ");

        System.out.println(ex.getMessage());
    }
}
public boolean login(String password, String email){
    
         try {
            String requete = "SELECT count(*) FROM user where password=? AND email=? ";
            
            PreparedStatement preparedStatement = Myconn.getInstance().getcnx().prepareStatement(requete);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
       
    }
public ResultSet getUserInfo(String username) {
        ResultSet rs = null;
        try {
            Connection cnx = Myconn.getInstance().getcnx();
            String req = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, username);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
   public List<User> afficher() {
    List<User> users = new ArrayList<>();
    try {
        String req = "SELECT * FROM `user` ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt(1));
            u.setFirstName(rs.getString(2));
            u.setLastName(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setAdresse(rs.getString(5));
            u.setAge(rs.getInt(6));
            u.setCin(rs.getInt(7));
            u.setPassword(rs.getString(8));
            u.setRoleperm(rs.getString(9));
            users.add(u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return users;
}






    
     public void modifier(User t) {
    try {
        String qry = "UPDATE `user` SET `FirstName`=?,`LastName`=?,`Email`=?,`Address`=?,`Age`=?,`CIN`=?,`Password`=?,`RolePerm`=? WHERE `id`=?";
        PreparedStatement ps = cnx.prepareStatement(qry);
        ps.setString(1, t.getFirstName());
        ps.setString(2, t.getLastName());
        ps.setString(3, t.getEmail());
        ps.setString(4, t.getAdresse());
        ps.setInt(5, t.getAge());
        ps.setInt(6, t.getCin());
        ps.setString(7, t.getPassword());
        ps.setString(8, t.getRoleperm());
        ps.setInt(9, t.getId());

        ps.executeUpdate();
        System.out.println("modifié avec succès ");
    } catch (SQLException ex) {
        System.out.println("merci de vérifier et valider vos coordonnées ");
        System.out.println(ex.getMessage());
    }
}

    public void supprimer(int id) {
        try {
            String qry = "DELETE FROM `user` WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("supprimé avec succès ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void ajouterd(doctor d) {
        try {
            String qry = "INSERT INTO `doctor`(`FirstName`, `LastName`, `Email`, `Address`, `Age`, `Cin`, `Password`, `RolePerm`, `Diplome`, `Specialite`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setString(1, d.getFirstName());
            ps.setString(2, d.getLastName());
            ps.setString(3, d.getEmail());
            ps.setString(4, d.getAddress());
            ps.setInt(5, d.getAge());
            ps.setInt(6, d.getCin());
            ps.setString(7, d.getPassword());
            ps.setString(8, d.getRoleperm());
            ps.setString(9, d.getDiplome());
            ps.setString(10, d.getSpecialite());

            ps.executeUpdate();
            System.out.println("Doctor ajouté avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout du doctor: " + ex.getMessage());
        }
    }

    public List<doctor> afficherd() {
        List<doctor> doctors = new ArrayList<>();

        try {
            String req ="SELECT * FROM `doctor` ";

            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                doctor d = new doctor();
                d.setId(rs.getInt(1));
                d.setFirstName(rs.getString(2));
                d.setLastName(rs.getString(3));
                d.setEmail(rs.getString(4));
                d.setAddress(rs.getString(5));
                d.setAge(rs.getInt(6));
                d.setCin(rs.getInt(7));
                d.setPassword(rs.getString(8));
                d.setRoleperm(rs.getString(9));
                d.setDiplome(rs.getString(10));
                d.setSpecialite(rs.getString(11));

                doctors.add(d);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'affichage des doctors: " + ex.getMessage());
        }

        return doctors;
    }

    public void modifierd(doctor d) {
        try {
            String qry = "UPDATE `doctor` SET `FirstName`=?,`LastName`=?,`Email`=?,`Address`=?,`Age`=?,`Cin`=?,`Password`=?,`RolePerm`=?,`Diplome`=?,`Specialite`=? WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setString(1, d.getFirstName());
            ps.setString(2, d.getLastName());
            ps.setString(3, d.getEmail());
            ps.setString(4, d.getAddress());
            ps.setInt(5, d.getAge());
            ps.setInt(6, d.getCin());
            ps.setString(7, d.getPassword());
            ps.setString(8, d.getRoleperm());
            ps.setString(9, d.getDiplome());
            ps.setString(10, d.getSpecialite());
            ps.setInt(11, d.getId());

            ps.executeUpdate();
            System.out.println("Doctor modifié avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du doctor: " + ex.getMessage());
        }
    }
public void supprimerDoctor(int id) {
    try {
        String qry = "DELETE FROM `doctor` WHERE `id`=?";
        PreparedStatement ps = cnx.prepareStatement(qry);
        ps.setInt(1, id);

        ps.executeUpdate();
        System.out.println("Doctor supprimé avec succès ");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}








}
