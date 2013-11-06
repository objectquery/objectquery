package org.objectquery.generic;

import java.util.IdentityHashMap;

import javassist.util.proxy.ProxyObject;

import org.objectquery.InsertQuery;

public class GenericInsertQuery<T> extends GenericBaseQuery<T> implements InsertQuery<T> {

	public GenericInsertQuery(Class<T> targetClass) {
		this(new GenericInternalQueryBuilder(GroupType.AND), targetClass);
	}

	public GenericInsertQuery(InternalQueryBuilder builder, Class<T> targetClass) {
		super(builder, targetClass, new IdentityHashMap<Object, PathItem>(), QueryType.INSERT);
	}

	public <S, V extends S> void set(S target, V value) {
		PathItem item = extractItem(target);
		if (item.getParent() == null)
			throw new ObjectQueryException(" Cannot set the value of the root element");
		if ((value instanceof ProxyObject && ((ProxyObject) value).getHandler() instanceof ObjectQueryHandler) || unproxable.get(value) != null)
			throw new ObjectQueryException(" Impossible use in an insert operation an expressio as value");
		builder.set(item, value);
	}
}
