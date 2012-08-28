package org.objectquery.builder;

import junit.framework.Assert;

import org.junit.Test;
import org.objectquery.builder.domain.Person;

public class TestQueryOrder {

	@Test
	public void testOrders() {
		TestQueryBuilder builder = new TestQueryBuilder();
		ObjectQuery<Person> query = new AbstractObjectQuery<Person>(builder, Person.class);
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

}
