package Beans;

import Beans.PedidoBean;
import Beans.ProductoBean;
import com.google.gson.annotations.Expose;

/**
 *
 * @author Kysuke
 */

public class LineadepedidoBean {

    @Expose
    private Integer id = null;
    @Expose
    private Integer cantidad;
    @Expose(serialize = false)
    private Integer id_pedido = 0;
    @Expose(deserialize = false)
    private PedidoBean obj_pedido = null;
    @Expose(serialize = false)
    private Integer id_producto = 0;
    @Expose(deserialize = false)
    private ProductoBean obj_producto = null;

    public LineadepedidoBean(Integer id, Integer cantidad, PedidoBean obj_Pedido, 
            ProductoBean obj_Producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.obj_pedido = obj_Pedido;
        this.obj_producto = obj_Producto;
    }
    
    public LineadepedidoBean(Integer id, Integer cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public LineadepedidoBean(Integer id) {
        this.id = id;
    }

    public LineadepedidoBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public PedidoBean getObj_pedido() {
        return obj_pedido;
    }

    public void setObj_pedido(PedidoBean obj_pedido) {
        this.obj_pedido = obj_pedido;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public ProductoBean getObj_producto() {
        return obj_producto;
    }

    public void setObj_producto(ProductoBean obj_producto) {
        this.obj_producto = obj_producto;
    }

}
