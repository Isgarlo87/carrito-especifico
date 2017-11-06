package Connection;

/**
 *
 * @author Kysuke
 */

import java.sql.Connection;
import java.sql.SQLException;
 
 
public class JdbcImpl implements ConnectionInterface {
 
    private Connection con;
 
    @Override
    public Connection newConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlOdbc = "jdbc:mysql://127.0.0.1:3306/usuariodb";
            con = (java.sql.DriverManager.getConnection(urlOdbc, "root", "admin"));
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se ha podido establecer la conexion" + e.getMessage());
        }
    }
 
    @Override
    public void disposeConnection() throws Exception {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido cerrar la conexion" + e.getMessage());
        }
    }
 
}