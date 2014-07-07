package org.objectquery.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public static void setMappingValue(Object instance, PathItem field, Object value) throws Exception {
		String name = field.getName();
		Method set = instance.getClass().getMethod("set" + Character.toUpperCase(name.charAt(0)) + name.substring(1), field.getClazz());
		set.invoke(instance, value);

	}

	public static Object getValue(Object instance, PathItem field) throws Exception {
		String name = field.getName();
		Method set = instance.getClass().getMethod("get" + Character.toUpperCase(name.charAt(0)) + name.substring(1));
		return set.invoke(instance);
	}

	public static Object setMapping(Class<?> target, List<Projection> projections, Map<String, Object> values) {
		try {
			Object targetInst = target.newInstance();
			for (Projection projection : projections) {
				StringBuilder path = new StringBuilder();
				PathItem mapper = projection.getMapper();
				buildPath(mapper, path, "_");
				Object value = values.get(path.toString());
				setMappingValue(getParentObject(targetInst, mapper.getParent()), mapper, value);
			}
			return targetInst;
		} catch (Exception e) {
			throw new ObjectQueryException("Error filling the mapped result ", e);
		}
	}

	public static Object getParentObject(Object targetInst, PathItem parent) throws Exception {
		if (parent.getParent() == null) {
			return targetInst;
		} else {
			Object cur = getParentObject(targetInst, parent.getParent());
			Object toRet = getValue(cur, parent);
			if (toRet == null) {
				toRet = parent.getClazz().newInstance();
				setMappingValue(cur, parent, toRet);
			}
			return toRet;
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
