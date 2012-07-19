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

	public <C> void condition(C base, ConditionType type, C value) {
		PathItem item = null;
		if (!(base instanceof ProxyObject)) {
			if ((item = objectQuery.unproxable.get(base)) == null)
				throw new ObjectQueryException("The given object as condition isn't a proxy, use target() method for start to take object for query", null);
		} else
			item = (PathItem) ((ProxyObject) base).getHandler();
		if (type == null)
			throw new ObjectQueryException("The given type of condition is null", null);
		group.condition(item, type, value);

	}

	public QueryCondition and() {
		return new QueryConditionImpl(objectQuery, objectQuery.builder.and());
	}

	public QueryCondition not() {
		return new QueryConditionImpl(objectQuery, objectQuery.builder.not());
	}

	public QueryCondition or() {
		return new QueryConditionImpl(objectQuery, objectQuery.builder.or());
	}

}
