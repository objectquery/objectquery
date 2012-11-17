package org.objectquery.generic;

public class ConditionItem implements ConditionElement {

	private PathItem item;
	private ConditionType type;
	private Object value;

	public ConditionItem(PathItem item, ConditionType type, Object value) {
		this.item = item;
		this.type = type;
		this.value = value;
	}

	public PathItem getItem() {
		return item;
	}

	public ConditionType getType() {
		return type;
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
