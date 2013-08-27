package org.objectquery;

public interface UpdateQuery<T> extends BaseQuery<T>, QueryCondition {

	/**
	 * Set an value to update.
	 * 
	 * @param target
	 *            where the value should be set.
	 * @param value
	 *            to set.
	 */
	<S, V extends S> void set(S target, V value);
}
