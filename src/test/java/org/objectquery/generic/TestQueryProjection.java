package org.objectquery.generic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.objectquery.SelectQuery;
import org.objectquery.generic.domain.Person;

public class TestQueryProjection {

	@Test
	public void addProjectionTest() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(builder, Person.class);
		Person toSearch = query.target();
		query.prj(toSearch.getMom().getName());
		query.prj(toSearch.getMom().getName(), ProjectionType.COUNT);
		query.prj(toSearch.getMom().getName(), ProjectionType.AVG);
		query.prj(toSearch.getMom().getName(), ProjectionType.MAX);
		query.prj(toSearch.getMom().getName(), ProjectionType.MIN);
		query.prj(toSearch.getMom().getName(), ProjectionType.SUM);
		builder.build();

		assertEquals(6, builder.getProjectionsString().size());
		assertEquals("mom.name", builder.getProjectionsString().get(0));
		assertEquals("mom.name COUNT", builder.getProjectionsString().get(1));
		assertEquals("mom.name AVG", builder.getProjectionsString().get(2));
		assertEquals("mom.name MAX", builder.getProjectionsString().get(3));
		assertEquals("mom.name MIN", builder.getProjectionsString().get(4));
		assertEquals("mom.name SUM", builder.getProjectionsString().get(5));
	}

	@Test
	public void addSubqueryProjectionTest() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(builder, Person.class);
		query.prj(query.subQuery(Person.class));
		query.prj(query.subQuery(Person.class), ProjectionType.SUM);
		builder.build();

		assertEquals(2, builder.getProjectionsString().size());
		assertEquals("select  from Person AA0", builder.getProjectionsString().get(0));
		assertEquals("select  from Person AA1 SUM", builder.getProjectionsString().get(1));
	}

}
