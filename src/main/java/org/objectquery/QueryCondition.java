package org.objectquery;

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
	 * Add an equal condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void eq(C target, ObjectQuery<T> value);

	/**
	 * Add a not equal condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void notEq(C target, T value);

	/**
	 * Add a not equal condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void notEq(C target, ObjectQuery<T> value);

	/**
	 * Add a max condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void max(C target, T value);

	/**
	 * Add a max condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void max(C target, ObjectQuery<T> value);

	/**
	 * Add a max or equals condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void maxEq(C target, T value);
	
	/**
	 * Add an max or equals condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void maxEq(C target, ObjectQuery<T> value);

	/**
	 * Add a min condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void min(C target, T value);
	
	/**
	 * Add a min condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void min(C target, ObjectQuery<T> value);

	/**
	 * Add a min or equals condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void minEq(C target, T value);

	/**
	 * Add a min or equals condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void minEq(C target,ObjectQuery<T> value);

	/**
	 * Add a like condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void like(C target, T value);

	/**
	 * Add a not like condition between two values.
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
	 * Add an in condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T  extends C> void in(C target, ObjectQuery<T> value);

	/**
	 * Add a not in condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends Collection<? extends C>> void notIn(C target, T value);

	/**
	 * Add a not in condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T  extends C> void notIn(C target, ObjectQuery<T> value);

	/**
	 * Add a contains condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void contains(Collection<C> target, T value);
	
	/**
	 * Add a contains condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void contains(Collection<C> target, ObjectQuery<T> value);

	/**
	 * Add a not contains condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void notContains(Collection<C> target, T value);

	/**
	 * Add a not contains condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void notContains(Collection<C> target, ObjectQuery<T> value);
	
	/**
	 * Add a like condition between two values without case match.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void likeNc(C target, T value);

	/**
	 * Add a not like condition between two values without case match.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void notLikeNc(C target, T value);

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
