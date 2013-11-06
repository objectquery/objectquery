package org.objectquery.generic;

@Deprecated
public class GenericObjectQuery<T> extends GenericSelectQuery<T> {

	@Deprecated
	public GenericObjectQuery(Class<T> targetClass) {
		this(new GenericInternalQueryBuilder(GroupType.AND), targetClass);
	}

	@Deprecated
	public GenericObjectQuery(InternalQueryBuilder builder, Class<T> targetClass) {
		super(builder, targetClass);
	}
}
