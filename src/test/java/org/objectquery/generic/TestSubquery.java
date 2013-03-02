package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.ObjectQuery;
import org.objectquery.generic.domain.Person;

public class TestSubquery {

	@Test
	public void testSubquerySimple() {
		MockQueryBuilder builder = new MockQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);

		ObjectQuery<Person> subQuery = new GenericObjectQuery<Person>(Person.class);
		subQuery.eq(subQuery.target().getName(), "test");
		query.eq(query.target().getDud(), subQuery);
		
		builder.build();
		
		Assert.assertEquals("dud EQUALS select  from Person where name EQUALS test", builder.getConditionsString().get(0));

	}

	@Test
	public void testBackReferenceSubquery() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
		Person target = query.target();
		ObjectQuery<Person> subQuery = new GenericObjectQuery<Person>(Person.class);
		subQuery.eq(subQuery.target().getName(), target.getDog().getName());
		query.eq(query.target().getDud(), subQuery);

		query.applyAlias();
		builder.build();

		Assert.assertEquals(1, builder.getConditions().size());
		Assert.assertEquals("A0.dud EQUALS  select from Person A1 where A1.name EQUALS A2.dog.name", builder.getConditionsString().get(0));

	}

}
