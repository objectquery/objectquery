package org.objectquery.builder;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.builder.domain.Person;

public class AbstractObjectQueryTest {

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectCondition() {
		ObjectQuery<Person> query = new AbstractObjectQuery<Person>(null, Person.class);
		query.condition(new Object(), null, null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectProjection() {
		ObjectQuery<Person> query = new AbstractObjectQuery<Person>(null, Person.class);
		query.projection(new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectProjectionType() {
		ObjectQuery<Person> query = new AbstractObjectQuery<Person>(null, Person.class);
		query.projection(new Object(), null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectOrder() {
		ObjectQuery<Person> query = new AbstractObjectQuery<Person>(null, Person.class);
		query.order(new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectOrderType() {
		ObjectQuery<Person> query = new AbstractObjectQuery<Person>(null, Person.class);
		query.order(new Object(), null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongTypeCondition() {
		ObjectQuery<Person> query = new AbstractObjectQuery<Person>(null, Person.class);
		query.condition(query.target(), null, null);
	}
	
	@Test
	public void testSimpleQueryBuild() {
		TestQueryBuilder builder = new TestQueryBuilder();
		ObjectQuery<Person> query = new AbstractObjectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();
		query.condition(toSearch.getHome().getAddress(), ConditionType.EQUALS, "rue d'anton");
		query.condition(toSearch.getMum().getName(), ConditionType.EQUALS, "elisabeth");
		query.order(toSearch.getName());

		Assert.assertEquals("There is more conditions than expected", builder.getConditions().size(), 2);
		Assert.assertEquals("There is more orders than expected", builder.getOrders().size(), 1);

		Assert.assertTrue("Not present expected condition", builder.getConditions().contains(".home.address EQUALS rue d'anton"));
		Assert.assertTrue("Not present expected condition", builder.getConditions().contains(".mum.name EQUALS elisabeth"));
	}

}
