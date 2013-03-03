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

		ObjectQuery<Person> subQuery = query.subQuery(Person.class);
		subQuery.eq(subQuery.target().getName(), "test");
		query.eq(query.target().getDud(), subQuery);

		builder.build();

		Assert.assertEquals("A.dud EQUALS select  from Person AA0 where AA0.name EQUALS test", builder.getConditionsString().get(0));

	}

	@Test
	public void testBackReferenceSubquery() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
		Person target = query.target();
		ObjectQuery<Person> subQuery = query.subQuery(Person.class);
		subQuery.eq(subQuery.target().getName(), target.getDog().getName());
		query.eq(query.target().getDud(), subQuery);

		builder.build();

		Assert.assertEquals(1, builder.getConditions().size());
		Assert.assertEquals("A.dud EQUALS select  from Person AA0 where AA0.name EQUALS A.dog.name", builder.getConditionsString().get(0));
	}

	@Test
	public void testDoubleSubQuery() {

		MockQueryBuilder builder = new MockQueryBuilder();
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
		Person target = query.target();
		ObjectQuery<Person> subQuery = query.subQuery(Person.class);
		query.eq(target.getDud(), subQuery);
		subQuery.eq(subQuery.target().getName(), target.getDog().getName());
		ObjectQuery<Person> doubSubQuery = subQuery.subQuery(Person.class);
		subQuery.eq(subQuery.target().getMum(), doubSubQuery);

		doubSubQuery.eq(doubSubQuery.target().getMum().getName(), subQuery.target().getMum().getName());
		doubSubQuery.eq(doubSubQuery.target().getMum().getName(), query.target().getMum().getName());

		builder.build();
		Assert.assertEquals(1, builder.getConditions().size());
		Assert.assertEquals(
				"A.dud EQUALS select  from Person AA0 where AA0.name EQUALS A.dog.name AND AA0.mum EQUALS select  from Person AA0A0 where AA0A0.mum.name EQUALS AA0.mum.name AND AA0A0.mum.name EQUALS A.mum.name",
				builder.getConditionsString().get(0));

	}

	@Test
	public void testMultipleReferenceSubquery() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
		Person target = query.target();
		ObjectQuery<Person> subQuery = query.subQuery(Person.class);
		ObjectQuery<Person> subQuery1 = query.subQuery(Person.class);
		query.eq(target.getDud(), subQuery);
		query.eq(target.getMum(), subQuery1);
		builder.build();

		Assert.assertEquals(2, builder.getConditions().size());
		Assert.assertEquals("A.dud EQUALS select  from Person AA0", builder.getConditionsString().get(0));
		Assert.assertEquals("A.mum EQUALS select  from Person AA1", builder.getConditionsString().get(1));
	}

}
