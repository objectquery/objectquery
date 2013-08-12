package org.objectquery;

public interface BaseQuery<T> {

	/**
	 * Retrieve the instance to build query.
	 * 
	 * @return the instance for query building.
	 */
	T target();

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
}
