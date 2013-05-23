package org.objectquery.generic;

public class Join {

	private PathItem root;
	private PathItem joinPath;
	private JoinType type;
	private Class<?> javaType;

	public Join(PathItem root, PathItem joinPath, JoinType type, Class<?> javaType) {
		this.root = root;
		this.joinPath = joinPath;
		this.type = type;
		this.javaType = javaType;
	}

	public PathItem getRoot() {
		return root;
	}

	public PathItem getJoinPath() {
		return joinPath;
	}

	public JoinType getType() {
		return type;
	}

	public Class<?> getJavaType() {
		return javaType;
	}

}
