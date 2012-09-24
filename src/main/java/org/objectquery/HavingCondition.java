package org.objectquery;


public interface HavingCondition {

	/**
	 * Add an equal condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	void eq(Double value);

	/**
	 * Add an not equal condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	void notEq(Double value);

	/**
	 * Add an max condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	void max(Double value);

	/**
	 * Add an max or equals condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	void maxEq(Double value);

	/**
	 * Add an min condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	void min(Double value);

	/**
	 * Add an min or equals condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	void minEq(Double value);
}
