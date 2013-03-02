package org.objectquery.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MockQueryBuilder extends GenericInternalQueryBuilder {

	public MockQueryBuilder() {
		super(GroupType.AND);
	}

	private List<String> conditionsString = new ArrayList<String>();
	private List<String> projectionsString = new ArrayList<String>();
	private List<String> ordersString = new ArrayList<String>();
	private List<String> havingString = new ArrayList<String>();

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

	private void stringfyQuery(GenericObjectQuery<?> goq, StringBuilder builder) {
		GenericInternalQueryBuilder iqb = (GenericInternalQueryBuilder) goq.getBuilder();
		List<String> conditionsString = new ArrayList<String>();
		List<String> ordersString = new ArrayList<String>();
		List<String> projectionsString = new ArrayList<String>();
		List<String> havingString = new ArrayList<String>();
		build(iqb.getConditions(), iqb.getOrders(), iqb.getProjections(), iqb.getHavings(), conditionsString, ordersString, projectionsString, havingString);
		builder.append("select ");
		Iterator<String> iter = null;
		if (!projectionsString.isEmpty()) {
			iter = projectionsString.iterator();
			while (iter.hasNext()) {
				builder.append(iter.next());
				if (iter.hasNext())
					builder.append(",");
			}
		}
		builder.append(" from ").append(goq.getTargetClass().getSimpleName());
		if (!conditionsString.isEmpty()) {
			builder.append(" where ");
			iter = conditionsString.iterator();
			while (iter.hasNext()) {
				builder.append(iter.next());
				if (iter.hasNext())
					builder.append(" ").append(iqb.getType().toString()).append(" ");
			}
		}

		if (!havingString.isEmpty()) {
			builder.append(" having ");
			while (iter.hasNext()) {
				builder.append(iter.next());
				if (iter.hasNext()) {
					// TODO:Fix when refactored for support of different
					// grouping operator in having condition.
					builder.append(" AND ");
				}
			}
		}

		if (!ordersString.isEmpty()) {
			builder.append(" order by ");
			while (iter.hasNext()) {
				builder.append(iter.next());
				if (iter.hasNext())
					builder.append(",");
			}
		}

	}

	private void stringfyCondition(ConditionItem cond, StringBuilder sb) {
		buildPath(cond.getItem(), sb);
		sb.append(" ").append(cond.getType()).append(" ");
		if (cond.getValue() instanceof PathItem)
			buildPath((PathItem) cond.getValue(), sb);
		else if (cond.getValue() instanceof GenericObjectQuery<?>) {
			stringfyQuery((GenericObjectQuery<?>) cond.getValue(), sb);
		} else
			sb.append(cond.getValue());
	}

	public void build() {
		build(getConditions(), getOrders(), getProjections(), getHavings(), conditionsString, ordersString, projectionsString, havingString);
	}

	private void build(List<ConditionElement> conditions, List<Order> orders, List<Projection> projections, List<Having> havings,
			List<String> conditionsString, List<String> ordersString, List<String> projectionsString, List<String> havingString) {

		for (ConditionElement cond : conditions) {
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
		for (Order ord : orders) {
			StringBuilder sb = new StringBuilder();
			buildPath(ord.getItem(), sb);
			if (ord.getProjectionType() != null)
				sb.append(" ").append(ord.getProjectionType());
			if (ord.getType() != null)
				sb.append(" ").append(ord.getType());
			ordersString.add(sb.toString());
		}
		for (Projection proj : projections) {
			StringBuilder sb = new StringBuilder();
			buildPath(proj.getItem(), sb);
			if (proj.getType() != null)
				sb.append(" ").append(proj.getType());
			projectionsString.add(sb.toString());
		}
		for (Having having : havings) {
			StringBuilder sb = new StringBuilder();
			buildPath(having.getItem(), sb);
			sb.append(" ").append(having.getProjectionType());
			sb.append(" ").append(having.getConditionType());
			sb.append(" ").append(having.getValue());
			havingString.add(sb.toString());
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

	public List<String> getHavingString() {
		return havingString;
	}

	@Override
	public void clear() {
		super.clear();
		conditionsString.clear();
		ordersString.clear();
		projectionsString.clear();
		havingString.clear();
	}
}
