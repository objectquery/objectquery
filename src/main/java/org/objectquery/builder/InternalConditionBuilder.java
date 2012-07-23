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
	 * Build a condition group of a type.
	 * 
	 * @param type
	 *            the type of group.
	 * @return the new group.
	 */
	public ConditionGroup newGroup(GroupType type);
}
