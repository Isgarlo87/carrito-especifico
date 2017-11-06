package Helper;

/**
 *
 * @author Kysuke
 */

public class EstadoHelper {
    public static enum Tipo_estado {
 
        Debug,
        Production
    };
 
    public static Tipo_estado getTipo_estado() {
        return Tipo_estado.Debug;
    }
 
    public static String getVersion() {
        return "03";
    }
 
    public static String getFecha() {
        return "17/10/2017";
    }
 
    public static String getAnyo() {
        return "2017";
    }
 
    public static String getCurso() {
        return "2017-2018";
    }
 
    public static String getAutor() {
        return "Rafael Aznar";
    }
 
    public static String getMailAutor() {
        return "rafaaznar{at}gmail{dot}com";
    }
 
    public static String getLicenciaLink() {
        return "<a href=\"https://opensource.org/licenses/MIT\">MIT License</a>";
    }
 
    public static int getDelay() {
        return 0;
    }
}
