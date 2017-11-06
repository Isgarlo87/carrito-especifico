package Connection;

import Helper.ConnectionClassHelper;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author Kysuke
 */

public class DbcpImpl implements ConnectionInterface {
    
    BasicDataSource basicDataSource = null;
    
    @Override
    public Connection newConnection() throws Exception {
        Connection con = null;
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername(ConnectionClassHelper.getDatabaseLogin());
        basicDataSource.setPassword(ConnectionClassHelper.getDatabasePassword());
        basicDataSource.setUrl(ConnectionClassHelper.getConnectionChain());


        return con;
    }

    @Override
    public void disposeConnection() throws Exception {
        try {
            if (basicDataSource != null) {
                basicDataSource.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("No se ha podido cerrar la conexion" + ex.getMessage());
        }
    }
}
