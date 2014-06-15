package org.objectquery.generic;

import junit.framework.Assert;

import org.junit.Test;
import org.objectquery.SelectQuery;
import org.objectquery.generic.domain.Person;

public class TestQueryOrder {

	@Test
	public void testOrders() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person,Object>(builder, Person.class);
		Person toSearch = query.target();

		query.order(toSearch.getDog().getName());
		query.order(toSearch.getDog().getName(), OrderType.ASC);
		query.order(toSearch.getDog().getName(), OrderType.DESC);
		builder.build();

		Assert.assertEquals(3, builder.getOrdersString().size());
		Assert.assertEquals("dog.name", builder.getOrdersString().get(0));
		Assert.assertEquals("dog.name ASC", builder.getOrdersString().get(1));
		Assert.assertEquals("dog.name DESC", builder.getOrdersString().get(2));

	}

	@Test
	public void testOrdersProjection() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person,Object>(builder, Person.class);
		Person toSearch = query.target();

		query.order(toSearch.getDog().getName(), ProjectionType.COUNT, OrderType.ASC);
		query.order(toSearch.getDog().getName(), ProjectionType.MAX, OrderType.DESC);
		builder.build();

		Assert.assertEquals(2, builder.getOrdersString().size());
		Assert.assertEquals("dog.name COUNT ASC", builder.getOrdersString().get(0));
		Assert.assertEquals("dog.name MAX DESC", builder.getOrdersString().get(1));
	}

	@Test
	public void testOrdersSubquery() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person,Object>(builder, Person.class);

		query.order(query.subQuery(Person.class), ProjectionType.COUNT, OrderType.ASC);
		query.order(query.subQuery(Person.class), ProjectionType.MAX, OrderType.DESC);
		builder.build();

		Assert.assertEquals(2, builder.getOrdersString().size());
		Assert.assertEquals("select  from Person AA0 COUNT ASC", builder.getOrdersString().get(0));
		Assert.assertEquals("select  from Person AA1 MAX DESC", builder.getOrdersString().get(1));
	}

}
