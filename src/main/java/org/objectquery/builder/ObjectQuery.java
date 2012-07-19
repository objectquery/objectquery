package org.objectquery.builder;

public interface ObjectQuery<T> extends QueryCondition {

	/**
	 * Retrieve the instance to build query.
	 * 
	 * @return
	 */
	T target();

	/**
	 * Add a projection to query.
	 * 
	 * @param projection
	 *            the projection object to add.
	 */
	void projection(Object projection);

	/**
	 * Add a projection to query with an operator.
	 * 
	 * @param projection
	 *            the projection object to add.
	 * @param type
	 *            the type of projection to add.
	 */
	void projection(Object projection, ProjectionType type);

	/**
	 * Add an order condition to query.
	 * 
	 * @param order
	 *            the order to add.
	 */
	void order(Object order);

	/**
	 * Add an order to query.
	 * 
	 * @param order
	 *            the order to add.
	 * @param type
	 *            the type of order.
	 */
	void order(Object order, OrderType type);

}
