package org.objectquery;

import org.objectquery.generic.JoinType;
import org.objectquery.generic.OrderType;
import org.objectquery.generic.ProjectionType;

public interface BaseSelectQuery<T> extends BaseQuery<T>, QueryCondition {

	/**
	 * Add an order condition to query.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person&gt; query = ....
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
	 * SelectQuery&lt;Person&gt; query = ....
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
	 * SelectQuery&lt;Person&gt; query = ....
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
	 * SelectQuery&lt;Person&gt; query = ....
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
	 * Is needed for all the times you what create a sub-query to use inside the
	 * current.
	 * 
	 * Example: <code>
	 * SelectQuery&lt:Person&gt; query = ....
	 * SelectQuery&lt;Home&gt; queryHomes = query.subQuery(Home.class);
	 * ....
	 * query.in(query.target().getHome(),queryHomes);
	 * </code>
	 * 
	 * @param target
	 *            class target of sub query.
	 * @return the ObjectQuery instance of sub query.
	 */
	<S> BaseSelectQuery<S> subQuery(Class<S> target);

	/**
	 * Create a join object with the specified type
	 * 
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; query = ...
	 * Home home = query.join(Home.class);
	 * query.eq(query.target().getAddress(),home.getAddress());
	 * </code>
	 * 
	 * @param joinClass
	 *            the type of the join object.
	 * @return the new join object.
	 */
	<J> J join(Class<J> joinClass);

	/**
	 * Create a join object with the specified type and specific join operator
	 * 
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; query = ...
	 * Home home = query.join(Home.class,JoinType.LEFT);
	 * query.eq(query.target().getAddress(),home.getAddress());
	 * </code>
	 * 
	 * @param joinClass
	 *            the type of the join object.
	 * @param type
	 *            the join operator type.
	 * @return the new join object.
	 */
	<J> J join(Class<J> joinClass, JoinType type);

	/**
	 * Create a join object with the specified type, on the specified base path
	 * 
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; query = ...
	 * Company company = query.join(target.getCompany(),Company.class);
	 * query.eq(query.target().getAddress(),company.getAddress());
	 * </code>
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
	 * and join operator
	 * 
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; query = ...
	 * Company company = query.join(target.getCompany(),Company.class,JoinType.LEFT);
	 * query.eq(query.target().getAddress(),company.getAddress());
	 * </code>
	 * 
	 * @param joinPath
	 *            the path to reach the base object.
	 * @param joinClass
	 *            the type of the join object.
	 * @param type
	 *            the join operator type.
	 * 
	 * @return the new join object.
	 */
	<J> J join(J joinPath, Class<J> joinClass, JoinType type);
}
