package org.objectquery.generic;

/**
 * Interface to implement a custom query builder from the specified tecnology.
 * 
 */
public interface InternalQueryBuilder extends InternalConditionBuilder {

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
	 * Add an order to query.
	 * 
	 * @param item
	 *            the order to add.
	 * @param projectionType
	 *            the gruping function in the order.
	 * @param type
	 *            the type of order.
	 */
	void order(PathItem item, ProjectionType projectionType, OrderType type);

	/**
	 * Create Add a new having item
	 * 
	 * @param item
	 *            having operation target
	 * @param projectionType
	 *            the type of having projection.
	 * @param conditionType
	 *            the type of having condition.
	 * @param value
	 *            the condition value.
	 */
	void having(PathItem item, ProjectionType projectionType, ConditionType conditionType, Object value);

}
