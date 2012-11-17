package org.objectquery.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericInternalQueryBuilder extends ConditionGroup implements InternalQueryBuilder {

	private List<Order> orders = new ArrayList<Order>();
	private List<Projection> projections = new ArrayList<Projection>();
	private List<Having> havings = new ArrayList<Having>();

	public GenericInternalQueryBuilder(GroupType type) {
		super(type);
	}

	public static void buildPath(PathItem item, StringBuilder builder) {
		buildPath(item, builder, ".");
	}

	public static void buildPath(PathItem item, StringBuilder builder, String separator) {
		if (item.getParent() != null && item.getParent().getParent() != null) {
			buildPath(item.getParent(), builder);
			builder.append(separator);
		}
		builder.append(item.getName());
	}

	public void order(PathItem item, ProjectionType projectionType, OrderType type) {
		orders.add(new Order(item, projectionType, type));
	}

	public void projection(PathItem item, ProjectionType type) {
		projections.add(new Projection(item, type));
	}

	public void having(PathItem item, ProjectionType projectionType, ConditionType conditionType, Object value) {
		havings.add(new Having(item, projectionType, conditionType, value));
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

	public void clear() {
		orders.clear();
		projections.clear();
		havings.clear();
		getConditions().clear();

	}
}
