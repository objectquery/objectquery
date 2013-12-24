package org.objectquery;

/**
 * insert query allow to declare a query to insert a new record.
 * 
 * usage example: <code>
 * InsertQuery&lt;Person&gt; insert = new GenericInsertQuery&lt;Person&gt;(Person.class);
 * Person toInsert = insert.target();
 * insert.set(toInsert.getName(),"new name");
 * insert.set(toInsert.getSurname(),"new surname");
 * 
 * </code>
 * 
 * @author tglman
 * 
 * @param <T>
 *            The target type of the query.
 */
public interface InsertQuery<T> extends BaseQuery<T> {

	/**
	 * Assign a value to a field in the insert query.
	 * 
	 * example:
	 * <code>
	 * query.set(target.getName,"new name");
	 * </code>
	 * 
	 * @param target
	 *            field target of the value.
	 * @param value
	 *            to set.
	 */
	<S, V extends S> void set(S target, V value);

}
