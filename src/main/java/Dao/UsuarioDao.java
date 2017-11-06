package Dao;

import Beans.TipoUsuarioBean;
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

public class UsuarioDao implements DaoTableInterface<UsuarioBean>, DaoViewInterface<UsuarioBean> {

    // VARIABLES
    private final String strTable = "usuario";
    private String strSQL = "SELECT * FROM " + strTable + " WHERE 1=1 ";
    private Connection oConnection = null;

    // CONSTRUCTOR
    public UsuarioDao(Connection oPooledConnection) {
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
    public UsuarioBean get(UsuarioBean oBean, int intExpand) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "select * from " + strTable + " WHERE 1=1 ";
        strSQL += " AND id=" + oBean.getId();
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                oBean.setId(oResultSet.getInt("id"));
                oBean.setDni(oResultSet.getString("dni"));
                oBean.setNombre(oResultSet.getString("nombre"));
                oBean.setPrimer_apellido(oResultSet.getString("primer_apellido"));
                oBean.setSegundo_apellido(oResultSet.getString("segundo_apellido"));
                oBean.setLogin(oResultSet.getString("login"));
                oBean.setPass(oResultSet.getString("pass"));
                oBean.setEmail(oResultSet.getString("email"));
                oBean.setId_tipousuario(oResultSet.getInt("id_tipousuario"));
                if (intExpand > 0) {
                    TipoUsuarioBean oTipoUsuario = new TipoUsuarioBean();
                    TipousuarioDao oTipoUsuarioDao = new TipousuarioDao(oConnection);
                    oTipoUsuario.setId(oBean.getId_tipousuario());
                    oTipoUsuario = oTipoUsuarioDao.get(oTipoUsuario, --intExpand);
                    oBean.setObj_tipousuario(oTipoUsuario);
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
    public Integer set(UsuarioBean oBean) throws Exception {
        PreparedStatement oPreparedStatement = null;
        Integer iResult = 0;
        Boolean insert = true;
        try {
            if (oBean.getId() == null) {
                strSQL = "INSERT INTO " + strTable;
                strSQL += "( ";
                strSQL += "dni, ";
                strSQL += "nombre, ";
                strSQL += "primer_apellido, ";
                strSQL += "segundo_apellido, ";
                strSQL += "login, ";
                strSQL += "pass, ";
                strSQL += "email, ";
                strSQL += "id_tipousuario ";
                strSQL += ") ";
                strSQL += " VALUES ";
                strSQL += "( ";
                strSQL += EncodingUtilHelper.quotate(oBean.getDni()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getNombre()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getPrimer_apellido()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getSegundo_apellido()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getLogin()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getPass()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getEmail()) + ",";
                strSQL += oBean.getId_tipousuario();
                strSQL += ")";
            } else {
                insert = false;
                strSQL = "UPDATE " + strTable;
                strSQL += " SET ";
                strSQL += "dni=" + EncodingUtilHelper.quotate(oBean.getDni()) + ", ";
                strSQL += "nombre=" + EncodingUtilHelper.quotate(oBean.getNombre()) + ",";
                strSQL += "primer_apellido=" + EncodingUtilHelper.quotate(oBean.getPrimer_apellido()) + ",";
                strSQL += "segundo_apellido=" + EncodingUtilHelper.quotate(oBean.getSegundo_apellido()) + ",";
                strSQL += "login=" + EncodingUtilHelper.quotate(oBean.getLogin()) + ",";
                strSQL += "pass=" + EncodingUtilHelper.quotate(oBean.getPass()) + ",";
                strSQL += "email= " + EncodingUtilHelper.quotate(oBean.getEmail()) + ", ";
                strSQL += "id_tipousuario= " + oBean.getId_tipousuario();
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
                throw new Exception("UsuarioDao getCount error");
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
     * @return ArrayList<UsuarioBean>
     * @throws Exception
     */
    @Override
    public ArrayList<UsuarioBean> getpage(int intRegsPerPag, int intPage, LinkedHashMap<String, String> hmOrder, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        String strSQL1 = strSQL;
        strSQL1 += SqlBuilder.buildSqlFilter(alFilter);
        strSQL1 += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL1 += SqlBuilder.buildSqlLimit(this.getcount(alFilter), intRegsPerPag, intPage);
        ArrayList<UsuarioBean> aloBean = new ArrayList<>();
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL1);
            oResultSet = oPreparedStatement.executeQuery(strSQL1);
            while (oResultSet.next()) {
                aloBean.add(this.get(new UsuarioBean(oResultSet.getInt("id")), AppConfigurationHelper.getJsonMsgDepth()));
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

    public UsuarioBean getFromLoginAndPass(UsuarioBean oUsuarioBean) throws Exception {

        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "SELECT * FROM " + strTable + " WHERE 1=1 ";
        strSQL += " AND login='" + oUsuarioBean.getLogin() + "'";
        strSQL += " AND pass='" + oUsuarioBean.getPass() + "'";

        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oUsuarioBean.setId(oResultSet.getInt("id"));
                oUsuarioBean.setDni(oResultSet.getString("dni"));
                oUsuarioBean.setNombre(oResultSet.getString("nombre"));
                oUsuarioBean.setPrimer_apellido(oResultSet.getString("primer_apellido"));
                oUsuarioBean.setSegundo_apellido(oResultSet.getString("segundo_apellido"));
                oUsuarioBean.setLogin(oResultSet.getString("login"));
                oUsuarioBean.setPass(oResultSet.getString("pass"));
                oUsuarioBean.setEmail(oResultSet.getString("email"));
                oUsuarioBean.setId_tipousuario(oResultSet.getInt("id_tipousuario"));
                //pendiente la expansión ************************* %%%%%%
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
        return oUsuarioBean;

    }

    public Long getCountxtipousuario(int id_tipousuario) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "SELECT COUNT(*) FROM " + strTable;
        strSQL += " WHERE id_tipousuario=" + id_tipousuario;
        Long iResult = 0L;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                iResult = oResultSet.getLong("COUNT(*)");
            } else {
                throw new Exception("UsuarioDao getCount error");
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

    public ArrayList<UsuarioBean> getPagextipousuario(int intRegsPerPag, int intPage, LinkedHashMap<String, String> hmOrder, ArrayList<FilterBeanHelper> alFilter, int id_tipousuario) throws Exception {
        String strSQL1 = strSQL;
        strSQL1 += " and id_tipousuario=" + id_tipousuario + " ";
        strSQL1 += SqlBuilder.buildSqlFilter(alFilter);
        strSQL1 += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL1 += SqlBuilder.buildSqlLimit(this.getcount(alFilter), intRegsPerPag, intPage);
        ArrayList<UsuarioBean> aloBean = new ArrayList<>();
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL1);
            oResultSet = oPreparedStatement.executeQuery(strSQL1);
            while (oResultSet.next()) {
                aloBean.add(this.get(new UsuarioBean(oResultSet.getInt("id")), AppConfigurationHelper.getJsonMsgDepth()));
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
