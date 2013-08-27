package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.generic.domain.Person;

public class TestInsertQuery {

	@Test
	public void testCreation() {
		GenericInsertQuery<Person> insert = new GenericInsertQuery<Person>(Person.class);
		Assert.assertEquals(((GenericInternalQueryBuilder) insert.getBuilder()).getQueryType(), QueryType.INSERT);
	}

	@Test
	public void testSetSimpleUpdate() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericInsertQuery<Person> insert = new GenericInsertQuery<Person>(builder, Person.class);
		Person toUp = insert.target();
		insert.set(toUp.getName(), "value");
		builder.build();
		Assert.assertEquals(1, builder.getSetsString().size());
		Assert.assertEquals("name value", builder.getSetsString().get(0));
	}
}
