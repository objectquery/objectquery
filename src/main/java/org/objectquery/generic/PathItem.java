package org.objectquery.generic;

public class PathItem {

	private Class<?> clazz;
	private PathItem parent;
	private String name;

	public PathItem(Class<?> clazz, PathItem parent, String name) {
		this.clazz = clazz;
		this.parent = parent;
		this.name = name;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PathItem getParent() {
		return parent;
	}

	public void clear() {
		PathItem cur = this;
		while (cur != null) {
			PathItem n = cur.parent;
			cur.parent = null;
			cur.name = null;
			cur.clazz = null;
			cur = n;
		}
	}

}
