package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.QueryCondition;
import org.objectquery.SelectQuery;
import org.objectquery.generic.domain.Person;

public class TestGroupCondition {

	@Test
	public void conditionPlainAnd() {
		MockQueryBuilder tqb = new MockQueryBuilder();
		SelectQuery<Person> oq = new GenericSelectQuery<Person>(tqb, Person.class);
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
		MockQueryBuilder tqb = new MockQueryBuilder();
		SelectQuery<Person> oq = new GenericSelectQuery<Person>(tqb, Person.class);
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
		MockQueryBuilder tqb = new MockQueryBuilder();
		SelectQuery<Person> oq = new GenericSelectQuery<Person>(tqb, Person.class);
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
		MockQueryBuilder tqb = new MockQueryBuilder();
		SelectQuery<Person> oq = new GenericSelectQuery<Person>(tqb, Person.class);
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
		MockQueryBuilder tqb = new MockQueryBuilder();
		SelectQuery<Person> oq = new GenericSelectQuery<Person>(tqb, Person.class);
		Person pers = oq.target();
		QueryCondition cond = oq.or();
		cond.eq(pers.getName(), "mary");
		cond.eq(pers.getDog().getName(), "mary");
		tqb.build();

		Assert.assertEquals(1, tqb.getConditionsString().size());
		Assert.assertEquals(" ( name EQUALS mary OR dog.name EQUALS mary ) ", tqb.getConditionsString().get(0));

	}

}