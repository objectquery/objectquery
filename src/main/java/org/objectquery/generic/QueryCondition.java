package org.objectquery.generic;

import java.util.Collection;

public interface QueryCondition {

	/**
	 * Add an equal condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void eq(C target, T value);

	/**
	 * Add an not equal condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void notEq(C target, T value);

	/**
	 * Add an max condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void max(C target, T value);

	/**
	 * Add an max or equals condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void maxEq(C target, T value);

	/**
	 * Add an min condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void min(C target, T value);

	/**
	 * Add an min or equals condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void minEq(C target, T value);

	/**
	 * Add an like condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void like(C target, T value);

	/**
	 * Add an not like condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void notLike(C target, T value);

	/**
	 * Add an in condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends Collection<? extends C>> void in(C target, T value);

	/**
	 * Add an not in condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends Collection<? extends C>> void notIn(C target, T value);

	/**
	 * Add an contains condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void contains(Collection<C> target, T value);

	/**
	 * Add an contains condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void notContains(Collection<C> target, T value);

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
