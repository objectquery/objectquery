package org.objectquery.generic;

import java.util.IdentityHashMap;

import org.objectquery.UpdateQuery;

public class GenericUpdateQuery<T> extends GenericBaseQuery<T> implements UpdateQuery<T> {

	public GenericUpdateQuery(Class<T> targetClass) {
		this(new GenericInternalQueryBuilder(GroupType.AND), targetClass);
	}

	public GenericUpdateQuery(InternalQueryBuilder builder, Class<T> targetClass) {
		super(builder, targetClass, new IdentityHashMap<Object, PathItem>());
	}

	public void set(Object target, Object value) {
		// TODO Implements the set of values
	}

}
