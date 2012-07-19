package org.objectquery.builder;

import java.util.ArrayList;
import java.util.List;

public class TestQueryBuilder extends AbstractInternalQueryBuilder {

	private List<String> conditionsString = new ArrayList<String>();
	private List<String> projectionsString = new ArrayList<String>();
	private List<String> ordersString = new ArrayList<String>();

	public void build() {
		for (ConditionElement cond : getConditions()) {
			if (cond instanceof ConditionItem) {
				StringBuilder sb = new StringBuilder();
				buildPath(((ConditionItem) cond).getItem(), sb);
				sb.append(" ").append(((ConditionItem) cond).getType()).append(" ").append(((ConditionItem) cond).getValue());
				conditionsString.add(sb.toString());
			}
		}
		for (Order ord : getOrders()) {
			StringBuilder sb = new StringBuilder();
			buildPath(ord.getItem(), sb);
			if (ord.getType() != null)
				sb.append(" ").append(ord.getType());
			ordersString.add(sb.toString());
		}
		for (Projection proj : getProjections()) {
			StringBuilder sb = new StringBuilder();
			buildPath(proj.getItem(), sb);
			sb.append(" ").append(proj.getType());
			projectionsString.add(sb.toString());
		}
	}

	public List<String> getConditionsString() {
		return conditionsString;
	}

	public List<String> getOrdersString() {
		return ordersString;
	}

	public List<String> getProjectionsString() {
		return projectionsString;
	}
}
