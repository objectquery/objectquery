package org.objectquery.builder;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractInternalQueryBuilder extends ConditionGroup implements InternalQueryBuilder {

	private List<Order> orders = new ArrayList<Order>();
	private List<Projection> projections = new ArrayList<Projection>();

	public AbstractInternalQueryBuilder() {
		super(GroupType.AND);
	}

	public static void buildPath(PathItem item, StringBuilder builder) {
		if (item.getParent().getParent() != null) {
			buildPath(item.getParent(), builder);
			builder.append(".");
		}
		builder.append(item.getName());
	}

	public void order(PathItem item, OrderType type) {
		orders.add(new Order(item, type));
	}

	public void order(PathItem item) {
		orders.add(new Order(item, null));
	}

	public void projection(PathItem item) {
		projections.add(new Projection(item, null));
		StringBuilder builder = new StringBuilder();
		buildPath(item, builder);
	}

	public void projection(PathItem item, ProjectionType type) {
		projections.add(new Projection(item, type));
	}

	public List<Order> getOrders() {
		return orders;
	}

	public List<Projection> getProjections() {
		return projections;
	}
}