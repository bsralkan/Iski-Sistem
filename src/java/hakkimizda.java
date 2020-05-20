

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Busra
 */

@ManagedBean(name="hakkimizda",eager=true)
@SessionScoped
public class hakkimizda {
  
    public ResultSet getHakkimizda() throws SQLException{
        String url = "jdbc:derby://localhost:1527/iski";
        String user = "iski";
        String password = "iski";
        Connection con = DriverManager.getConnection(url, user, password);
        if (con == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
         try {
            PreparedStatement ps = con.prepareStatement("SELECT baslik,yazi FROM hakkimizda");
            CachedRowSet rs = new com.sun.rowset.CachedRowSetImpl();
            rs.populate(ps.executeQuery());
            return rs;
        } finally {
            con.close();

        }
        
    }
    
    
    

    
}
