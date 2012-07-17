package org.objectquery.builder;

public class PathItem {

	private Class<?> clazz;
	private ObjectQueryHandler parent;
	private String name;

	public PathItem(Class<?> clazz, ObjectQueryHandler parent, String name) {
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

	public ObjectQueryHandler getParent() {
		return parent;
	}

}
