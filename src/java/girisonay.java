
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Busra
 */

public class girisonay {

    public static boolean onay(String tc, String sifre) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/iski", "iski", "iski");
            st = con.prepareStatement("select*from musteri where tc=? and sifre=? ");
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

}
