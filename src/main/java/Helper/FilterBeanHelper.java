package Helper;


public class FilterBeanHelper {

    private String link;
    private String field;
    private String operation;
    private String value;

    public FilterBeanHelper(String link, String field, String operation, String value) {
        this.link = link;
        this.field = field;
        this.operation = operation;
        this.value = value;
    }

    public FilterBeanHelper() {
        
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
