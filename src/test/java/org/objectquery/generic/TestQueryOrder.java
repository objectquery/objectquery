package org.objectquery.generic;

import junit.framework.Assert;

import org.junit.Test;
import org.objectquery.ObjectQuery;
import org.objectquery.generic.GenericObjectQuery;
import org.objectquery.generic.OrderType;
import org.objectquery.generic.ProjectionType;
import org.objectquery.generic.domain.Person;

public class TestQueryOrder {

	@Test
	public void testOrders() {
		TestQueryBuilder builder = new TestQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
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
		TestQueryBuilder builder = new TestQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();

		query.order(toSearch.getDog().getName(), ProjectionType.COUNT, OrderType.ASC);
		query.order(toSearch.getDog().getName(), ProjectionType.MAX, OrderType.DESC);
		builder.build();

		Assert.assertEquals(2, builder.getOrdersString().size());
		Assert.assertEquals("dog.name COUNT ASC", builder.getOrdersString().get(0));
		Assert.assertEquals("dog.name MAX DESC", builder.getOrdersString().get(1));

	}

}
