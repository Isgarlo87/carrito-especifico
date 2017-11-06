package Beans;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Kysuke
 */

public class ProductoBean {

    // VARIABLES
    @Expose
    private Integer id;
    @Expose
    private String nombre;
    @Expose
    private Integer existencias;
    @Expose
    private Double precio;

    // CONSTRUCTORES
    public ProductoBean(int id) {
        this.id = id;
    }

    public ProductoBean() {
    }

    
    // MÉTODOS FUNCIONALES
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
