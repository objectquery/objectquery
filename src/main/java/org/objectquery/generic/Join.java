package org.objectquery.generic;

public class Join {

	private PathItem root;
	private Class<?> baseType;
	private PathItem joinPath;

	public Join(PathItem root, Class<?> baseType, PathItem joinPath) {
		this.root = root;
		this.baseType = baseType;
		this.joinPath = joinPath;
	}

	public PathItem getRoot() {
		return root;
	}

	public Class<?> getBaseType() {
		return baseType;
	}

	public PathItem getJoinPath() {
		return joinPath;
	}

}
