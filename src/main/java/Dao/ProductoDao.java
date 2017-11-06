package Dao;

import Beans.ProductoBean;
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
public class ProductoDao implements DaoTableInterface<ProductoBean>, DaoViewInterface<ProductoBean> {

    private final String strTable = "producto";
    private String strSQL = "SELECT * FROM " + strTable + " WHERE 1=1 ";
    private Connection oConnection = null;

    public ProductoDao(Connection oPooledConnection) {
        oConnection = oPooledConnection;
    }

    @Override
    public ProductoBean get(ProductoBean oBean, int intExpand) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "select * from " + strTable + " WHERE 1=1 ";
        strSQL += " AND id= " + oBean.getId();
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                oBean.setId(oResultSet.getInt("id"));
                oBean.setNombre(oResultSet.getString("nombre"));
                oBean.setExistencias(oResultSet.getInt("existencias"));
                oBean.setPrecio(oResultSet.getDouble("precio"));
                //pendiente la expansión de clave externa
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

    @Override
    public Integer set(ProductoBean oBean) throws Exception {
        PreparedStatement oPreparedStatement = null;
        Integer iResult = 0;
        try {
            if (oBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable;
                strSQL += "( ";
                strSQL += "nombre, ";
                strSQL += "existencias, ";
                strSQL += "precio, ";
                strSQL += ") ";
                strSQL += " VALUES ";
                strSQL += "( ";
                strSQL += EncodingUtilHelper.quotate(oBean.getNombre()) + ", ";
                strSQL += oBean.getExistencias() + ", ";
                strSQL += oBean.getPrecio();
                strSQL += ")";
            } else {
                strSQL = "UPDATE " + strTable;
                strSQL += " SET ";
                strSQL += "nombre=" + EncodingUtilHelper.quotate(oBean.getNombre()) + ", ";
                strSQL += "existencias=" + oBean.getExistencias() + ", ";
                strSQL += "precio= " + oBean.getPrecio();
                strSQL += " WHERE id=" + oBean.getId();
            }
            oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
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

    @Override
    public Integer remove(Integer id) throws Exception {
        int iResult = 0;
        strSQL = "DELETE FROM " + strTable + " WHERE id=? ";
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            iResult = oPreparedStatement.executeUpdate(strSQL);
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

    @Override
    public Long getcount(ArrayList<FilterBeanHelper> alFilter) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "SELECT COUNT(*) FROM " + strTable;
        Long iResult = 0L;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                iResult = oResultSet.getLong("COUNT(*)");
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
        return iResult;
    }

    @Override
    public ArrayList<ProductoBean> getpage(int intRegsPerPag, int intPage, LinkedHashMap<String, String> hmOrder, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        String strSQL1 = strSQL;
        strSQL1 += SqlBuilder.buildSqlFilter(alFilter);
        strSQL1 += SqlBuilder.buildSqlOrder(hmOrder);
        strSQL1 += SqlBuilder.buildSqlLimit(this.getcount(alFilter), intRegsPerPag, intPage);
        ArrayList<ProductoBean> aloBean = new ArrayList<>();
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL1);
            oResultSet = oPreparedStatement.executeQuery(strSQL1);
            while (oResultSet.next()) {
                aloBean.add(this.get(new ProductoBean(oResultSet.getInt("id")), AppConfigurationHelper.getJsonMsgDepth()));
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
}
