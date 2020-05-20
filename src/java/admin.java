
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.sql.rowset.CachedRowSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Busra
 * 
 */

@ManagedBean
public class admin {
    public String tc;
    public String sifre;
    public String tarih;
    public String fiyat;
  
    
    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
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
    
    public String girisYap() throws SQLException {

        boolean onaylandimi = adminOnay(tc, sifre);
        if (onaylandimi) {
            return "/adminIndex.xhtml";
        } else {
            return "/admin.xhtml";
        }

    }
    
       
    public static boolean adminOnay(String tc, String sifre) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/iski", "iski", "iski");
            st = con.prepareStatement("SELECT*FROM admin WHERE tc=? AND sifre=? ");
            st.setString(1, tc);
            st.setString(2, sifre);
     
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("login'de error oldu" + ex.getMessage());
            return false;
        } finally {
            con.close();
        }
        return false;

    }
    
    public ResultSet getBorcluMusteriler() throws SQLException {
        String url = "jdbc:derby://localhost:1527/iski";
        String user = "iski";
        String password = "iski";
        Connection con = DriverManager.getConnection(url, user, password);
        if (con == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM musteri WHERE tc IN (SELECT tc FROM borclar)");
            CachedRowSet rs = new com.sun.rowset.CachedRowSetImpl();
            rs.populate(ps.executeQuery());
            return rs;
        } finally {
            con.close();

        }
    }
    public ResultSet getMusteriDagilimi() throws SQLException {
        String url = "jdbc:derby://localhost:1527/iski";
        String user = "iski";
        String password = "iski";
        Connection con = DriverManager.getConnection(url, user, password);
        if (con == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            
            PreparedStatement ps = con.prepareStatement("SELECT ilce, COUNT(*) as count FROM musteri GROUP BY ilce");            
            CachedRowSet rs = new com.sun.rowset.CachedRowSetImpl();
            rs.populate(ps.executeQuery());
            return rs;
        } finally {
            con.close();

        }
    }
    
    
    
    
}
