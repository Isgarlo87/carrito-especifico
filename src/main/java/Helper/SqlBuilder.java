package Helper;

import static java.lang.Math.ceil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Kysuke
 */

public class SqlBuilder {

    private static String getFilterExpression(FilterBeanHelper temp) {

        switch (temp.getOperation()) {
            case "like": //like
                return temp.getLink() + " " + temp.getField() + " LIKE '%" + temp.getValue() + "%' ";
            case "nlik": //not like
                return temp.getLink() + " " + temp.getField() + " NOT LIKE '%" + temp.getValue() + "%' ";
            case "star": //starts with
                return temp.getLink() + " " + temp.getField() + " LIKE '" + temp.getValue() + "%' ";
            case "nsta": //not starts with
                return temp.getLink() + " " + temp.getField() + " NOT LIKE '" + temp.getValue() + "%' ";
            case "ends": //ends with
                return temp.getLink() + " " + temp.getField() + " LIKE '%" + temp.getValue() + "' ";
            case "nend": //not ends with
                return temp.getLink() + " " + temp.getField() + " NOT LIKE '%" + temp.getValue() + "' ";
            case "equa": //equal
                return temp.getLink() + " " + temp.getField() + " = " + temp.getValue() + " ";
            case "nequ": //not equal
                return temp.getLink() + " " + temp.getField() + " != " + temp.getValue() + " ";
            case "lowe": //lower than
                return temp.getLink() + " " + temp.getField() + " < " + temp.getValue() + " ";
            case "lequ": //lower or equal than
                return temp.getLink() + " " + temp.getField() + " <= " + temp.getValue() + " ";
            case "grea": //greater than
                return temp.getLink() + " " + temp.getField() + " > " + temp.getValue() + " ";
            case "gequ": //greater or equal than
                return temp.getLink() + " " + temp.getField() + " >= " + temp.getValue() + " ";
            default:
                throw new Error("Filter expression malformed. Operator not valid.");
        }
    }

    public static String buildSqlLimit(Long intRegistrosTotales, Integer intRegistrosPorPagina,
            Integer intNumeroPagina) {
        String SQLLimit = "";
        if (intRegistrosPorPagina > 0 && intRegistrosPorPagina < 10000) {
            if (intNumeroPagina > 0 && intNumeroPagina <= (ceil(intRegistrosTotales / intRegistrosPorPagina))) {
                SQLLimit = " LIMIT " + (intNumeroPagina - 1) * intRegistrosPorPagina + " , " + intRegistrosPorPagina;
            }
        }
        return SQLLimit;
    }

    public static String buildSqlOrder(LinkedHashMap<String, String> hmOrder) {
        String strSQLOrder = "";
        if (hmOrder != null) {
            for (Map.Entry<String, String> entry : hmOrder.entrySet()) {
                strSQLOrder += entry.getKey();
                strSQLOrder += " ";
                strSQLOrder += entry.getValue();
                strSQLOrder += ",";
            }
            strSQLOrder = "ORDER BY " + strSQLOrder.substring(0, strSQLOrder.length() - 1);
        }
        return strSQLOrder;
    }

    public static String buildSqlFilter(ArrayList<FilterBeanHelper> alFilter) {
        String strSQLFilter = "";
        Iterator it = alFilter.iterator();
        while (it.hasNext()) {
            FilterBeanHelper oFilterBean = (FilterBeanHelper) it.next();
            strSQLFilter += getFilterExpression(oFilterBean);
        }
        return strSQLFilter;
    }
}
