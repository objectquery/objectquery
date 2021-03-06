package org.objectquery;

import java.util.Collection;

/**
 * Base Condition interface that allow to add condition and group of condition.
 * 
 * @author tglman
 * 
 */
public interface QueryCondition {

	/**
	 * Add an equal condition between two values.
	 * 
	 * Example: <code>
	 * query.eq(query.target().getName(),"example");
	 * </code>
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
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; anotherQuery = ...
	 * query.eq(query.target().getMum(),anoterQuery);
	 * </code>
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void eq(C target, BaseSelectQuery<T> value);

	/**
	 * Add a not equal condition between two values.
	 * 
	 * Example: <code>
	 * query.notEq(query.target().getName(),"example");
	 * </code>
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
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; anotherQuery = ...
	 * query.notEq(query.target().getMum(),anoterQuery);
	 * </code>
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void notEq(C target, BaseSelectQuery<T> value);

	/**
	 * Add a "grater than" condition between two values.
	 * 
	 * Example: <code>
	 * query.gt(query.target().getHeight(),30);
	 * </code>
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
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; anotherQuery = ...
	 * query.gt(query.target().getMembers(),anoterQuery);
	 * </code>
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void gt(C target, BaseSelectQuery<T> value);

	/**
	 * Add a "grater than or equals" condition between two values.
	 * 
	 * Example: <code>
	 * query.gtEq(query.target().getHeight(),30);
	 * </code>
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
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; anotherQuery = ...
	 * query.gtEq(query.target().getMembers(),anoterQuery);
	 * </code>
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void gtEq(C target, BaseSelectQuery<T> value);

	/**
	 * Add a "lesser than" condition between two values.
	 * 
	 * Example: <code>
	 * query.lt(query.target().getHeight(),30);
	 * </code>
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
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; anotherQuery = ...
	 * query.lt(query.target().getMembers(),anoterQuery);
	 * </code>
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void lt(C target, BaseSelectQuery<T> value);

	/**
	 * Add a "lesser than" or equals condition between two values. Example:
	 * 
	 * Example:  <code>
	 * query.ltEq(query.target().getHeight(),30);
	 * </code>
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
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; anotherQuery = ...
	 * query.ltEq(query.target().getMembers(),anoterQuery);
	 * </code>
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void ltEq(C target, BaseSelectQuery<T> value);

	/**
	 * Add a like condition between two values.
	 * 
	 * Example: <code>
	 * query.like(query.target().getName(),"lk");
	 * </code>
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
	 * Example: <code>
	 * query.notLike(query.target().getName(),"lk");
	 * </code>
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
	 * Example: <code>
	 * Collection<String> inValues = ... 
	 * query.in(query.target().getName(), inValues);
	 * </code>
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
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; inValues = ... 
	 * query.in(query.target().getMum(), inValues);
	 * </code>
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void in(C target, BaseSelectQuery<T> value);

	/**
	 * Add a not in condition between two values.
	 * 
	 * Example: <code>
	 * Collection<String> inValues = ... 
	 * query.in(query.target().getName(), inValues);
	 * </code>
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
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; inValues = ... 
	 * query.notIn(query.target().getMum(), inValues);
	 * </code>
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void notIn(C target, BaseSelectQuery<T> value);

	/**
	 * Add a contains condition between two values.
	 * 
	 * Example: <code>
	 * Person contained = ... 
	 * query.contains(query.target().getFriends(), contained);
	 * </code>
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
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; contained = ... 
	 * query.contains(query.target().getFriends(), contained);
	 * </code>
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void contains(Collection<C> target, BaseSelectQuery<T> value);

	/**
	 * Add a not contains condition between two values.
	 * 
	 * Example: <code>
	 * Person notContained = ... 
	 * query.notContains(query.target().getFriends(), contained);
	 * </code>
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
	 * Example: <code>
	 * SelectQuery&lt;Person&gt; notContained = ... 
	 * query.notContains(query.target().getFriends(), notContained);
	 * </code>
	 * 
	 * @param target
	 *            the base operation target.
	 * @param value
	 *            the query source of expected value.
	 */
	<C, T extends C> void notContains(Collection<C> target, BaseSelectQuery<T> value);

	/**
	 * Add a like condition between two values without case match.
	 * 
	 * Example: <code>
	 * query.likeNc(query.target().getName(), "NoCaseTest");
	 * </code>
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
	 * Example: <code>
	 * query.notLikeNc(query.target().getName(), "NoCaseTest");
	 * </code>
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
	 * Example: <code>
	 * query.between(query.target().getSize(), 5, 10);
	 * </code>
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
	 * Example: <code>
	 * QueryCondition or = query.or();
	 * or.eq(....
	 * </code>
	 * 
	 * @return the condition group.
	 */
	QueryCondition or();

	/**
	 * Create a condition group and.
	 *
	 * Example: <code>
	 * QueryCondition and = query.and();
	 * and.eq(....
	 * </code>
	 * 
	 * @return the condition group.
	 */
	QueryCondition and();

}
