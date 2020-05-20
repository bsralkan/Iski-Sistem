
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.sql.rowset.CachedRowSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Busra
 */


@ManagedBean
@Named(value="kayit")
@RequestScoped
//@SessionScoped
public class kayit {

    private String tc;
    private String ad;
    private String soyad;
    private String sifre;
    private String mail;
    private String telefon;
    private String ilce;
    
    
     public String kaydet() throws SQLException {
        Connection baglanti = null;
        PreparedStatement statement = null;
        baglanti = DriverManager.getConnection("jdbc:derby://localhost:1527/iski", "iski", "iski");
        statement = baglanti.prepareStatement("INSERT INTO musteri values(?,?,?,?,?,?,?)");
        statement.setString(1, tc);
        statement.setString(2, ad);
        statement.setString(3, soyad);
        statement.setString(4, mail);
        statement.setString(5, telefon);
        statement.setString(6, sifre);
        statement.setString(7, ilce);
        int sonuc=statement.executeUpdate();
        if(sonuc==1)
        {
            return "/giris.xhtml";
        }
            
        return "/kayit.xhtml";
               
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }
    
    private List<String> ilceler = new ArrayList<>();

    public List<String> getIlceler() throws SQLException {
        String url = "jdbc:derby://localhost:1527/iski";
        String user = "iski";
        String password = "iski";
        Connection con = DriverManager.getConnection(url, user, password);
        if (con == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
         try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ilce ORDER BY ilce ");
            CachedRowSet rs = new com.sun.rowset.CachedRowSetImpl();
            rs.populate(ps.executeQuery());
            while(rs.next()){
                ilceler.add(rs.getString("ilce"));
            }
            return ilceler;
        } finally {
            con.close();

        }
        
    }

    public void setIlceler(List<String> ilceler) {
        this.ilceler = ilceler;
    }

 

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    
  
    
    

}
