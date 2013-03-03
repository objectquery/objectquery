package org.objectquery.generic;

import org.objectquery.ObjectQuery;

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
	 * Add a sub Query projection to query with an operator.
	 * 
	 * @param item
	 *            the sub Query projection object to add.
	 * @param type
	 *            the type of projection to add.
	 */
	void projection(ObjectQuery<?> projection, ProjectionType type);

	/**
	 * Add an sub query order to query.
	 * 
	 * @param item
	 *            the order to add.
	 * @param projectionType
	 *            the gruping function in the order.
	 * @param type
	 *            the type of order.
	 */
	void order(ObjectQuery<?> order, ProjectionType projectionType, OrderType type);

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

	/**
	 * Clear all object used for build query and the object can be reused for
	 * build another query.
	 */
	public void clear();

}
