package Services;

import Beans.ReplyBean;

/**
 *
 * @author Kysuke
 */

public interface TableServiceInterface {

    public ReplyBean get() throws Exception;

    public ReplyBean set() throws Exception;

    public ReplyBean remove() throws Exception;
}
