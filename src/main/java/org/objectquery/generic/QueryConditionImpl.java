package org.objectquery.generic;

import java.util.Collection;

import javassist.util.proxy.ProxyObject;

import org.objectquery.SelectQuery;
import org.objectquery.QueryCondition;

public class QueryConditionImpl implements QueryCondition {

	private GenericBaseQuery<?> objectQuery;
	private InternalConditionBuilder group;

	protected QueryConditionImpl(InternalConditionBuilder group) {
		this.objectQuery = (GenericBaseQuery<?>) this;
		this.group = group;
	}

	public QueryConditionImpl(GenericBaseQuery<?> objectQuery, ConditionGroup group) {
		this.objectQuery = objectQuery;
		this.group = group;
	}

	public void condition(Object base, ConditionType type, Object value, Object value1) {
		if (base == null)
			throw new NullPointerException("The given object as condition is null");
		PathItem item = null;
		Class<?> baseType = base.getClass();
		if (!(base instanceof ProxyObject)) {
			if ((item = objectQuery.unproxable.get(base)) == null)
				throw new ObjectQueryException(
						"The given object as condition isn't a proxy, use target() method for start to take object for query or box() for manage primitive types");
		} else {
			if (!(((ProxyObject) base).getHandler() instanceof ObjectQueryHandler))
				throw new ObjectQueryException(
						"The given object as condition isn't a objectquery proxy, use target() method for start to take object for query");

			item = ((ObjectQueryHandler) ((ProxyObject) base).getHandler()).getPath();
			baseType = base.getClass().getSuperclass();
		}
		if (type == null)
			throw new NullPointerException("The given type of condition is null");

		Object curValue = null;
		if (value != null) {
			if (value instanceof GenericSelectQuery<?>) {
				// TODO: Be careful because in the future can be possible
				// another implementation.
				GenericSelectQuery<?> val = (GenericSelectQuery<?>) value;
				if (!val.isSubQuery())
					throw new ObjectQueryException(
							"The given sub query is not a sub query instance, use the method ObjectQuery.subQuery to obtain a sub query instance");
				if (!baseType.isAssignableFrom(val.getTargetClass()))
					throw new ObjectQueryException("The given object value is not assignabled to gived condition");
				if (!((GenericInternalQueryBuilder) val.getBuilder()).getProjections().isEmpty())
					throw new ObjectQueryException("The query given as value are using projection that is not allowed");
			} else {
				if (ConditionType.IN.equals(type) || ConditionType.NOT_IN.equals(type)) {
					if (!(value.getClass().isArray() || value instanceof Collection))
						throw new ObjectQueryException("The given object value is not an array or collection required for in value");
				} else if (ConditionType.CONTAINS.equals(type) || ConditionType.NOT_CONTAINS.equals(type)) {
					if (!(base.getClass().isArray() || base instanceof Collection))
						throw new ObjectQueryException("The given object value is not an array or collection required for in value");
				} else if (!baseType.isAssignableFrom(value.getClass()))
					throw new ObjectQueryException("The given object value is not assignabled to gived condition");
			}
			if (value instanceof ProxyObject && ((ProxyObject) value).getHandler() instanceof ObjectQueryHandler)
				curValue = ((ObjectQueryHandler) ((ProxyObject) value).getHandler()).getPath();
			else if ((curValue = objectQuery.unproxable.get(value)) == null)
				curValue = value;
		}

		Object curValue1 = null;

		if (value1 != null) {
			if (!baseType.isAssignableFrom(value1.getClass()))
				throw new ObjectQueryException("The given object value is not assignabled to gived condition", null);

			if (value1 instanceof ProxyObject && ((ProxyObject) value1).getHandler() instanceof ObjectQueryHandler)
				curValue1 = ((ObjectQueryHandler) ((ProxyObject) value1).getHandler()).getPath();
			else if ((curValue1 = objectQuery.unproxable.get(value1)) == null)
				curValue1 = value1;
		}

		group.condition(item, type, curValue, curValue1);

	}

	public QueryCondition and() {
		return new QueryConditionImpl(objectQuery, group.newGroup(GroupType.AND));
	}

	public QueryCondition or() {
		return new QueryConditionImpl(objectQuery, group.newGroup(GroupType.OR));
	}

	public <C, T extends C> void eq(C target, T value) {
		condition(target, ConditionType.EQUALS, value, null);
	}

	public <C, T extends C> void eq(C target, SelectQuery<T> value) {
		condition(target, ConditionType.EQUALS, value, null);
	}

	public <C, T extends C> void notEq(C target, T value) {
		condition(target, ConditionType.NOT_EQUALS, value, null);
	}

	public <C, T extends C> void notEq(C target, SelectQuery<T> value) {
		condition(target, ConditionType.NOT_EQUALS, value, null);
	}

	public <C, T extends C> void gt(C target, T value) {
		condition(target, ConditionType.GREATER, value, null);
	}

	public <C, T extends C> void gt(C target, SelectQuery<T> value) {
		condition(target, ConditionType.GREATER, value, null);
	}

	public <C, T extends C> void gtEq(C target, T value) {
		condition(target, ConditionType.GREATER_EQUALS, value, null);
	}

	public <C, T extends C> void gtEq(C target, SelectQuery<T> value) {
		condition(target, ConditionType.GREATER_EQUALS, value, null);
	}

	public <C, T extends C> void lt(C target, T value) {
		condition(target, ConditionType.LESS, value, null);
	}

	public <C, T extends C> void lt(C target, SelectQuery<T> value) {
		condition(target, ConditionType.LESS, value, null);

	}

	public <C, T extends C> void ltEq(C target, T value) {
		condition(target, ConditionType.LESS_EQUALS, value, null);
	}

	public <C, T extends C> void ltEq(C target, SelectQuery<T> value) {
		condition(target, ConditionType.LESS_EQUALS, value, null);
	}

	public <C, T extends C> void like(C target, T value) {
		condition(target, ConditionType.LIKE, value, null);
	}

	public <C, T extends C> void notLike(C target, T value) {
		condition(target, ConditionType.NOT_LIKE, value, null);
	}

	public <C, T extends Collection<? extends C>> void in(C target, T value) {
		condition(target, ConditionType.IN, value, null);
	}

	public <C, T extends C> void in(C target, SelectQuery<T> value) {
		condition(target, ConditionType.IN, value, null);
	}

	public <C, T extends Collection<? extends C>> void notIn(C target, T value) {
		condition(target, ConditionType.NOT_IN, value, null);
	}

	public <C, T extends C> void notIn(C target, SelectQuery<T> value) {
		condition(target, ConditionType.NOT_IN, value, null);
	}

	public <C, T extends C> void contains(Collection<C> target, T value) {
		condition(target, ConditionType.CONTAINS, value, null);
	}

	public <C, T extends C> void contains(Collection<C> target, SelectQuery<T> value) {
		condition(target, ConditionType.CONTAINS, value, null);
	}

	public <C, T extends C> void notContains(Collection<C> target, T value) {
		condition(target, ConditionType.NOT_CONTAINS, value, null);
	}

	public <C, T extends C> void notContains(Collection<C> target, SelectQuery<T> value) {
		condition(target, ConditionType.NOT_CONTAINS, value, null);
	}

	public <C, T extends C> void likeNc(C target, T value) {
		condition(target, ConditionType.LIKE_NOCASE, value, null);
	}

	public <C, T extends C> void notLikeNc(C target, T value) {
		condition(target, ConditionType.NOT_LIKE_NOCASE, value, null);
	}

	public <C, T extends C> void between(C target, T from, T to) {
		condition(target, ConditionType.BETWEEN, from, to);
	}

}
