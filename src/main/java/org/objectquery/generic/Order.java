package org.objectquery.generic;

public class Order {

	private PathItem item;
	private ProjectionType projectionType;
	private OrderType type;

	public Order(PathItem item, ProjectionType projectionType, OrderType type) {
		this.item = item;
		this.projectionType = projectionType;
		this.type = type;
	}

	public PathItem getItem() {
		return item;
	}

	public OrderType getType() {
		return type;
	}

	public ProjectionType getProjectionType() {
		return projectionType;
	}
}
