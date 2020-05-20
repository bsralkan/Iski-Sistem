
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
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
@SessionScoped


public class giris {

    
    public static String tc;
    private String sifre;
    public static String ad;
    public String style;
    
    
    public String girisYap() throws SQLException {

        boolean onaylandimi = girisonay.onay(tc, sifre);
        if (onaylandimi) {
            return "/index.xhtml";
        } else {
            return "/kayit.xhtml";
        }

    }
  

    public String KayitOl() throws SQLException {
        return "kayit.xhtml";
    }

    public String guncelle() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/iski", "iski", "iski");
        ps = con.prepareStatement("UPDATE musteri SET sifre=? WHERE tc=?");
        ps.setString(2, tc);
        ps.setString(1, sifre);

        int sonuc = ps.executeUpdate();
        if (sonuc == 1) {
            return "/profil.xhtml";
        }

        return "/profil.xhtml";
    }
    
    

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public giris() {
        style = "height: 450px;background-color: #cccccc; width: 200px;\n"
                + "            position: initial; bottom: 50%; border: 2px solid; padding: 10px;margin: auto; display: none;";
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void loginac() {
        style = "height: 450px;background-color: #cccccc; width: 200px;\n"
                + "            position: initial; bottom: 50%; border: 2px solid; padding: 10px;margin: auto; display: block;";
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }


   
}
