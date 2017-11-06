package Services;

import Beans.ReplyBean;
import Beans.TipoUsuarioBean;
import Connection.ConnectionInterface;
import Dao.TipousuarioDao;
import Helper.AppConfigurationHelper;
import Helper.FilterBeanHelper;
import Helper.ParameterCook;
import Static.Log4jStatic;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Kysuke
 */

public class TipousuarioService implements ViewServiceInterface, EmptyServiceInterface, TableServiceInterface {

    HttpServletRequest oRequest = null;

    public TipousuarioService(HttpServletRequest request) {
        oRequest = request;
    }

    @Override
    public ReplyBean get() throws Exception {
        int id = Integer.parseInt(oRequest.getParameter("id"));
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBean oReplyBean = null;
        try {
            oPooledConnection = AppConfigurationHelper.getSourceConnection();
            oConnection = oPooledConnection.newConnection();
            TipoUsuarioBean oBean = new TipoUsuarioBean(id);
            TipousuarioDao oDao = new TipousuarioDao(oConnection);
            oBean = oDao.get(oBean, AppConfigurationHelper.getJsonMsgDepth());
            Gson oGson = new Gson();
            String strJson = oGson.toJson(oBean);
            oReplyBean = new ReplyBean(200, strJson);
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (AppConfigurationHelper.getSourceConnection() != null) {
                AppConfigurationHelper.getSourceConnection().disposeConnection();
            }
        }
        return oReplyBean;
    }

    @Override
    public ReplyBean set() throws Exception {
        String json = oRequest.getParameter("json");
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBean oReplyBean = null;
        TipoUsuarioBean oBean = new TipoUsuarioBean();
        Gson oGson = new Gson();
        oBean = oGson.fromJson(json, oBean.getClass());
        int iResult = 0;
        try {
            oPooledConnection = AppConfigurationHelper.getSourceConnection();
            oConnection = oPooledConnection.newConnection();
            TipousuarioDao oDao = new TipousuarioDao(oConnection);
            iResult = oDao.set(oBean);
            String strJson = oGson.toJson(iResult);
            oReplyBean = new ReplyBean(200, strJson);
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (AppConfigurationHelper.getSourceConnection() != null) {
                AppConfigurationHelper.getSourceConnection().disposeConnection();
            }
        }
        return oReplyBean;
    }

    @Override
    public ReplyBean remove() throws Exception {
        int id = Integer.parseInt(oRequest.getParameter("id"));
        int iResult = 0;
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBean oReplyBean = null;
        try {
            oPooledConnection = AppConfigurationHelper.getSourceConnection();
            oConnection = oPooledConnection.newConnection();
            TipousuarioDao oDao = new TipousuarioDao(oConnection);
            iResult = oDao.remove(id);
            Gson oGson = new Gson();
            String strJson = oGson.toJson(iResult);
            oReplyBean = new ReplyBean(200, strJson);
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (AppConfigurationHelper.getSourceConnection() != null) {
                AppConfigurationHelper.getSourceConnection().disposeConnection();
            }
        }
        return oReplyBean;

    }

    @Override
    public ReplyBean getcount() throws Exception {
        Long lResult;
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBean oReplyBean = null;
        String strFilter = oRequest.getParameter("filter");
        ArrayList<FilterBeanHelper> alFilter = ParameterCook.getFilterParams(strFilter);
        try {
            oPooledConnection = AppConfigurationHelper.getSourceConnection();
            oConnection = oPooledConnection.newConnection();
            TipousuarioDao oDao = new TipousuarioDao(oConnection);
            lResult = oDao.getcount(alFilter);
            Gson oGson = new Gson();
            String strJson = oGson.toJson(lResult);
            oReplyBean = new ReplyBean(200, strJson);
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (AppConfigurationHelper.getSourceConnection() != null) {
                AppConfigurationHelper.getSourceConnection().disposeConnection();
            }
        }
        return oReplyBean;
    }

    @Override
    public ReplyBean getpage() throws Exception {
        int np = Integer.parseInt(oRequest.getParameter("np"));
        int rpp = Integer.parseInt(oRequest.getParameter("rpp"));
        String strOrder = oRequest.getParameter("order");
        String strFilter = oRequest.getParameter("filter");
        LinkedHashMap<String, String> hmOrder = ParameterCook.getOrderParams(strOrder);
        ArrayList<FilterBeanHelper> alFilter = ParameterCook.getFilterParams(strFilter);
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBean oReplyBean = null;
        ArrayList<TipoUsuarioBean> aloBean = null;
        try {
            oPooledConnection = AppConfigurationHelper.getSourceConnection();
            oConnection = oPooledConnection.newConnection();
            TipousuarioDao oDao = new TipousuarioDao(oConnection);
            aloBean = oDao.getpage(rpp, np, hmOrder, alFilter);
            Gson oGson = new Gson();
            String strJson = oGson.toJson(aloBean);
            oReplyBean = new ReplyBean(200, strJson);
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (AppConfigurationHelper.getSourceConnection() != null) {
                AppConfigurationHelper.getSourceConnection().disposeConnection();
            }
        }
        return oReplyBean;
    }

}
