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
		Object curValue = null;
		if (value instanceof ProxyObject && ((ProxyObject) value).getHandler() instanceof ObjectQueryHandler)
			curValue = ((ObjectQueryHandler) ((ProxyObject) value).getHandler()).getPath();
		else if ((curValue = unproxable.get(value)) == null)
			curValue = value;
		builder.set(item, curValue);
	}
}
