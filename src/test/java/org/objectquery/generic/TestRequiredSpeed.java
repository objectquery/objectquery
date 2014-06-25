package org.objectquery.generic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.objectquery.generic.domain.Person;

public class TestRequiredSpeed {

	// @Test
	public void testSpeed() {
		long time = System.currentTimeMillis();
		long ptime = System.currentTimeMillis();

		int size = 2000000;
		int perc = size / 100;

		for (int i = 0; i < size; i++) {
			if (i % perc == 0) {
				System.out.println((i / perc) + " " + ((System.currentTimeMillis() - ptime)));
				ptime = System.currentTimeMillis();
			}
			buildQuery();
		}
		System.out.println(((System.currentTimeMillis() - time)));
		System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024 + "Mb");
		assertTrue(((System.currentTimeMillis() - time) / 1000) < 3600);
	}

	public void buildQuery() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(builder, Person.class);

		Person toSearch = query.target();
		query.eq(toSearch.getHome().getAddress(), toSearch.getDog().getHome().getAddress());
		query.eq(toSearch.getDad().getHome(), toSearch.getDog().getHome());
		query.eq(toSearch.getMom().getName(), toSearch.getDog().getOwner().getName());
		query.order(toSearch.getName());
		builder.build();
		assertEquals("size of result conditions", 3, builder.getConditionsString().size());
		assertEquals("first condition", "home.address EQUALS dog.home.address", builder.getConditionsString().get(0));
		assertEquals("second condition", "dud.home EQUALS dog.home", builder.getConditionsString().get(1));
		assertEquals("thirdth condition", "mum.name EQUALS dog.owner.name", builder.getConditionsString().get(2));
		query.clear();
	}

}
