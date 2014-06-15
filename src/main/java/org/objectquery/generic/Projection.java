package org.objectquery.generic;

import org.objectquery.BaseSelectQuery;

public class Projection {

	private Object item;
	private ProjectionType type;
	private PathItem mapper;

	public Projection(BaseSelectQuery<?> item, PathItem mapper, ProjectionType type) {
		this.item = item;
		this.type = type;
		this.mapper = mapper;
	}

	public Projection(PathItem item, PathItem mapper, ProjectionType type) {
		this.item = item;
		this.type = type;
		this.mapper = mapper;
	}

	public Object getItem() {
		return item;
	}

	public PathItem getMapper() {
		return mapper;
	}

	public ProjectionType getType() {
		return type;
	}

	public void clear() {
		if (item instanceof GenericSelectQuery<?, ?>)
			((GenericSelectQuery<?, ?>) item).clear();
		else
			((PathItem) item).clear();
		item = null;
	}

}
