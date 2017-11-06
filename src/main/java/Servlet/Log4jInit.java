package Servlet;

import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Kysuke
 */

public class Log4jInit extends HttpServlet {

    @Override
    public void init() {
 
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("log4j-carrito-server");
 
        if (file != null) {
            PropertyConfigurator.configure(prefix + file);
            System.out.println("Log4J Logging started: " + prefix + file);
        } else {
            System.out.println("Log4J Is not configured for your Application: " + prefix + file);
        }
    }
    
}
