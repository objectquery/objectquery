package org.objectquery;

import org.objectquery.generic.ProjectionType;

public interface SelectMapQuery<T, M> extends BaseSelectQuery<T> {

	/**
	 * Create a mapper for map the results of a query to another class. if you
	 * create a mapper all the projection have to be associated with a filed of
	 * the mapper.
	 * 
	 * @return the mapping object to use in the projection.
	 */
	M mapper();

	/**
	 * Add a projection to a query mapping the return value.
	 * 
	 * Example:<code>
	 * SelectMapQuery&lt;Person,PersonDTO&gt; query = ....
	 * PersonDTO mapTo = query.mapper();
	 * query.prj(query.target().getName(),mapTo.getName());
	 * </code>
	 * 
	 * @param projection
	 *            the projection to add.
	 * @param mapper
	 *            the corresponding field to map.
	 */
	<PT> void prj(PT projection, PT mapper);

	/**
	 * Add a projection to a query mapping the return value.
	 * 
	 * Example:<code>
	 * SelectQuery&lt;Person,PersonDTO&gt; query = ....
	 * PersonDTO mapTo = query.mapper(PersonDto.class);
	 * query.prj(query.target().getName(),mapTo.getNameCount(),ProjectionType.COUNT);
	 * </code>
	 * 
	 * @param projection
	 *            the projection to add.
	 * @param mapper
	 *            the corresponding field to map.
	 * @param type
	 *            the type of projection to add.
	 */
	<PT> void prj(PT projection, PT mapper, ProjectionType type);

}
