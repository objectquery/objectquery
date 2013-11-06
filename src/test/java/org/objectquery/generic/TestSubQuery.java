package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.SelectQuery;
import org.objectquery.generic.domain.Person;

public class TestSubQuery {

	@Test
	public void testSubquerySimple() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person>(builder, Person.class);

		SelectQuery<Person> subQuery = query.subQuery(Person.class);
		subQuery.eq(subQuery.target().getName(), "test");
		query.eq(query.target().getDad(), subQuery);

		builder.build();

		Assert.assertEquals("A.dad EQUALS select  from Person AA0 where AA0.name EQUALS test", builder.getConditionsString().get(0));

	}

	@Test
	public void testBackReferenceSubquery() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericSelectQuery<Person> query = new GenericSelectQuery<Person>(builder, Person.class);
		Person target = query.target();
		SelectQuery<Person> subQuery = query.subQuery(Person.class);
		subQuery.eq(subQuery.target().getName(), target.getDog().getName());
		query.eq(query.target().getDad(), subQuery);

		builder.build();

		Assert.assertEquals(1, builder.getConditions().size());
		Assert.assertEquals("A.dad EQUALS select  from Person AA0 where AA0.name EQUALS A.dog.name", builder.getConditionsString().get(0));
	}

	@Test
	public void testDoubleSubQuery() {

		MockQueryBuilder builder = new MockQueryBuilder();
		GenericSelectQuery<Person> query = new GenericSelectQuery<Person>(builder, Person.class);
		Person target = query.target();
		SelectQuery<Person> subQuery = query.subQuery(Person.class);
		query.eq(target.getDad(), subQuery);
		subQuery.eq(subQuery.target().getName(), target.getDog().getName());
		SelectQuery<Person> doubSubQuery = subQuery.subQuery(Person.class);
		subQuery.eq(subQuery.target().getMom(), doubSubQuery);

		doubSubQuery.eq(doubSubQuery.target().getMom().getName(), subQuery.target().getMom().getName());
		doubSubQuery.eq(doubSubQuery.target().getMom().getName(), query.target().getMom().getName());

		builder.build();
		Assert.assertEquals(1, builder.getConditions().size());
		Assert.assertEquals(
				"A.dad EQUALS select  from Person AA0 where AA0.name EQUALS A.dog.name AND AA0.mom EQUALS select  from Person AA0A0 where AA0A0.mom.name EQUALS AA0.mom.name AND AA0A0.mom.name EQUALS A.mom.name",
				builder.getConditionsString().get(0));

	}

	@Test
	public void testMultipleReferenceSubquery() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericSelectQuery<Person> query = new GenericSelectQuery<Person>(builder, Person.class);
		Person target = query.target();
		SelectQuery<Person> subQuery = query.subQuery(Person.class);
		SelectQuery<Person> subQuery1 = query.subQuery(Person.class);
		query.eq(target.getDad(), subQuery);
		query.eq(target.getMom(), subQuery1);
		builder.build();

		Assert.assertEquals(2, builder.getConditions().size());
		Assert.assertEquals("A.dad EQUALS select  from Person AA0", builder.getConditionsString().get(0));
		Assert.assertEquals("A.mom EQUALS select  from Person AA1", builder.getConditionsString().get(1));
	}

}
