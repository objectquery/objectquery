package org.objectquery;

import org.objectquery.generic.JoinType;
import org.objectquery.generic.OrderType;
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
public interface SelectQuery<T> extends BaseQuery<T>, QueryCondition {

	/**
	 * Add a projection to query.
	 * 
	 * Example:<code>
	 * SelectQuery<Person> query = ....
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
	 * SelectQuery<Person> query = ....
	 * query.prj(query.target().getSurname(),ProjectionType.COUNT);
	 * </code>
	 * 
	 * @param projection
	 *            the projection object to add.
	 * @param type
	 *            the type of projection to add.
	 */
	void prj(Object projection, ProjectionType type);

	/**
	 * Add an order condition to query.
	 * 
	 * Example:<code>
	 * SelectQuery<Person> query = ....
	 * query.order(query.target().getName());
	 * </code>
	 * 
	 * @param order
	 *            the order to add.
	 */
	void order(Object order);

	/**
	 * Add an order to query.
	 * 
	 * Example:<code>
	 * SelectQuery<Person> query = ....
	 * query.order(query.target().getName(),OrderType.DESC);
	 * </code>
	 * 
	 * @param order
	 *            the order to add.
	 * @param type
	 *            the type of order.
	 */
	void order(Object order, OrderType type);

	/**
	 * Add an order to query.
	 * 
	 * Example:<code>
	 * SelectQuery<Person> query = ....
	 * query.order(query.target().getSurname(),ProjectionType.COUNT,OrderType.DESC);
	 * </code>
	 * 
	 * @param order
	 *            the order to add.
	 * @param projectionType
	 *            the tipe of projection for grouping actions in order.
	 * @param type
	 *            the type of order.
	 */
	void order(Object order, ProjectionType projectionType, OrderType type);

	/**
	 * Declare an having condition on projection.
	 * 
	 * Example:<code>
	 * SelectQuery<Person> query = ....
	 * query.having(query.target().getSurname(),ProjectionType.COUNT).gt(20);
	 * </code>
	 * 
	 * @param projection
	 *            target of condition.
	 * @param type
	 *            grouping operation
	 * @return the having condition manager.
	 */
	HavingCondition having(Object projection, ProjectionType type);

	/**
	 * Create a new Sub Query of current query.
	 * 
	 * @param target
	 *            class target of sub query.
	 * @return the ObjectQuery instance of sub query.
	 */
	<S> SelectQuery<S> subQuery(Class<S> target);

	/**
	 * Create a join object with the specified type
	 * 
	 * @param joinClass
	 *            the type of the join object.
	 * @return the new join object.
	 */
	<J> J join(Class<J> joinClass);

	/**
	 * Create a join object with the specified type
	 * 
	 * @param joinClass
	 *            the type of the join object.
	 * @return the new join object.
	 */
	<J> J join(Class<J> joinClass, JoinType type);

	/**
	 * Create a join object with the specified type, on the specified base path
	 * 
	 * @param joinPath
	 *            the path to reach the base object.
	 * @param joinClass
	 *            the type of the join object.
	 * @return the new join object.
	 */
	<J> J join(J joinPath, Class<J> joinClass);

	/**
	 * Create a join object with the specified type, on the specified base path
	 * 
	 * @param joinPath
	 *            the path to reach the base object.
	 * @param joinClass
	 *            the type of the join object.
	 * @return the new join object.
	 */
	<J> J join(J joinPath, Class<J> joinClass, JoinType type);
}
