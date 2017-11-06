package Services;

import Beans.ReplyBean;

/**
 *
 * @author Kysuke
 */

public interface ViewServiceInterface {

    public ReplyBean getpage() throws Exception;

    public ReplyBean getcount() throws Exception;
}
