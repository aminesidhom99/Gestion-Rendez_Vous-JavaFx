/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.type_reclamation;
import Entities.user;
import Utils.myDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EMNA
 */
public class ServiceUser implements IService<user>{
        Connection cnx;
    
    public  ServiceUser(){
        cnx =myDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(user t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<user> afficher() {
 List<user> user = new ArrayList<>();
         
        try {
             String req ="SELECT * FROM `user` ";
            
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                user t = new user();
                t.setId(rs.getInt(1));
                t.setNom(rs.getString("nom"));
                t.setPrenom(rs.getString("prenom"));
                user.add(t);
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

   return user;        }

    @Override
    public void modifier(user t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
        public List<String> getallnames(){
        List<String> lu = new ArrayList<>();
        try {
             String req ="SELECT nom , prenom FROM `user` ";
            
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                String ch =rs.getString("nom")+" "+rs.getString("prenom");
                lu.add(ch) ;

            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lu;
    }
}
