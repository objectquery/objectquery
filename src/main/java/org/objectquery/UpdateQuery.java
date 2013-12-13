package org.objectquery;

/**
 * Update query allow to set the values and condition of a update query.
 * 
 * 
 * Example of usage:
 * 
 * <code>
 * UpdateQuery&lt;Person&gt; query = new GenericUpdateQuery&lt;Person&gt;(Person.class);
 * Person toUpdate = query.target();
 * query.eq(toUpdate.getName(),"old Name");
 * query.set(toUpdate.getName(),"new Name");
 * </code>
 * 
 * @author tglman
 * 
 * @param <T>
 *            The target type of the query.
 */
public interface UpdateQuery<T> extends BaseQuery<T>, QueryCondition {

	/**
	 * Set an value to update.
	 * 
	 * @param target
	 *            where the value should be set.
	 * @param value
	 *            to set.
	 */
	<S, V extends S> void set(S target, V value);
}
