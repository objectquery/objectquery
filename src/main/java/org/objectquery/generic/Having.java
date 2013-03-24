package org.objectquery.generic;

import org.objectquery.ObjectQuery;

public class Having {
	private Object item;
	private ProjectionType projectionType;
	private ConditionType conditionType;
	private Object value;

	public Having(PathItem item, ProjectionType projectionType, ConditionType conditionType, Object value) {
		this.item = item;
		this.projectionType = projectionType;
		this.conditionType = conditionType;
		this.value = value;
	}

	public Having(ObjectQuery<?> item, ProjectionType projectionType, ConditionType conditionType, Object value) {
		this.item = item;
		this.projectionType = projectionType;
		this.conditionType = conditionType;
		this.value = value;
	}

	public Object getItem() {
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
		if (item instanceof PathItem)
			((PathItem) item).clear();
		else if (item instanceof GenericObjectQuery<?>)
			((GenericObjectQuery<?>) item).clear();
		item = null;
		if (value instanceof PathItem)
			((PathItem) value).clear();
		value = null;
	}

}
