package Beans;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Kysuke
 */

public class CarritoBean {
    @Expose
    private Integer cantidad;
    @Expose
    private ProductoBean oProducto;

    public CarritoBean(Integer cantidad, ProductoBean oProducto) {
        this.cantidad = cantidad;
        this.oProducto = oProducto;
    }

    public CarritoBean() {
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoBean getoProducto() {
        return oProducto;
    }

    public void setoProducto(ProductoBean oProducto) {
        this.oProducto = oProducto;
    }
    
}
