package org.objectquery.builder;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.builder.domain.Person;

public class GroupConditionTest {

	@Test
	public void conditionPlainAnd() {
		TestQueryBuilder tqb = new TestQueryBuilder();
		ObjectQuery<Person> oq = new AbstractObjectQuery<Person>(tqb, Person.class);
		Person pers = oq.target();
		QueryCondition cond = oq.and();
		cond.condition(pers.getName(), ConditionType.EQUALS, "mary");
		cond.condition(pers.getDog().getName(), ConditionType.EQUALS, "mary");
		tqb.build();

		Assert.assertEquals(1, tqb.getConditionsString().size());
		Assert.assertEquals(" ( name EQUALS mary AND dog.name EQUALS mary ) ", tqb.getConditionsString().get(0));

	}

	@Test
	public void conditionPlainOr() {
		TestQueryBuilder tqb = new TestQueryBuilder();
		ObjectQuery<Person> oq = new AbstractObjectQuery<Person>(tqb, Person.class);
		Person pers = oq.target();
		QueryCondition cond = oq.or();
		cond.condition(pers.getName(), ConditionType.EQUALS, "mary");
		cond.condition(pers.getDog().getName(), ConditionType.EQUALS, "mary");
		tqb.build();
		
		Assert.assertEquals(1, tqb.getConditionsString().size());
		Assert.assertEquals(" ( name EQUALS mary OR dog.name EQUALS mary ) ", tqb.getConditionsString().get(0));
		
	}
	
}
