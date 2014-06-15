package org.objectquery.generic;

import org.objectquery.BaseSelectQuery;
import org.objectquery.HavingCondition;

public class GenericHavingCondition implements HavingCondition {
	private InternalQueryBuilder builder;
	private GenericSelectQuery<?, ?> objectQuery;
	private Object item;
	private ProjectionType type;

	public GenericHavingCondition(InternalQueryBuilder builder, GenericSelectQuery<?, ?> objectQuery, PathItem item, ProjectionType type) {
		this.builder = builder;
		this.item = item;
		this.type = type;
		this.objectQuery = objectQuery;
	}

	public GenericHavingCondition(InternalQueryBuilder builder, GenericSelectQuery<?, ?> objectQuery, BaseSelectQuery<?> item, ProjectionType type) {
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
			builder.having((BaseSelectQuery<?>) item, type, conditionType, curValue);
	}

	public void eq(Double value) {
		having(ConditionType.EQUALS, value);
	}

	public void notEq(Double value) {
		having(ConditionType.NOT_EQUALS, value);
	}

	public void gt(Double value) {
		having(ConditionType.GREATER, value);
	}

	public void gtEq(Double value) {
		having(ConditionType.GREATER_EQUALS, value);
	}

	public void lt(Double value) {
		having(ConditionType.LESS, value);
	}

	public void ltEq(Double value) {
		having(ConditionType.LESS_EQUALS, value);
	}

	@Deprecated
	public void max(Double value) {
		having(ConditionType.GREATER, value);
	}

	@Deprecated
	public void maxEq(Double value) {
		having(ConditionType.GREATER_EQUALS, value);
	}

	@Deprecated
	public void min(Double value) {
		having(ConditionType.LESS, value);
	}

	@Deprecated
	public void minEq(Double value) {
		having(ConditionType.LESS_EQUALS, value);
	}

}
