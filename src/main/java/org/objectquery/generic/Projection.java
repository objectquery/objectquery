package org.objectquery.generic;

import org.objectquery.SelectQuery;

public class Projection {

	private Object item;
	private ProjectionType type;

	public Projection(SelectQuery<?> item, ProjectionType type) {
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
		if (item instanceof GenericSelectQuery<?>)
			((GenericSelectQuery<?>) item).clear();
		else
			((PathItem) item).clear();
		item = null;
	}

}
