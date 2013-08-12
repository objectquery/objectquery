package org.objectquery;

public interface InsertQuery<T> extends BaseQuery<T> {

	/**
	 * Assign a value to a field in the insert query.
	 * 
	 * @param target
	 *            field target of the value.
	 * @param value
	 *            to set.
	 */
	public <S, V extends S> void set(S target, V value);

}
