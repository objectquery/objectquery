package org.objectquery.builder;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.builder.domain.Person;

public class TestQueryCondition {

	@Test
	public void testPathValueQueryBuild() {
		TestQueryBuilder builder = new TestQueryBuilder();
		ObjectQuery<Person> query = new AbstractObjectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();
		query.eq(toSearch.getHome().getAddress(), toSearch.getDog().getHome().getAddress());
		query.eq(toSearch.getDud().getHome(), toSearch.getDog().getHome());
		query.eq(toSearch.getMum().getName(), toSearch.getDog().getOwner().getName());
		query.order(toSearch.getName());

		builder.build();
		Assert.assertEquals("There is more conditions than expected", builder.getConditionsString().size(), 3);
		Assert.assertEquals("There is more orders than expected", builder.getOrdersString().size(), 1);

		Assert.assertEquals("Not present expected condition", "home.address EQUALS dog.home.address", builder.getConditionsString().get(0));
		Assert.assertEquals("Not present expected condition", "dud.home EQUALS dog.home", builder.getConditionsString().get(1));
		Assert.assertEquals("Not present expected condition", "mum.name EQUALS dog.owner.name", builder.getConditionsString().get(2));
		Assert.assertEquals("Not present expected order", builder.getOrdersString().get(0), "name");
	}

	@Test
	public void testConditionsTypes() {
		TestQueryBuilder builder = new TestQueryBuilder();
		ObjectQuery<Person> query = new AbstractObjectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();
		query.eq(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.contains(toSearch.getFriends(), toSearch.getDud());
		query.in(toSearch.getMum(), toSearch.getDud().getFriends());
		query.like(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.max(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.maxEq(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.min(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.minEq(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.notEq(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.notIn(toSearch.getMum(), toSearch.getDud().getFriends());
		query.notContains(toSearch.getMum().getFriends(), toSearch.getDud());
		query.notLike(toSearch.getMum().getName(), toSearch.getDud().getName());
		builder.build();

		Assert.assertEquals("mum.name EQUALS dud.name", builder.getConditionsString().get(0));
		Assert.assertEquals("friends CONTAINS dud", builder.getConditionsString().get(1));
		Assert.assertEquals("mum IN dud.friends", builder.getConditionsString().get(2));
		Assert.assertEquals("mum.name LIKE dud.name", builder.getConditionsString().get(3));
		Assert.assertEquals("mum.name MAX dud.name", builder.getConditionsString().get(4));
		Assert.assertEquals("mum.name MAX_EQUALS dud.name", builder.getConditionsString().get(5));
		Assert.assertEquals("mum.name MIN dud.name", builder.getConditionsString().get(6));
		Assert.assertEquals("mum.name MIN_EQUALS dud.name", builder.getConditionsString().get(7));
		Assert.assertEquals("mum.name NOT_EQUALS dud.name", builder.getConditionsString().get(8));
		Assert.assertEquals("mum NOT_IN dud.friends", builder.getConditionsString().get(9));
		Assert.assertEquals("mum.friends NOT_CONTAINS dud", builder.getConditionsString().get(10));
		Assert.assertEquals("mum.name NOT_LIKE dud.name", builder.getConditionsString().get(11));

	}
}
