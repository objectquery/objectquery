package org.objectquery.builder;

import java.util.ArrayList;
import java.util.List;

public class TestQueryBuilder implements InternalQueryBuilder {

	private List<String> conditions = new ArrayList<String>();
	private List<String> projections = new ArrayList<String>();
	private List<String> orders = new ArrayList<String>();

	public void projection(String projection) {
		projections.add(projection);
	}

	public void projection(String projection, ProjectionType type) {
		projections.add(projection + " " + type.name());
	}

	public void condition(String target, ConditionType type, Object value) {
		conditions.add(target + " " + type.name() + " " + value);
	}

	public void order(String order) {
		order(order, OrderType.ASC);
	}

	public void order(String order, OrderType type) {
		orders.add(order + " " + type.name());
	}

	public List<String> getConditions() {
		return conditions;
	}

	public List<String> getOrders() {
		return orders;
	}

	public List<String> getProjections() {
		return projections;
	}

}
