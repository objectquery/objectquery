package org.objectquery.builder;

/**
 * Interface to implement a custom query builder from the specified tecnology.
 * 
 */
public interface InternalQueryBuilder {

	/**
	 * Add a projection to query.
	 * 
	 * @param item
	 *            the projection object to add.
	 */
	void projection(PathItem item);

	/**
	 * Add a projection to query with an operator.
	 * 
	 * @param item
	 *            the projection object to add.
	 * @param type
	 *            the type of projection to add.
	 */
	void projection(PathItem item, ProjectionType type);

	/**
	 * Add a condition between two values
	 * 
	 * @param item
	 *            the base operation target.
	 * @param type
	 *            the operation type.
	 * @param value
	 *            the operation expected value.
	 */
	void condition(PathItem item, ConditionType type, Object value);

	/**
	 * Add an order condition to query.
	 * 
	 * @param pathItem
	 *            the order to add.
	 */
	void order(PathItem pathItem);

	/**
	 * Add an order to query.
	 * 
	 * @param item
	 *            the order to add.
	 * @param type
	 *            the type of order.
	 */
	void order(PathItem item, OrderType type);

}
