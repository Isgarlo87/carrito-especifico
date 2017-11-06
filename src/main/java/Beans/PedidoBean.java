package Beans;

import com.google.gson.annotations.Expose;
import java.sql.Date;

/**
 *
 * @author Kysuke
 */

public class PedidoBean {

    // VARIABLES
    @Expose
    private Integer id = 0;
    @Expose
    private Date fecha;
    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario = null;

    // CONSTRUCTORES
    public PedidoBean(int id) {
        this.id = id;
    }
    
    public PedidoBean(Date fecha, Integer id_usuario) {
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }

    public PedidoBean() {
    }

    // MÉTODOS FUNCIONALES
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public UsuarioBean getObj_usuario() {
        return obj_usuario;
    }

    public void setObj_usuario(UsuarioBean obj_usuario) {
        this.obj_usuario = obj_usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
