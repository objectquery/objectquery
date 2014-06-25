package org.objectquery.generic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.objectquery.BaseSelectQuery;

public class GenericInternalQueryBuilder extends ConditionGroup implements InternalQueryBuilder {

	private List<Order> orders = new ArrayList<Order>();
	private List<Projection> projections = new ArrayList<Projection>();
	private List<Having> havings = new ArrayList<Having>();
	private List<SetValue> sets = new ArrayList<SetValue>();
	private QueryType queryType;

	public GenericInternalQueryBuilder(GroupType type) {
		super(type);
	}

	public static void buildPath(PathItem item, StringBuilder builder) {
		buildPath(item, builder, ".");
	}

	public static void buildPath(PathItem item, StringBuilder builder, String separator) {
		if (item.getParent() != null && (item.getParent().getParent() != null || !item.getParent().getName().isEmpty())) {
			buildPath(item.getParent(), builder, separator);
			builder.append(separator);
		}
		builder.append(item.getName());
	}

	public static void setMappingValue(Object instance, PathItem field, Object value) {
		try {
			String name = field.getName();
			Method set = instance.getClass().getMethod("set" + Character.toUpperCase(name.charAt(0)) + name.substring(1), field.getClazz());
			set.invoke(instance, value);
		} catch (Exception e) {
			throw new ObjectQueryException("Error setting mapper value", e);
		}
	}

	public void order(BaseSelectQuery<?> order, ProjectionType projectionType, OrderType type) {
		orders.add(new Order(order, projectionType, type));
	}

	public void order(PathItem item, ProjectionType projectionType, OrderType type) {
		orders.add(new Order(item, projectionType, type));
	}

	public void projection(PathItem item, PathItem mapper, ProjectionType type) {
		projections.add(new Projection(item, mapper, type));
	}

	public void projection(BaseSelectQuery<?> projection, PathItem mapper, ProjectionType type) {
		projections.add(new Projection(projection, mapper, type));
	}

	public void having(PathItem item, ProjectionType projectionType, ConditionType conditionType, Object value) {
		havings.add(new Having(item, projectionType, conditionType, value));
	}

	public void having(BaseSelectQuery<?> item, ProjectionType projectionType, ConditionType conditionType, Object value) {
		havings.add(new Having(item, projectionType, conditionType, value));
	}

	public void set(PathItem target, Object value) {
		sets.add(new SetValue(target, value));
	}

	public List<Order> getOrders() {
		return orders;
	}

	public List<Projection> getProjections() {
		return projections;
	}

	public List<Having> getHavings() {
		return havings;
	}

	public List<SetValue> getSets() {
		return sets;
	}

	public void init(QueryType type) {
		setType(type);
	}

	public QueryType getQueryType() {
		return queryType;
	}

	public void setType(QueryType type) {
		this.queryType = type;
	}

	public void clear() {
		super.clear();
		for (Order order : orders) {
			order.clear();
		}
		orders.clear();
		for (Projection projection : projections) {
			projection.clear();
		}
		projections.clear();
		for (Having having : havings) {
			having.clear();
		}
		havings.clear();
	}
}
