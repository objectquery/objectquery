package org.objectquery.generic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.objectquery.SelectQuery;
import org.objectquery.generic.domain.Person;

public class TestQueryHaving {

	@Test
	public void testSimpleHanving() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(builder, Person.class);

		Person target = query.target();
		query.having(target.getName(), ProjectionType.COUNT).eq(2D);
		builder.build();
		assertEquals(1, builder.getHavingString().size());
		assertEquals("name COUNT EQUALS 2.0", builder.getHavingString().get(0));

	}

	@Test
	public void testAllType() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(builder, Person.class);

		Person target = query.target();
		query.having(target.getName(), ProjectionType.COUNT).eq(2D);
		query.having(target.getName(), ProjectionType.MIN).eq(2D);
		query.having(target.getName(), ProjectionType.MAX).eq(2D);
		query.having(target.getName(), ProjectionType.AVG).eq(2D);
		query.having(target.getName(), ProjectionType.SUM).eq(2D);
		builder.build();
		assertEquals(5, builder.getHavingString().size());
		assertEquals("name COUNT EQUALS 2.0", builder.getHavingString().get(0));
		assertEquals("name MIN EQUALS 2.0", builder.getHavingString().get(1));
		assertEquals("name MAX EQUALS 2.0", builder.getHavingString().get(2));
		assertEquals("name AVG EQUALS 2.0", builder.getHavingString().get(3));
		assertEquals("name SUM EQUALS 2.0", builder.getHavingString().get(4));

	}

	@Test
	public void testAllCondition() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(builder, Person.class);

		Person target = query.target();
		query.having(target.getName(), ProjectionType.COUNT).eq(2D);
		query.having(target.getName(), ProjectionType.COUNT).notEq(2D);
		query.having(target.getName(), ProjectionType.COUNT).max(2D);
		query.having(target.getName(), ProjectionType.COUNT).maxEq(2D);
		query.having(target.getName(), ProjectionType.COUNT).min(2D);
		query.having(target.getName(), ProjectionType.COUNT).minEq(2D);
		builder.build();
		assertEquals(6, builder.getHavingString().size());
		assertEquals("name COUNT EQUALS 2.0", builder.getHavingString().get(0));
		assertEquals("name COUNT NOT_EQUALS 2.0", builder.getHavingString().get(1));
		assertEquals("name COUNT GREATER 2.0", builder.getHavingString().get(2));
		assertEquals("name COUNT GREATER_EQUALS 2.0", builder.getHavingString().get(3));
		assertEquals("name COUNT LESS 2.0", builder.getHavingString().get(4));
		assertEquals("name COUNT LESS_EQUALS 2.0", builder.getHavingString().get(5));
	}

}
