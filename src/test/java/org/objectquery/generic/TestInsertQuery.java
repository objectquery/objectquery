package org.objectquery.generic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.objectquery.generic.domain.Person;

public class TestInsertQuery {

	@Test
	public void testCreation() {
		GenericInsertQuery<Person> insert = new GenericInsertQuery<Person>(Person.class);
		assertEquals(((GenericInternalQueryBuilder) insert.getBuilder()).getQueryType(), QueryType.INSERT);
	}

	@Test
	public void testSetSimpleInsert() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericInsertQuery<Person> insert = new GenericInsertQuery<Person>(builder, Person.class);
		Person toUp = insert.target();
		insert.set(toUp.getName(), "value");
		builder.build();
		assertEquals(1, builder.getSetsString().size());
		assertEquals("name value", builder.getSetsString().get(0));
	}

	@Test
	public void testSetNestedInsert() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericInsertQuery<Person> insert = new GenericInsertQuery<Person>(builder, Person.class);
		Person toUp = insert.target();
		insert.set(toUp.getDad().getName(), "value");
		builder.build();
		assertEquals(1, builder.getSetsString().size());
		assertEquals("dad.name value", builder.getSetsString().get(0));
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongValueInsert() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericInsertQuery<Person> insert = new GenericInsertQuery<Person>(builder, Person.class);
		Person toUp = insert.target();
		insert.set(toUp.getName(), toUp.getDad().getName());
		builder.build();
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongValueObjInsert() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericInsertQuery<Person> insert = new GenericInsertQuery<Person>(builder, Person.class);
		Person toUp = insert.target();
		insert.set(toUp.getDad(), toUp.getMom());
		builder.build();
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongFieldInsert() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericInsertQuery<Person> insert = new GenericInsertQuery<Person>(builder, Person.class);
		insert.set("value", "value");
		builder.build();
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongRootInsert() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericInsertQuery<Person> insert = new GenericInsertQuery<Person>(builder, Person.class);
		Person toUp = insert.target();
		insert.set(toUp, new Person());
		builder.build();
	}
}
