package org.objectquery.builder;

import javassist.util.proxy.ProxyObject;

public class QueryConditionImpl implements QueryCondition {

	private AbstractObjectQuery<?> objectQuery;
	private InternalConditionBuilder group;

	protected QueryConditionImpl(InternalConditionBuilder group) {
		this.objectQuery = (AbstractObjectQuery<?>) this;
		this.group = group;
	}

	public QueryConditionImpl(AbstractObjectQuery<?> objectQuery, ConditionGroup group) {
		this.objectQuery = objectQuery;
		this.group = group;
	}

	public <C, T extends C> void condition(C base, ConditionType type, T value) {
		if (base == null)
			throw new ObjectQueryException("The given object as condition is null", null);
		PathItem item = null;
		Class<?> baseType = base.getClass();
		if (!(base instanceof ProxyObject)) {
			if ((item = objectQuery.unproxable.get(base)) == null)
				throw new ObjectQueryException("The given object as condition isn't a proxy, use target() method for start to take object for query", null);
		} else {
			item = (PathItem) ((ProxyObject) base).getHandler();
			baseType = base.getClass().getSuperclass();
		}
		if (type == null)
			throw new ObjectQueryException("The given type of condition is null", null);

		if (value != null && !baseType.isAssignableFrom(value.getClass()))
			throw new ObjectQueryException("The given object value is not assignabled to gived condition", null);

		group.condition(item, type, value);

	}

	public QueryCondition and() {
		return new QueryConditionImpl(objectQuery, objectQuery.builder.newGroup(GroupType.AND));
	}

	public QueryCondition or() {
		return new QueryConditionImpl(objectQuery, objectQuery.builder.newGroup(GroupType.OR));
	}

}
