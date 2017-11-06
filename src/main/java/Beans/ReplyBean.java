package Beans;

/**
 *
 * @author Kysuke
 */

public class ReplyBean {

    // VARIABLES
    
    private Integer code;
    private String json;

    // CONSTRUCTORES
    
    public ReplyBean(Integer code, String json) {
        this.code = code;
        this.json = json;
    }

    public ReplyBean() {
    }

    
    // MÉTODOS FUNCIONALES
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

}
