package org.objectquery.builder;

public abstract class AbstractInternalQueryBuilder implements InternalQueryBuilder {

	protected abstract void condition(String item, ConditionType type, Object value);

	protected abstract void order(String path, OrderType type);

	protected abstract void projection(String path, ProjectionType type);

	private void buildPath(PathItem item, StringBuilder builder) {
		if (item.getParent() != null) {
			buildPath(item.getParent(), builder);
			builder.append(".");
		}
		builder.append(item.getName());
	}

	public void condition(PathItem item, ConditionType type, Object value) {
		StringBuilder builder = new StringBuilder();
		buildPath(item, builder);
		condition(builder.toString(), type, value);
	}

	public void order(PathItem item, OrderType type) {
		StringBuilder builder = new StringBuilder();
		buildPath(item, builder);
		order(builder.toString(), type);
	}

	public void order(PathItem item) {
		StringBuilder builder = new StringBuilder();
		buildPath(item, builder);
		order(builder.toString(), null);
	}

	public void projection(PathItem item) {
		StringBuilder builder = new StringBuilder();
		buildPath(item, builder);
		projection(builder.toString(), null);
	}

	public void projection(PathItem item, ProjectionType type) {
		StringBuilder builder = new StringBuilder();
		buildPath(item, builder);
		projection(builder.toString(), type);
	}

}
