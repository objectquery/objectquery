package org.objectquery.builder;

/**
 * Interface to implement a custom query builder from the specified tecnology.
 * 
 */
public interface InternalQueryBuilder extends InternalConditionBuilder {

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
