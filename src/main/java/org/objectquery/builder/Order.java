package org.objectquery.builder;

public class Order {

	private PathItem item;
	private OrderType type;

	public Order(PathItem item, OrderType type) {
		this.item = item;
		this.type = type;
	}

	public PathItem getItem() {
		return item;
	}

	public OrderType getType() {
		return type;
	}
}
