package org.objectquery.generic;

public class SetValue {
	private PathItem target;
	private Object value;

	public SetValue(PathItem target, Object value) {
		super();
		this.target = target;
		this.value = value;
	}

	public PathItem getTarget() {
		return target;
	}

	public void setTarget(PathItem target) {
		this.target = target;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
