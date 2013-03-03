package org.objectquery.generic;

import org.objectquery.ObjectQuery;

public class Projection {

	private Object item;
	private ProjectionType type;

	public Projection(ObjectQuery<?> item, ProjectionType type) {
		this.item = item;
		this.type = type;
	}

	public Projection(PathItem item, ProjectionType type) {
		this.item = item;
		this.type = type;
	}

	public Object getItem() {
		return item;
	}

	public ProjectionType getType() {
		return type;
	}

	public void clear() {
		if (item instanceof GenericObjectQuery<?>)
			((GenericObjectQuery<?>) item).clear();
		else
			((PathItem) item).clear();
		item = null;
	}

}
