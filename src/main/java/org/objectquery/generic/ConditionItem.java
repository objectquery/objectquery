package org.objectquery.generic;

public class ConditionItem implements ConditionElement {

	private PathItem item;
	private ConditionType type;
	private Object value;
	private Object valueTo;

	public ConditionItem(PathItem item, ConditionType type, Object value, Object value1) {
		this.item = item;
		this.type = type;
		this.value = value;
		this.valueTo = value1;
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

	public Object getValueTo() {
		return valueTo;
	}

	public void clear() {
		item.clear();
		item = null;
		if (value instanceof PathItem)
			((PathItem) value).clear();
		else if (value instanceof GenericSelectQuery<?, ?>)
			((GenericSelectQuery<?, ?>) value).clear();
		value = null;
	}
}
