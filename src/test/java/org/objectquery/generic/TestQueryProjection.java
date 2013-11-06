package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.SelectQuery;
import org.objectquery.generic.domain.Person;

public class TestQueryProjection {

	@Test
	public void addProjectionTest() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();
		query.prj(toSearch.getMom().getName());
		query.prj(toSearch.getMom().getName(), ProjectionType.COUNT);
		query.prj(toSearch.getMom().getName(), ProjectionType.AVG);
		query.prj(toSearch.getMom().getName(), ProjectionType.MAX);
		query.prj(toSearch.getMom().getName(), ProjectionType.MIN);
		query.prj(toSearch.getMom().getName(), ProjectionType.SUM);
		builder.build();

		Assert.assertEquals(6, builder.getProjectionsString().size());
		Assert.assertEquals("mom.name", builder.getProjectionsString().get(0));
		Assert.assertEquals("mom.name COUNT", builder.getProjectionsString().get(1));
		Assert.assertEquals("mom.name AVG", builder.getProjectionsString().get(2));
		Assert.assertEquals("mom.name MAX", builder.getProjectionsString().get(3));
		Assert.assertEquals("mom.name MIN", builder.getProjectionsString().get(4));
		Assert.assertEquals("mom.name SUM", builder.getProjectionsString().get(5));
	}

	@Test
	public void addSubqueryProjectionTest() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person>(builder, Person.class);
		query.prj(query.subQuery(Person.class));
		query.prj(query.subQuery(Person.class), ProjectionType.SUM);
		builder.build();

		Assert.assertEquals(2, builder.getProjectionsString().size());
		Assert.assertEquals("select  from Person AA0", builder.getProjectionsString().get(0));
		Assert.assertEquals("select  from Person AA1 SUM", builder.getProjectionsString().get(1));
	}

}
