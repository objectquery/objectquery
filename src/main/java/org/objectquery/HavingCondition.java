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
	 * Add an greater then condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	void gt(Double value);

	/**
	 * Add an greater then or equals condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	void gtEq(Double value);

	/**
	 * Add an less then condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	void lt(Double value);

	/**
	 * Add an less then or equal condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	void ltEq(Double value);

	/**
	 * Add an max condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	@Deprecated
	void max(Double value);

	/**
	 * Add an max or equals condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	@Deprecated
	void maxEq(Double value);

	/**
	 * Add an min condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	@Deprecated
	void min(Double value);

	/**
	 * Add an min or equals condition on having.
	 * 
	 * @param value
	 *            the operation expected value.
	 */
	@Deprecated
	void minEq(Double value);
}
