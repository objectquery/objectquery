package org.objectquery.generic;

import java.util.IdentityHashMap;

import org.objectquery.DeleteQuery;

public class GenericeDeleteQuery<T> extends GenericBaseQuery<T> implements DeleteQuery<T> {

	public GenericeDeleteQuery(Class<T> targetClass) {
		this(new GenericInternalQueryBuilder(GroupType.AND), targetClass);
	}

	public GenericeDeleteQuery(InternalQueryBuilder builder, Class<T> targetClass) {
		super(builder, targetClass, new IdentityHashMap<Object, PathItem>());
	}
}
