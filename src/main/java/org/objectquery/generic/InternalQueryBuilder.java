package org.objectquery.generic;

import org.objectquery.BaseSelectQuery;

/**
 * Interface to implement a custom query builder from the specified tecnology.
 * 
 */
public interface InternalQueryBuilder extends InternalConditionBuilder {

	/**
	 * Initialize the current builder for the specified query type.
	 * 
	 * @param type
	 *            the type of the query.
	 */
	void init(QueryType type);

	/**
	 * Add a projection to query with an operator.
	 * 
	 * @param item
	 *            the projection object to add.
	 * @param mapper
	 *            if is not null the mapper mapped field for the projection.
	 * @param type
	 *            the type of projection to add.
	 * 
	 */
	void projection(PathItem item, PathItem mapper, ProjectionType type);

	/**
	 * Add a sub Query projection to query with an operator.
	 * 
	 * @param projection
	 *            the sub Query projection object to add.
	 * @param mapper
	 *            if is not null the mapper mapped field for the projection.
	 * @param type
	 *            the type of projection to add.
	 */
	void projection(BaseSelectQuery<?> projection, PathItem mapper, ProjectionType type);

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
	void order(BaseSelectQuery<?> order, ProjectionType projectionType, OrderType type);

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
	void having(BaseSelectQuery<?> item, ProjectionType projectionType, ConditionType conditionType, Object value);

	/**
	 * Create and add a new set Item.
	 * 
	 * @param target
	 *            of the set operation.
	 * @param value
	 *            the value of the operation.
	 */
	void set(PathItem target, Object value);

	/**
	 * Clear all object used for build query and the object can be reused for
	 * build another query.
	 */
	void clear();

}
