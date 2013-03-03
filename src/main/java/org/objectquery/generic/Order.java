package org.objectquery.generic;

import org.objectquery.ObjectQuery;

public class Order {

	private Object item;
	private ProjectionType projectionType;
	private OrderType type;

	public Order(ObjectQuery<?> item, ProjectionType projectionType, OrderType type) {
		this.item = item;
		this.projectionType = projectionType;
		this.type = type;
	}

	public Order(PathItem item, ProjectionType projectionType, OrderType type) {
		this.item = item;
		this.projectionType = projectionType;
		this.type = type;
	}

	public Object getItem() {
		return item;
	}

	public OrderType getType() {
		return type;
	}

	public ProjectionType getProjectionType() {
		return projectionType;
	}

	public void clear() {
		if (item instanceof GenericObjectQuery<?>)
			((GenericObjectQuery<?>) item).clear();
		else
			((PathItem) item).clear();
		item = null;
	}
}
