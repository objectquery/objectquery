package org.objectquery.builder;

public interface InternalConditionBuilder {

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
	 * Create an and group.
	 * 
	 * @return the and group.
	 */
	ConditionGroup and();

	/**
	 * Create an not group.
	 * 
	 * @return the not group.
	 */
	ConditionGroup not();

	/**
	 * Create an or group.
	 * 
	 * @return the or group.
	 */
	ConditionGroup or();
}
