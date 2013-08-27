package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.generic.domain.Person;

public class TestUpdateQuery {

	@Test
	public void testCreation() {
		GenericUpdateQuery<Person> update = new GenericUpdateQuery<Person>(Person.class);
		Assert.assertEquals(((GenericInternalQueryBuilder) update.getBuilder()).getQueryType(), QueryType.UPDATE);
	}

	@Test
	public void testSetSimpleUpdate() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericUpdateQuery<Person> update = new GenericUpdateQuery<Person>(builder, Person.class);
		Person toUp = update.target();
		update.set(toUp.getName(), "new-name");
		builder.build();
		Assert.assertEquals(1, builder.getSetsString().size());
		Assert.assertEquals("name new-name", builder.getSetsString().get(0));
	}
}
