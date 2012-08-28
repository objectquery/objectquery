package org.objectquery.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestQueryBuilder extends GenericInternalQueryBuilder {

	public TestQueryBuilder() {
		super(GroupType.AND);
	}

	private List<String> conditionsString = new ArrayList<String>();
	private List<String> projectionsString = new ArrayList<String>();
	private List<String> ordersString = new ArrayList<String>();

	private void stringfyGroup(ConditionGroup group, StringBuilder builder) {
		if (!group.getConditions().isEmpty()) {
			builder.append(" ( ");
			Iterator<ConditionElement> eli = group.getConditions().iterator();
			while (eli.hasNext()) {
				ConditionElement el = eli.next();
				if (el instanceof ConditionItem) {
					stringfyCondition((ConditionItem) el, builder);
				} else if (el instanceof ConditionGroup) {
					stringfyGroup((ConditionGroup) el, builder);
				}
				if (eli.hasNext()) {
					builder.append(" ").append(group.getType().toString()).append(" ");
				}
			}
			builder.append(" ) ");
		}
	}

	private void stringfyCondition(ConditionItem cond, StringBuilder sb) {
		buildPath(cond.getItem(), sb);
		sb.append(" ").append(cond.getType()).append(" ");
		if (cond.getValue() instanceof PathItem)
			buildPath((PathItem) cond.getValue(), sb);
		else
			sb.append(cond.getValue());
	}

	public void build() {
		for (ConditionElement cond : getConditions()) {
			if (cond instanceof ConditionItem) {
				StringBuilder sb = new StringBuilder();
				stringfyCondition((ConditionItem) cond, sb);
				conditionsString.add(sb.toString());
			} else if (cond instanceof ConditionGroup) {
				StringBuilder sb = new StringBuilder();
				stringfyGroup((ConditionGroup) cond, sb);
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
			if (proj.getType() != null)
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
