package org.objectquery;

import org.objectquery.generic.ProjectionType;

/**
 * <p>
 * SelectQuery is the main interface for build a "SELECT" query, it allow to add
 * projection, condition, order and having clauses using instances of domain
 * classes.
 * </p>
 * 
 * <h5>Example Of Usage:</h5>
 * 
 * <pre>
 * <code>
 * SelectQuery&lt;Person&gt; query = new GenericSelectQuery&lt;Person&gt;(Person.class);
 * Person toSearch = query.target();
 * query.prj(toSearch.getName());
 * query.eq(toSearch.getMum().getName(),"elisabeth");
 * query.gt(toSearch.getAge(),20);
 * query.order(toSearch.getName());
 * </code>
 * </pre>
 * 
 * @author tglman
 * 
 * @param <T>
 *            The target type of the query.
 */
public interface SelectQuery<T> extends BaseSelectQuery<T> {

	/**
	 * Add a projection to query.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person&gt; query = ....
	 * query.prj(query.target().getName());
	 * </code>
	 * 
	 * @param projection
	 *            the projection object to add.
	 */
	void prj(Object projection);

	/**
	 * Add a projection to query with an operator.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person&gt; query = ....
	 * query.prj(query.target().getSurname(),ProjectionType.COUNT);
	 * </code>
	 * 
	 * @param projection
	 *            the projection object to add.
	 * @param type
	 *            the type of projection to add.
	 */
	void prj(Object projection, ProjectionType type);

}
