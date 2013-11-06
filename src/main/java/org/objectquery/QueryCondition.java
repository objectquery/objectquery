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
	<C, T extends C> void eq(C target, SelectQuery<T> value);

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
	<C, T extends C> void notEq(C target, SelectQuery<T> value);
	
	/**
	 * Add a "grater than" condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void gt(C target, T value);

	/**
	 * Add a "grater than" condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void gt(C target, SelectQuery<T> value);

	/**
	 * Add a "grater than or equals" condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void gtEq(C target, T value);
	
	/**
	 * Add an "grater than or equals" condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void gtEq(C target, SelectQuery<T> value);

	/**
	 * Add a "lesser than" condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void lt(C target, T value);
	
	/**
	 * Add a "lesser than" condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void lt(C target, SelectQuery<T> value);

	/**
	 * Add a "lesser than" or equals condition between two values.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the operation expected value.
	 */
	<C, T extends C> void ltEq(C target, T value);

	/**
	 * Add a min or equals condition between expression and a query.
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void ltEq(C target,SelectQuery<T> value);

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
	<C, T  extends C> void in(C target, SelectQuery<T> value);

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
	<C, T  extends C> void notIn(C target, SelectQuery<T> value);

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
	<C, T extends C> void contains(Collection<C> target, SelectQuery<T> value);

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
	<C, T extends C> void notContains(Collection<C> target, SelectQuery<T> value);
	
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
	 * Add an between condition with a target and a range( from to ).
	 * 
	 * @param target
	 *            the target of between operation.
	 * @param from
	 *            the value from.
	 * @param to
	 *            the value to.
	 */
	<C, T extends C> void between(C target, T from, T to);

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
