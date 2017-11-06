package Beans;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Kysuke
 */

public class TipoUsuarioBean {
    
    // VARIABLES
    @Expose
    private Integer id;
    @Expose
    private String descripcion;

    // CONSTRUCTORES
    public TipoUsuarioBean(Integer id) {
        this.id = id;
    }

    public TipoUsuarioBean() {
    }
    
    
    // MÉTODOS FUNCIONALES
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}