/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidesktop;

import Entities.type_reclamation;
import Entities.reclamation;
import Entities.user;
import Services.ServiceReclamation;
import Services.ServiceTypeRec;
import Utils.myDB;
import java.sql.Timestamp;

/**
 *
 * @author EMNA
 */
    public class PiDesktop {

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
        
       //type_reclamation t1 = new type_reclamation("jahjgjkghjghjgjva");
       ServiceTypeRec st = new ServiceTypeRec();
  //      st.ajouter(t1);
       System.out.println( st.afficher());

       
reclamation r1 = new reclamation(new user(1),new type_reclamation(2),"etat@java.tn",7851426,"etat auto");
ServiceReclamation rec = new ServiceReclamation();

//rec.ajouter(r1);
System.out.println(rec.afficher());

    
}
    }
