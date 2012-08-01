package org.objectquery.builder;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.builder.domain.Person;

public class TestQueryBuild {

	@Test
	public void testPathValueQueryBuild() {
		TestQueryBuilder builder = new TestQueryBuilder();
		ObjectQuery<Person> query = new AbstractObjectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();
		query.condition(toSearch.getHome().getAddress(), ConditionType.EQUALS, toSearch.getDog().getHome().getAddress());
		query.condition(toSearch.getMum().getName(), ConditionType.EQUALS, toSearch.getDog().getOwner().getName());
		query.order(toSearch.getName());

		builder.build();
		Assert.assertEquals("There is more conditions than expected", builder.getConditionsString().size(), 2);
		Assert.assertEquals("There is more orders than expected", builder.getOrdersString().size(), 1);

		Assert.assertEquals("Not present expected condition", "home.address EQUALS dog.home.address", builder.getConditionsString().get(0));
		Assert.assertEquals("Not present expected condition", "mum.name EQUALS dog.owner.name", builder.getConditionsString().get(1));
		Assert.assertEquals("Not present expected order", builder.getOrdersString().get(0), "name");
	}
}
