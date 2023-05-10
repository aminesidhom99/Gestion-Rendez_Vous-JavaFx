package Services;

import Entities.Medicament;
import Utils.DataSource;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;


public class ServiceMedicament implements IService<Medicament>{
    Connection cnx;
    public  ServiceMedicament(){
        cnx =DataSource.getInstance().getConnection();
    }
    @Override
    public void ajouter(Medicament t) {
         try {
            String qry = "INSERT INTO `medicament`( `nom`,`categorie`,`description`,`image`,`prix`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(qry);
            String img="imgs\\imgs\\"+t.getImage();
            ps.setString(1, t.getNom());
            ps.setString(2, t.getCategorie());
            ps.setString(3, t.getDescription());
            ps.setString(4, img);
            ps.setFloat(5, t.getPrix());
            ps.executeUpdate();
            System.out.println("haithem 9al jawek behi ");
        } catch (SQLException ex) {
            System.out.println("haithem 9al mouch jawek behi ");

            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Medicament> afficher() {
        List<Medicament> medicaments = new ArrayList<>();
         
        try {
             String req ="SELECT * FROM `medicament` ";
            
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                Medicament m = new Medicament();
                m.setId(rs.getInt(1));
                m.setNom(rs.getString(2));
                m.setDescription(rs.getString(4));
                m.setCategorie(rs.getString(3));
                m.setImage(rs.getString(5));
                m.setPrix(rs.getFloat(6));
                medicaments.add(m);
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

   return medicaments;
    }

    @Override
    public void modifier(Medicament t) {
        try {
           String req = " UPDATE `medicament` SET `id` = '" +t.getId()+ "', `Nom` = '" +t.getNom() +" ', `Categorie` = ' "+ t.getCategorie() + " ', `Description` = ' "+ t.getDescription() + " ', `Prix` = ' "+ t.getPrix() +  "' WHERE `medicament`.`id` = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Medicament updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
         try {
            String req = "DELETE FROM `medicament` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Medicament deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Medicament> chercher(String ch) {
                List<Medicament> medicaments = new ArrayList<>();
         
        try {
             String req ="SELECT * FROM `medicament` where Nom LIKE '"+ch+"%' ";
            
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                Medicament m = new Medicament();
                m.setId(rs.getInt(1));
                m.setNom(rs.getString(2));
                m.setDescription(rs.getString(4));
                m.setCategorie(rs.getString(3));
                m.setImage(rs.getString(5));
                m.setPrix(rs.getFloat(6));
                medicaments.add(m);
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

   return medicaments;

    }
    public Medicament getbyid(int id){
        Medicament m = new Medicament();
         try {
             String req ="SELECT * FROM `medicament` where id = '"+id+"%' ";
            
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                
                m.setId(rs.getInt(1));
                m.setNom(rs.getString(2));
                m.setDescription(rs.getString(4));
                m.setCategorie(rs.getString(3));
                m.setImage(rs.getString(5));
                m.setPrix(rs.getFloat(6));
            }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return m;
    }
    public void generateQR(int id) throws WriterException, IOException{
        Medicament m=getbyid(id);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String info="Description : "+m.getDescription()+"\n"+"Prix : "+m.getPrix();
        String medicamentData = info;
        int width = 300;
        int height = 300;
        BitMatrix bitMatrix = qrCodeWriter.encode(medicamentData, BarcodeFormat.QR_CODE, width, height);
        BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
           for (int y = 0; y < height; y++) {
              qrImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
             }
        }
        String outputDir = "imgs/QRcodes/";
        File directory = new File(outputDir);
        if (!directory.exists()){
            directory.mkdirs();
        }

        String outputFilePath = outputDir + m.getNom()+"_qr.png";
        ImageIO.write(qrImage, "png", new File(outputFilePath));

    }
}
