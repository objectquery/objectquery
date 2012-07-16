package org.objectquery.builder;

/**
 * Interface to implement a custom query builder from the specified tecnology.
 * 
 */
public interface InternalQueryBuilder {

	/**
	 * Add a projection to query.
	 * 
	 * @param projection
	 *            the projection object to add.
	 */
	void projection(String projection);

	/**
	 * Add a projection to query with an operator.
	 * 
	 * @param projection
	 *            the projection object to add.
	 * @param type
	 *            the type of projection to add.
	 */
	void projection(String projection, ProjectionType type);

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
	void condition(String target, ConditionType type, Object value);

	/**
	 * Add an order condition to query.
	 * 
	 * @param order
	 *            the order to add.
	 */
	void order(String order);

	/**
	 * Add an order to query.
	 * 
	 * @param order
	 *            the order to add.
	 * @param type
	 *            the type of order.
	 */
	void order(String order, OrderType type);

}
