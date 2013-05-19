package org.objectquery;

import org.objectquery.generic.JoinType;
import org.objectquery.generic.OrderType;
import org.objectquery.generic.ProjectionType;

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
	void prj(Object projection);

	/**
	 * Add a projection to query with an operator.
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

	/**
	 * Add an order to query.
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
	 * Box a byte.
	 * 
	 * @param b
	 *            to box.
	 * @return the boxed byte.
	 */
	Byte box(byte b);

	/**
	 * Box a char.
	 * 
	 * @param c
	 *            to box.
	 * @return the boxed char.
	 */
	Character box(char c);

	/**
	 * Box a boolean.
	 * 
	 * @param b
	 *            to box
	 * @return boxed Boolean.
	 */
	Boolean box(boolean b);

	/**
	 * Box an short.
	 * 
	 * @param s
	 *            to box.
	 * @return the boxed short
	 */
	Short box(short s);

	/**
	 * Box an int.
	 * 
	 * @param i
	 *            to box.
	 * @return boxed int.
	 */
	Integer box(int i);

	/**
	 * Box an long.
	 * 
	 * @param l
	 *            to box.
	 * @return boxed long.
	 */
	Long box(long l);

	/**
	 * Box a float.
	 * 
	 * @param f
	 *            to box.
	 * @return boxed float.
	 */
	Float box(float f);

	/**
	 * Box a double.
	 * 
	 * @param d
	 *            to box.
	 * @return the boxed double.
	 */
	Double box(double d);

	/**
	 * Declare an having condition on projection.
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
	<S> ObjectQuery<S> subQuery(Class<S> target);

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
