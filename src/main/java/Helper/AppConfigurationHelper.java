package Helper;

import Connection.BoneCPImpl;
import Connection.ConnectionInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Kysuke
 */

public class AppConfigurationHelper {
    
    public static int getJsonMsgDepth() {
        return 1;
    }
    
    public static ConnectionInterface getSourceConnection() throws Exception {
        ConnectionInterface oDataConnectionSource = new BoneCPImpl();
        return oDataConnectionSource;
    }

    public static Gson getGson() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("dd/MM/yyyy");
        Gson oGson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        return oGson;
    }
}
