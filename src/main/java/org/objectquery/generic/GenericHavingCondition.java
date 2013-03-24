package org.objectquery.generic;

import org.objectquery.HavingCondition;
import org.objectquery.ObjectQuery;

public class GenericHavingCondition implements HavingCondition {
	private InternalQueryBuilder builder;
	private GenericObjectQuery<?> objectQuery;
	private Object item;
	private ProjectionType type;

	public GenericHavingCondition(InternalQueryBuilder builder, GenericObjectQuery<?> objectQuery, PathItem item, ProjectionType type) {
		this.builder = builder;
		this.item = item;
		this.type = type;
		this.objectQuery = objectQuery;
	}

	public GenericHavingCondition(InternalQueryBuilder builder, GenericObjectQuery<?> objectQuery, ObjectQuery<?> item, ProjectionType type) {
		this.builder = builder;
		this.item = item;
		this.type = type;
		this.objectQuery = objectQuery;
	}

	private void having(ConditionType conditionType, Object value) {
		Object curValue;
		if ((curValue = objectQuery.unproxable.get(value)) == null)
			curValue = value;
		if (item instanceof PathItem)
			builder.having((PathItem) item, type, conditionType, curValue);
		else
			builder.having((ObjectQuery<?>) item, type, conditionType, curValue);
	}

	public void eq(Double value) {
		having(ConditionType.EQUALS, value);
	}

	public void notEq(Double value) {
		having(ConditionType.NOT_EQUALS, value);
	}

	public void max(Double value) {
		having(ConditionType.GREATER, value);
	}

	public void maxEq(Double value) {
		having(ConditionType.GREATER_EQUALS, value);
	}

	public void min(Double value) {
		having(ConditionType.LESS, value);
	}

	public void minEq(Double value) {
		having(ConditionType.LESS_EQUALS, value);
	}

}
