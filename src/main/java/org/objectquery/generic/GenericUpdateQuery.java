package org.objectquery.generic;

import java.util.IdentityHashMap;

import javassist.util.proxy.ProxyObject;

import org.objectquery.UpdateQuery;

public class GenericUpdateQuery<T> extends GenericBaseQuery<T> implements UpdateQuery<T> {

	public GenericUpdateQuery(Class<T> targetClass) {
		this(new GenericInternalQueryBuilder(GroupType.AND), targetClass);
	}

	public GenericUpdateQuery(InternalQueryBuilder builder, Class<T> targetClass) {
		super(builder, targetClass, new IdentityHashMap<Object, PathItem>(), QueryType.UPDATE);
	}

	public <S, V extends S> void set(S target, V value) {
		PathItem item = extractItem(target);
		if (item.getParent() == null)
			throw new ObjectQueryException(" Cannot set the value of the root element");
		Object curValue = null;
		if (value instanceof ProxyObject && ((ProxyObject) value).getHandler() instanceof ObjectQueryHandler)
			curValue = ((ObjectQueryHandler) ((ProxyObject) value).getHandler()).getPath();
		else if ((curValue = unproxable.get(value)) == null)
			curValue = value;
		builder.set(item, curValue);
	}

}
