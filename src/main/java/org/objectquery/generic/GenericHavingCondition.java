package org.objectquery.generic;

import org.objectquery.HavingCondition;

public class GenericHavingCondition implements HavingCondition {
	private InternalQueryBuilder builder;
	private GenericObjectQuery<?> objectQuery;
	private PathItem item;
	private ProjectionType type;

	public GenericHavingCondition(InternalQueryBuilder builder, GenericObjectQuery<?> objectQuery, PathItem item, ProjectionType type) {
		this.builder = builder;
		this.item = item;
		this.type = type;
		this.objectQuery = objectQuery;
	}

	private void having(PathItem item, ProjectionType projectionType, ConditionType conditionType, Object value) {
		Object curValue;
		if ((curValue = objectQuery.unproxable.get(value)) == null)
			curValue = value;
		builder.having(item, type, conditionType, curValue);
	}

	public void eq(Double value) {
		having(item, type, ConditionType.EQUALS, value);
	}

	public void notEq(Double value) {
		having(item, type, ConditionType.NOT_EQUALS, value);
	}

	public void max(Double value) {
		having(item, type, ConditionType.GREATER, value);
	}

	public void maxEq(Double value) {
		having(item, type, ConditionType.GREATER_EQUALS, value);
	}

	public void min(Double value) {
		having(item, type, ConditionType.LESS, value);
	}

	public void minEq(Double value) {
		having(item, type, ConditionType.LESS_EQUALS, value);
	}

}
