package org.objectquery.builder;

public interface QueryCondition {

	/**
	 * Add a condition between two values
	 * 
	 * @param target
	 *            the base operation target.
	 * @param type
	 *            the operation type.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void condition(C target, ConditionType type, T value);

	/**
	 * Create a condition group or.
	 * 
	 * @return the condition group.
	 */
	QueryCondition or();

	/**
	 * Create a condition group and.
	 * 
	 * @return the condition group.
	 */
	QueryCondition and();

}
