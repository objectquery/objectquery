package org.objectquery.generic;

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
	 * @param value1 TODO
	 */
	void condition(PathItem item, ConditionType type, Object value, Object value1);

	/**
	 * Build a condition group of a type.
	 * 
	 * @param type
	 *            the type of group.
	 * @return the new group.
	 */
	public ConditionGroup newGroup(GroupType type);
}
