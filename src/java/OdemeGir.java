
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Busra
 */
@ManagedBean(name="odemegir", eager=true)
public class OdemeGir {

    public String tc;

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

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
    public String tarih;
    public String fiyat;

    
    public String odemeGir() throws SQLException {
        Connection baglanti = null;
        PreparedStatement statement = null;
        baglanti = DriverManager.getConnection("jdbc:derby://localhost:1527/iski", "iski", "iski");
        statement = baglanti.prepareStatement("INSERT INTO gecmis_odemeler values(?,?,?)");
        statement.setString(1, tc);
        statement.setString(2, tarih);
        statement.setString(3, fiyat);
        int sonuc=statement.executeUpdate();
        if(sonuc==1)
        {
            return "/adminIndex.xhtml";
        }
            
        return "/kayit.xhtml";
               
    }
}
