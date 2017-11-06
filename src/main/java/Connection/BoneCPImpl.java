package Connection;

/**
 *
 * @author Kysuke
 */

import Helper.ConnectionClassHelper;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.Connection;
import java.sql.SQLException;
 
public class BoneCPImpl implements ConnectionInterface {
 
    private BoneCP connectionPool = null;
 
    @Override
    public Connection newConnection() {
        Connection c = null;
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl(ConnectionClassHelper.getConnectionChain());
        config.setUsername(ConnectionClassHelper.getDatabaseLogin());
        config.setPassword(ConnectionClassHelper.getDatabasePassword());
        config.setMinConnectionsPerPartition(1);
        config.setMaxConnectionsPerPartition(3);
        config.setPartitionCount(1);
        try {
            connectionPool = new BoneCP(config);
        } catch (SQLException ex) {
        }
        try {
            c = connectionPool.getConnection();
        } catch (SQLException ex) {
        }
        return c;
    }
 
    @Override
    public void disposeConnection() {
        if (connectionPool != null) {
            connectionPool.close();
        }
    }
}