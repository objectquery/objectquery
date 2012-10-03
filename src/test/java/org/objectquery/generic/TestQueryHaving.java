package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.ObjectQuery;
import org.objectquery.generic.domain.Person;

public class TestQueryHaving {

	@Test
	public void testSimpleHanving() {
		TestQueryBuilder builder = new TestQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder,Person.class);
		
		Person target = query.target();
		query.having(target.getName(), ProjectionType.COUNT).eq(2D);
		builder.build();
		Assert.assertEquals(1,builder.getHavingString().size());
		Assert.assertEquals("name COUNT EQUALS 2.0",builder.getHavingString().get(0));
		
	}

	@Test
	public void testAllType() {
		TestQueryBuilder builder = new TestQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder,Person.class);
		
		Person target = query.target();
		query.having(target.getName(), ProjectionType.COUNT).eq(2D);
		query.having(target.getName(), ProjectionType.MIN).eq(2D);
		query.having(target.getName(), ProjectionType.MAX).eq(2D);
		query.having(target.getName(), ProjectionType.AVG).eq(2D);
		query.having(target.getName(), ProjectionType.SUM).eq(2D);
		builder.build();
		Assert.assertEquals(5,builder.getHavingString().size());
		Assert.assertEquals("name COUNT EQUALS 2.0",builder.getHavingString().get(0));
		Assert.assertEquals("name MIN EQUALS 2.0",builder.getHavingString().get(1));
		Assert.assertEquals("name MAX EQUALS 2.0",builder.getHavingString().get(2));
		Assert.assertEquals("name AVG EQUALS 2.0",builder.getHavingString().get(3));
		Assert.assertEquals("name SUM EQUALS 2.0",builder.getHavingString().get(4));

	}

	@Test
	public void testAllCondition() {
		TestQueryBuilder builder = new TestQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder,Person.class);
		
		Person target = query.target();
		query.having(target.getName(), ProjectionType.COUNT).eq(2D);
		query.having(target.getName(), ProjectionType.COUNT).notEq(2D);
		query.having(target.getName(), ProjectionType.COUNT).max(2D);
		query.having(target.getName(), ProjectionType.COUNT).maxEq(2D);
		query.having(target.getName(), ProjectionType.COUNT).min(2D);
		query.having(target.getName(), ProjectionType.COUNT).minEq(2D);
		builder.build();
		Assert.assertEquals(6,builder.getHavingString().size());
		Assert.assertEquals("name COUNT EQUALS 2.0",builder.getHavingString().get(0));
		Assert.assertEquals("name COUNT NOT_EQUALS 2.0",builder.getHavingString().get(1));
		Assert.assertEquals("name COUNT MAX 2.0",builder.getHavingString().get(2));
		Assert.assertEquals("name COUNT MAX_EQUALS 2.0",builder.getHavingString().get(3));
		Assert.assertEquals("name COUNT MIN 2.0",builder.getHavingString().get(4));
		Assert.assertEquals("name COUNT MIN_EQUALS 2.0",builder.getHavingString().get(5));
	}

}

