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

	@Test
	public void testSetNestedUpdate() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericUpdateQuery<Person> update = new GenericUpdateQuery<Person>(builder, Person.class);
		Person toUp = update.target();
		update.set(toUp.getDad().getName(), "new-name");
		builder.build();
		Assert.assertEquals(1, builder.getSetsString().size());
		Assert.assertEquals("dad.name new-name", builder.getSetsString().get(0));
	}

	@Test
	public void testSetCrossFieldUpdate() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericUpdateQuery<Person> update = new GenericUpdateQuery<Person>(builder, Person.class);
		Person toUp = update.target();
		update.set(toUp.getName(), toUp.getDad().getName());
		update.set(toUp.getDad(), toUp.getMom());
		builder.build();
		Assert.assertEquals(2, builder.getSetsString().size());
		Assert.assertEquals("name dad.name", builder.getSetsString().get(0));
		Assert.assertEquals("dad mom", builder.getSetsString().get(1));
	}

	@Test(expected = ObjectQueryException.class)
	public void testSetWrongTarget() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericUpdateQuery<Person> update = new GenericUpdateQuery<Person>(builder, Person.class);
		update.set("test", "test");
	}

	@Test(expected = ObjectQueryException.class)
	public void testSetWrongRootTarget() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericUpdateQuery<Person> update = new GenericUpdateQuery<Person>(builder, Person.class);
		Person toUp = update.target();
		update.set(toUp, new Person());
	}
}
