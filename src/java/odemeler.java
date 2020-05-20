

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
 */
@ManagedBean
public class odemeler {
   

    public ResultSet getOdemeler() throws SQLException {
        String url = "jdbc:derby://localhost:1527/iski";
        String user = "iski";
        String password = "iski";
        Connection con = DriverManager.getConnection(url, user, password);
        if (con == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        try {
            
            //PreparedStatement ps = con.prepareStatement("SELECT tc,tarih,fiyat FROM gecmis_odemeler where tc=?");
            PreparedStatement ps = con.prepareStatement("SELECT musteri.tc, musteri.ad, musteri.soyad, gecmis_odemeler.fiyat, "
                    + "gecmis_odemeler.tarih FROM musteri JOIN gecmis_odemeler ON musteri.tc=gecmis_odemeler.tc WHERE musteri.tc=?");
            ps.setString(1, giris.tc);
            CachedRowSet rs = new com.sun.rowset.CachedRowSetImpl();
            rs.populate(ps.executeQuery());
            return rs;
        } finally {
            con.close();

        }
    }
}
