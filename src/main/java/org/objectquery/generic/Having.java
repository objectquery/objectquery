package org.objectquery.generic;

public class Having {
	private PathItem item;
	private ProjectionType projectionType;
	private ConditionType conditionType;
	private Object value;

	public Having(PathItem item, ProjectionType projectionType, ConditionType conditionType, Object value) {
		super();
		this.item = item;
		this.projectionType = projectionType;
		this.conditionType = conditionType;
		this.value = value;
	}

	public PathItem getItem() {
		return item;
	}

	public ProjectionType getProjectionType() {
		return projectionType;
	}

	public ConditionType getConditionType() {
		return conditionType;
	}

	public Object getValue() {
		return value;
	}

	public void clear() {
		item.clear();
		item = null;
		if (value instanceof PathItem)
			((PathItem) value).clear();
		value = null;
	}

}
