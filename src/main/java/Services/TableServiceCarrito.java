package Services;

import Beans.ReplyBean;

/**
 *
 * @author Kysuke
 */

public interface TableServiceCarrito {

    public ReplyBean add() throws Exception;

    public ReplyBean remove() throws Exception;
}
