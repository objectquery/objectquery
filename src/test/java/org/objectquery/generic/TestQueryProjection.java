package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.ObjectQuery;
import org.objectquery.generic.GenericObjectQuery;
import org.objectquery.generic.ProjectionType;
import org.objectquery.generic.domain.Person;

public class TestQueryProjection {

	@Test
	public void addProjectionTest() {
		MockQueryBuilder builder = new MockQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();
		query.prj(toSearch.getMum().getName());
		query.prj(toSearch.getMum().getName(), ProjectionType.COUNT);
		query.prj(toSearch.getMum().getName(), ProjectionType.AVG);
		query.prj(toSearch.getMum().getName(), ProjectionType.MAX);
		query.prj(toSearch.getMum().getName(), ProjectionType.MIN);
		query.prj(toSearch.getMum().getName(), ProjectionType.SUM);
		builder.build();

		Assert.assertEquals(6, builder.getProjectionsString().size());
		Assert.assertEquals("mum.name", builder.getProjectionsString().get(0));
		Assert.assertEquals("mum.name COUNT", builder.getProjectionsString().get(1));
		Assert.assertEquals("mum.name AVG", builder.getProjectionsString().get(2));
		Assert.assertEquals("mum.name MAX", builder.getProjectionsString().get(3));
		Assert.assertEquals("mum.name MIN", builder.getProjectionsString().get(4));
		Assert.assertEquals("mum.name SUM", builder.getProjectionsString().get(5));
	}
}
