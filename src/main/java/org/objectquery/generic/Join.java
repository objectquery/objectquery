package org.objectquery.generic;

public class Join {

	private PathItem root;
	private PathItem joinPath;
	private JoinType type;

	public Join(PathItem root, PathItem joinPath, JoinType type) {
		this.root = root;
		this.joinPath = joinPath;
		this.type = type;
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

}
