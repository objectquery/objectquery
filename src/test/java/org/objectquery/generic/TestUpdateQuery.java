package org.objectquery.generic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.objectquery.generic.domain.Person;

public class TestUpdateQuery {

	@Test
	public void testCreation() {
		GenericUpdateQuery<Person> update = new GenericUpdateQuery<Person>(Person.class);
		assertEquals(((GenericInternalQueryBuilder) update.getBuilder()).getQueryType(), QueryType.UPDATE);
	}

	@Test
	public void testSetSimpleUpdate() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericUpdateQuery<Person> update = new GenericUpdateQuery<Person>(builder, Person.class);
		Person toUp = update.target();
		update.set(toUp.getName(), "new-name");
		builder.build();
		assertEquals(1, builder.getSetsString().size());
		assertEquals("name new-name", builder.getSetsString().get(0));
	}

	@Test
	public void testSetNestedUpdate() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericUpdateQuery<Person> update = new GenericUpdateQuery<Person>(builder, Person.class);
		Person toUp = update.target();
		update.set(toUp.getDad().getName(), "new-name");
		builder.build();
		assertEquals(1, builder.getSetsString().size());
		assertEquals("dad.name new-name", builder.getSetsString().get(0));
	}

	@Test
	public void testSetCrossFieldUpdate() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericUpdateQuery<Person> update = new GenericUpdateQuery<Person>(builder, Person.class);
		Person toUp = update.target();
		update.set(toUp.getName(), toUp.getDad().getName());
		update.set(toUp.getDad(), toUp.getMom());
		builder.build();
		assertEquals(2, builder.getSetsString().size());
		assertEquals("name dad.name", builder.getSetsString().get(0));
		assertEquals("dad mom", builder.getSetsString().get(1));
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
