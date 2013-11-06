package org.objectquery.generic;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javassist.util.proxy.ProxyObject;

import org.objectquery.HavingCondition;
import org.objectquery.SelectQuery;

public class GenericSelectQuery<T> extends GenericBaseQuery<T> implements SelectQuery<T> {

	private final boolean subQuery;
	private int subCount = 0;
	private List<Join> joins = new ArrayList<Join>();

	private GenericSelectQuery(InternalQueryBuilder builder, Class<T> clazz, Map<Object, PathItem> unproxable, boolean subQuery) {
		super(builder,clazz,unproxable, QueryType.SELECT);
		this.subQuery = subQuery;
	}

	public GenericSelectQuery(Class<T> targetClass) {
		this(new GenericInternalQueryBuilder(GroupType.AND), targetClass);
	}

	public GenericSelectQuery(InternalQueryBuilder builder, Class<T> targetClass) {
		this(builder, targetClass, new IdentityHashMap<Object, PathItem>(), false);
	}

	public void prj(Object projection) {
		prj(projection, null);
	}

	public void prj(Object projection, ProjectionType type) {
		if (isSubQuery()) {
			throw new ObjectQueryException("Add projection to a subquery is actually not supported");
		}
		if (projection instanceof GenericSelectQuery<?>) {
			if (!((GenericSelectQuery<?>) projection).isSubQuery())
				throw new ObjectQueryException(
						"The given sub query is not a sub query instance, use the method ObjectQuery.subQuery to obtain a sub query instance");
			builder.projection((SelectQuery<?>) projection, type);
		} else
			builder.projection(extractItem(projection), type);
	}


	public void order(Object order) {
		order(order, null, null);
	}

	public void order(Object order, OrderType type) {
		order(order, null, type);
	}

	public void order(Object order, ProjectionType projectionType, OrderType type) {
		if (order instanceof GenericSelectQuery<?>) {
			if (!((GenericSelectQuery<?>) order).isSubQuery())
				throw new ObjectQueryException(
						"The given sub query is not a sub query instance, use the method ObjectQuery.subQuery to obtain a sub query instance");
			builder.order((SelectQuery<?>) order, projectionType, type);
		} else
			builder.order(extractItem(order), projectionType, type);
	}

	public HavingCondition having(Object projection, ProjectionType type) {
		if (projection instanceof GenericSelectQuery<?>) {
			if (!((GenericSelectQuery<?>) projection).isSubQuery())
				throw new ObjectQueryException(
						"The given sub query is not a sub query instance, use the method ObjectQuery.subQuery to obtain a sub query instance");
			return new GenericHavingCondition(builder, this, (SelectQuery<?>) projection, type);
		} else
			return new GenericHavingCondition(builder, this, extractItem(projection), type);
	}

	public boolean isSubQuery() {
		return subQuery;
	}

	public <S> SelectQuery<S> subQuery(Class<S> targetClass) {
		if (!subQuery)
			getRootPathItem().setName("A");
		GenericSelectQuery<S> subQ = new GenericSelectQuery<S>(new GenericInternalQueryBuilder(GroupType.AND), targetClass, unproxable, true);
		subQ.getRootPathItem().setName(getRootPathItem().getName() + "A" + (subCount++));
		return subQ;
	}

	public <J> J join(Class<J> joinClass) {
		return join(null, joinClass);
	}

	public <J> J join(Class<J> joinClass, JoinType type) {
		return join(null, joinClass, type);
	}

	public <J> J join(J joinPath, Class<J> joinClass) {
		return join(joinPath, joinClass, JoinType.INNER);
	}

	@SuppressWarnings("unchecked")
	public <J> J join(J joinPath, Class<J> joinClass, JoinType type) {
		if (!subQuery)
			getRootPathItem().setName("A");
		J res = (J) proxy(joinClass, null, getRootPathItem().getName() + "B" + (joins.size()));
		joins.add(new Join(((ObjectQueryHandler) ((ProxyObject) res).getHandler()).getPath(), joinPath != null ? extractItem(joinPath) : null, type, joinClass));
		return res;
	}

	public List<Join> getJoins() {
		return joins;
	}

}
