package org.objectquery;

public interface BaseQuery<T> {

	/**
	 * Retrieve the instance to build query.
	 * 
	 * example: <code>
	 * SelectQuery&lt;Person&gt; queryPerson = ....
	 * Person target = queryPerson.target();
	 * </code>
	 * 
	 * @return the instance for query building.
	 */
	T target();

	/**
	 * Box a byte.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person&gt; query = ....
	 * query.eq(query.box(query.target().getByteField()),1b);
	 * </code>
	 * 
	 * @param b
	 *            to box.
	 * @return the boxed byte.
	 */
	Byte box(byte b);

	/**
	 * Box a char.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person&gt; query = ....
	 * query.eq(query.box(query.target().getCharField()),'A');
	 * </code>
	 * 
	 * @param c
	 *            to box.
	 * @return the boxed char.
	 */
	Character box(char c);

	/**
	 * Box a boolean.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person&gt; query = ....
	 * query.eq(query.box(query.target().getBooleanField()),true);
	 * </code>
	 * 
	 * @param b
	 *            to box
	 * @return boxed Boolean.
	 */
	Boolean box(boolean b);

	/**
	 * Box an short.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person&gt; query = ....
	 * query.eq(query.box(query.target().getShortField()),1);
	 * </code>
	 * 
	 * @param s
	 *            to box.
	 * @return the boxed short
	 */
	Short box(short s);

	/**
	 * Box an int.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person&gt; query = ....
	 * query.eq(query.box(query.target().getIntField()),1);
	 * </code>
	 * 
	 * @param i
	 *            to box.
	 * @return boxed int.
	 */
	Integer box(int i);

	/**
	 * Box an long.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person&gt; query = ....
	 * query.eq(query.box(query.target().getLongField()),1);
	 * </code>
	 * 
	 * @param l
	 *            to box.
	 * @return boxed long.
	 */
	Long box(long l);

	/**
	 * Box a float.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person&gt; query = ....
	 * query.eq(query.box(query.target().getFloadField()),1f);
	 * </code>
	 * 
	 * @param f
	 *            to box.
	 * @return boxed float.
	 */
	Float box(float f);

	/**
	 * Box a double.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person&gt; query = ....
	 * query.eq(query.box(query.target().getDoubleField()),1.0);
	 * </code>
	 * 
	 * @param d
	 *            to box.
	 * @return the boxed double.
	 */
	Double box(double d);
}
