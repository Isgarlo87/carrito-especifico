package Dao;

import Beans.PedidoBean;
import Beans.UsuarioBean;
import Helper.AppConfigurationHelper;
import Helper.EncodingUtilHelper;
import Helper.FilterBeanHelper;
import Helper.SqlBuilder;
import Static.Log4jStatic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author Kysuke
 */

public class PedidoDao implements DaoTableInterface<PedidoBean>, DaoViewInterface<PedidoBean> {

    // VARIABLES
    private final String strTable = "pedido";
    private String strSQL = "SELECT * FROM " + strTable + " WHERE 1=1 ";
    private Connection oConnection = null;

    // CONSTRUCTOR
    public PedidoDao(Connection oPooledConnection) {
        oConnection = oPooledConnection;
    }

    /**
     * @param intExpand
     * @Método get: Obtiene datos sobre un usuario, desde una base de datos, y
     * crea un objeto "Bean" a partir de dichos datos.
     * @param oBean
     * @return UsuarioBean
     * @throws Exception
     */
    @Override
    public PedidoBean get(PedidoBean oBean, int intExpand) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "select * from " + strTable + " WHERE 1=1 ";
        strSQL += " AND id=" + oBean.getId();
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                oBean.setId(oResultSet.getInt("id"));
                oBean.setFecha(oResultSet.getDate("fecha"));
                oBean.setId_usuario(oResultSet.getInt("id_usuario"));
                if (intExpand > 0) {
                    UsuarioBean oUsuario = new UsuarioBean();
                    UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
                    oUsuario.setId(oBean.getId_usuario());
                    oUsuario = oUsuarioDao.get(oUsuario, --intExpand);
                    oBean.setObj_usuario(oUsuario);
                }
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }

        }
        return oBean;
    }

    /**
     * @Método set: Crea o actualiza un registro de usuario en una base de datos
     * y devuelve un código en función de si la consulta ha tenido éxito o no.
     * @param oBean
     * @return Integer
     * @throws Exception
     */
    @Override
    public Integer set(PedidoBean oBean) throws Exception {
        PreparedStatement oPreparedStatement = null;
        Integer iResult = 0;
        Boolean insert = true;
        try {
            if (oBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable;
                strSQL += "( ";
                strSQL += "fecha, ";
                strSQL += "id_usuario ";
                strSQL += ") ";
                strSQL += " VALUES ";
                strSQL += "( ";
                strSQL += EncodingUtilHelper.stringifyAndQuotate(oBean.getFecha()) + ",";
                strSQL += oBean.getId_usuario();
                strSQL += ")";
            } else {
                insert = false;
                strSQL = "UPDATE " + strTable;
                strSQL += " SET ";
                strSQL += "fecha=" + EncodingUtilHelper.stringifyAndQuotate(oBean.getFecha()) + ", ";
                strSQL += "id_usuario= " + oBean.getId_usuario();
                strSQL += " WHERE id=" + oBean.getId();
            }
            oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            iResult = oPreparedStatement.executeUpdate();
            if (iResult < 1) {
                throw new Exception();
            }
            if (insert) {
                ResultSet oResultSet = oPreparedStatement.getGeneratedKeys();
                oResultSet.next();
                iResult = oResultSet.getInt(1);
            }

        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

    /**
     * @Método remove: Elimina un registro de usuario de una base de datos y
     * devuelve un código en función de si la consulta ha tenido éxito o no.
     * @param id
     * @return Integer
     * @throws Exception
     */
    @Override
    public Integer remove(Integer id) throws Exception {
        PreparedStatement oPreparedStatement = null;
        Integer iResult = 0;

        try {
            strSQL = "DELETE FROM " + strTable + " WHERE id=? ";
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            iResult = oPreparedStatement.executeUpdate();
            if (iResult < 1) {
                throw new Exception();
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

    /**
     * @param alFilter
     * @Método getCount: Obtiene el número de registros de la tabla usuario de
     * una base de datos.
     * @return Long
     * @throws Exception
     */
    @Override
    public Long getcount(ArrayList<FilterBeanHelper> alFilter) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "SELECT COUNT(*) FROM " + strTable;
        strSQL += " WHERE 1=1 " + SqlBuilder.buildSqlFilter(alFilter);
        Long iResult = 0L;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                iResult = oResultSet.getLong("COUNT(*)");
            } else {
                throw new Exception("PedidoDao getCount error");
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;

    }

    /**
     *
     * @param intRegsPerPag
     * @param intPage
     * @param hmOrder
     * @param alFilter
     * @return ArrayList<PedidoBean>
     * @throws Exception
     */
    @Override
    public ArrayList<PedidoBean> getpage(int intRegsPerPag, int intPage, LinkedHashMap<String, String> hmOrder, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        String strSQL1 = strSQL;
        strSQL1 += SqlBuilder.buildSqlFilter(alFilter);
        strSQL1 += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL1 += SqlBuilder.buildSqlLimit(this.getcount(alFilter), intRegsPerPag, intPage);
        ArrayList<PedidoBean> aloBean = new ArrayList<>();
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL1);
            oResultSet = oPreparedStatement.executeQuery(strSQL1);
            while (oResultSet.next()) {
                aloBean.add(this.get(new PedidoBean(oResultSet.getInt("id")), AppConfigurationHelper.getJsonMsgDepth()));
                //aloBean.add(this.get(new UsuarioBean(oResultSet.getInt("id"))));
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return aloBean;
    }

    public Long getCountxusuario(int id_usuario) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "SELECT COUNT(*) FROM " + strTable;
        strSQL += " WHERE id_usuario=" + id_usuario;
        Long iResult = 0L;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                iResult = oResultSet.getLong("COUNT(*)");
            } else {
                throw new Exception("PedidoDao getCount error");
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

    public ArrayList<PedidoBean> getPagexusuario(int intRegsPerPag, int intPage, LinkedHashMap<String, String> hmOrder, ArrayList<FilterBeanHelper> alFilter, int id_usuario) throws Exception {
        String strSQL1 = strSQL;
        strSQL1 += " and id_usuario=" + id_usuario + " ";
        strSQL1 += SqlBuilder.buildSqlFilter(alFilter);
        strSQL1 += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL1 += SqlBuilder.buildSqlLimit(this.getcount(alFilter), intRegsPerPag, intPage);
        ArrayList<PedidoBean> aloBean = new ArrayList<>();
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL1);
            oResultSet = oPreparedStatement.executeQuery(strSQL1);
            while (oResultSet.next()) {
                aloBean.add(this.get(new PedidoBean(oResultSet.getInt("id")), AppConfigurationHelper.getJsonMsgDepth()));
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jStatic.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return aloBean;
    }
}
