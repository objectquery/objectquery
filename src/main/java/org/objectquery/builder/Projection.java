package org.objectquery.builder;

public class Projection {

	private PathItem item;
	private ProjectionType type;

	public Projection(PathItem item, ProjectionType type) {
		this.item = item;
		this.type = type;
	}

	public PathItem getItem() {
		return item;
	}

	public ProjectionType getType() {
		return type;
	}

}
