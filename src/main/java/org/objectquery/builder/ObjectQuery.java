package org.objectquery.builder;

public interface ObjectQuery<T> {

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
	 * Add a condition between two values
	 * 
	 * @param target
	 *            the base operation target.
	 * @param type
	 *            the operation type.
	 * @param value
	 *            the operation expected value.
	 */
	<C> void condition(C target, ConditionType type, C value);

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
