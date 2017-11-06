package Dao;

/**
 *
 * @author Kysuke
 * @param <GenericBeanImplementation>
 */

public interface DaoTableInterface<GenericBeanImplementation> {
     
    public GenericBeanImplementation get(GenericBeanImplementation oBean, int intExpand) throws Exception;
 
    public Integer set(GenericBeanImplementation oBean) throws Exception;
 
    public Integer remove(Integer id) throws Exception;
 
}