package Connection;

import Helper.ConnectionClassHelper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Kysuke
 */

public class HikariCPImpl implements ConnectionInterface {

    private HikariDataSource connectionPool = null;

    @Override
    public Connection newConnection() throws Exception {

        Connection c = null;
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(ConnectionClassHelper.getConnectionChain());
        config.setUsername(ConnectionClassHelper.getDatabaseLogin());
        config.setPassword(ConnectionClassHelper.getDatabasePassword());

        connectionPool = new HikariDataSource(config);

        try {
            c = connectionPool.getConnection();
        } catch (SQLException ex) {
            
        }
        return c;
    }

    @Override
    public void disposeConnection() throws Exception {
        if (connectionPool != null) {
            connectionPool.close();
        }
    }
}
