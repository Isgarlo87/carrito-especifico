package Static;
import org.apache.log4j.Logger;

/**
 *
 * @author Kysuke
 */

public class Log4jStatic {
    public static void infoLog(String strMessage) {
        Logger log = Logger.getLogger("carrito-server");
        log.info(strMessage);
    }
 
    public static void errorLog(String strMessage, Exception e) {
        Logger log = Logger.getLogger("carrito-server");
        log.error(strMessage, e);
    }
 
    public static void errorLog(String strMessage) {
        Logger log = Logger.getLogger("carrito-server");
        log.error(strMessage);
    }
    
}