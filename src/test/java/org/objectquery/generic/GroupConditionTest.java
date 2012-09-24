package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.ObjectQuery;
import org.objectquery.QueryCondition;
import org.objectquery.generic.GenericObjectQuery;
import org.objectquery.generic.domain.Person;

public class GroupConditionTest {

	@Test
	public void conditionPlainAnd() {
		TestQueryBuilder tqb = new TestQueryBuilder();
		ObjectQuery<Person> oq = new GenericObjectQuery<Person>(tqb, Person.class);
		Person pers = oq.target();
		QueryCondition cond = oq.and();
		cond.eq(pers.getName(), "mary");
		cond.eq(pers.getDog().getName(), "mary");
		tqb.build();

		Assert.assertEquals(1, tqb.getConditionsString().size());
		Assert.assertEquals(" ( name EQUALS mary AND dog.name EQUALS mary ) ", tqb.getConditionsString().get(0));

	}

	@Test
	public void conditionNestedAnd() {
		TestQueryBuilder tqb = new TestQueryBuilder();
		ObjectQuery<Person> oq = new GenericObjectQuery<Person>(tqb, Person.class);
		Person pers = oq.target();
		QueryCondition cond = oq.and();
		QueryCondition and1 = cond.and();
		and1.eq(pers.getName(), "mary");
		and1.eq(pers.getDog().getName(), "mary");
		QueryCondition and2 = cond.and();
		and2.eq(pers.getName(), "miry");
		and2.eq(pers.getDog().getName(), "miry");

		tqb.build();

		Assert.assertEquals(1, tqb.getConditionsString().size());
		Assert.assertEquals(" (  ( name EQUALS mary AND dog.name EQUALS mary )  AND  ( name EQUALS miry AND dog.name EQUALS miry )  ) ", tqb
				.getConditionsString().get(0));

	}

	@Test
	public void conditionNestedOr() {
		TestQueryBuilder tqb = new TestQueryBuilder();
		ObjectQuery<Person> oq = new GenericObjectQuery<Person>(tqb, Person.class);
		Person pers = oq.target();
		QueryCondition cond = oq.or();
		QueryCondition and1 = cond.or();
		and1.eq(pers.getName(), "mary");
		and1.eq(pers.getDog().getName(), "mary");
		QueryCondition and2 = cond.or();
		and2.eq(pers.getName(), "miry");
		and2.eq(pers.getDog().getName(), "miry");

		tqb.build();

		Assert.assertEquals(1, tqb.getConditionsString().size());
		Assert.assertEquals(" (  ( name EQUALS mary OR dog.name EQUALS mary )  OR  ( name EQUALS miry OR dog.name EQUALS miry )  ) ", tqb.getConditionsString()
				.get(0));

	}

	@Test
	public void conditionNestedMixed() {
		TestQueryBuilder tqb = new TestQueryBuilder();
		ObjectQuery<Person> oq = new GenericObjectQuery<Person>(tqb, Person.class);
		Person pers = oq.target();
		QueryCondition cond = oq.or();
		QueryCondition and1 = cond.and();
		and1.eq(pers.getName(), "mary");
		and1.eq(pers.getDog().getName(), "mary");
		QueryCondition and2 = cond.and();
		and2.eq(pers.getName(), "miry");
		and2.eq(pers.getDog().getName(), "miry");

		tqb.build();

		Assert.assertEquals(1, tqb.getConditionsString().size());
		Assert.assertEquals(" (  ( name EQUALS mary AND dog.name EQUALS mary )  OR  ( name EQUALS miry AND dog.name EQUALS miry )  ) ", tqb
				.getConditionsString().get(0));

	}

	@Test
	public void conditionPlainOr() {
		TestQueryBuilder tqb = new TestQueryBuilder();
		ObjectQuery<Person> oq = new GenericObjectQuery<Person>(tqb, Person.class);
		Person pers = oq.target();
		QueryCondition cond = oq.or();
		cond.eq(pers.getName(), "mary");
		cond.eq(pers.getDog().getName(), "mary");
		tqb.build();

		Assert.assertEquals(1, tqb.getConditionsString().size());
		Assert.assertEquals(" ( name EQUALS mary OR dog.name EQUALS mary ) ", tqb.getConditionsString().get(0));

	}

}