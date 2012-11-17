package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.ObjectQuery;
import org.objectquery.generic.domain.Person;

public class TestRequiredSpeed {

	@Test
	public void testSpeed() {
		long time = System.currentTimeMillis();
		for (int i = 0; i < 400000; i++) {
			buildQuery();
		}
		System.out.println(((System.currentTimeMillis() - time)));
		Assert.assertTrue(((System.currentTimeMillis() - time) / 1000) < 3600);
	}

	public void buildQuery() {
		TestQueryBuilder builder = new TestQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();
		query.eq(toSearch.getHome().getAddress(), toSearch.getDog().getHome().getAddress());
		query.eq(toSearch.getDud().getHome(), toSearch.getDog().getHome());
		query.eq(toSearch.getMum().getName(), toSearch.getDog().getOwner().getName());
		query.order(toSearch.getName());

		builder.build();
	}

}
