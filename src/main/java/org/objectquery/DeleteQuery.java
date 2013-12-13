package org.objectquery;

/**
 * delete query to write a delete query with a set of condition
 * 
 * usage example:
 * 
 * <code>
 * DeleteQuery&lt;Person&gt; delete = new GenericDeleteQuery&lt;Person&gt;(Person.class);
 * Person toDelete = delete.target();
 * delete.eq(toDelete.getName(),"match name");
 * </code>
 * 
 * @author tglman
 * 
 * @param <T>
 *            The target type of the query.
 */
public interface DeleteQuery<T> extends BaseQuery<T>, QueryCondition {

}
