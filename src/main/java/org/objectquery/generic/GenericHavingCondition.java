package org.objectquery.generic;

import org.objectquery.HavingCondition;

public class GenericHavingCondition implements HavingCondition {
	private InternalQueryBuilder builder;
	private PathItem item;
	private ProjectionType type;

	public GenericHavingCondition(InternalQueryBuilder builder, PathItem item, ProjectionType type) {
		this.builder = builder;
		this.item = item;
		this.type = type;
	}

	public void eq(Double value) {
		builder.having(item, type, ConditionType.EQUALS, value);
	}

	public void notEq(Double value) {
		builder.having(item, type, ConditionType.NOT_EQUALS, value);
	}

	public void max(Double value) {
		builder.having(item, type, ConditionType.MAX, value);
	}

	public void maxEq(Double value) {
		builder.having(item, type, ConditionType.MAX_EQUALS, value);
	}

	public void min(Double value) {
		builder.having(item, type, ConditionType.MIN, value);
	}

	public void minEq(Double value) {
		builder.having(item, type, ConditionType.MIN_EQUALS, value);
	}

}
