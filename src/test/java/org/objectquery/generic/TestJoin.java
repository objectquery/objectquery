package org.objectquery.generic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.objectquery.generic.domain.Person;

public class TestJoin {

	@Test
	public void testSimpleCreateJoin() {
		MockQueryBuilder qb = new MockQueryBuilder();
		GenericSelectQuery<Person, Object> objectQuery = new GenericSelectQuery<Person, Object>(qb, Person.class);
		Person join = objectQuery.join(Person.class);
		objectQuery.eq(objectQuery.target().getName(), join.getName());
		qb.build();

		assertEquals(1, qb.getConditionsString().size());

		assertEquals("A.name EQUALS AB0.name", qb.getConditionsString().get(0));
		assertEquals(1, objectQuery.getJoins().size());
		assertEquals(Person.class, objectQuery.getJoins().get(0).getRoot().getClazz());
		assertEquals("AB0", objectQuery.getJoins().get(0).getRoot().getName());
		assertEquals(JoinType.INNER, objectQuery.getJoins().get(0).getType());
		assertNull(objectQuery.getJoins().get(0).getJoinPath());

	}

	@Test
	public void testLinkedCreateJoin() {
		MockQueryBuilder qb = new MockQueryBuilder();
		GenericSelectQuery<Person, Object> objectQuery = new GenericSelectQuery<Person, Object>(qb, Person.class);
		Person join = objectQuery.join(objectQuery.target().getMom(), Person.class, JoinType.OUTER);
		objectQuery.eq(objectQuery.target().getName(), join.getName());
		qb.build();

		assertEquals(1, qb.getConditionsString().size());

		assertEquals("A.name EQUALS AB0.name", qb.getConditionsString().get(0));
		assertEquals(1, objectQuery.getJoins().size());
		assertEquals(Person.class, objectQuery.getJoins().get(0).getRoot().getClazz());
		assertEquals("AB0", objectQuery.getJoins().get(0).getRoot().getName());
		assertEquals("mom", objectQuery.getJoins().get(0).getJoinPath().getName());
		assertEquals("A", objectQuery.getJoins().get(0).getJoinPath().getParent().getName());
		assertEquals(JoinType.OUTER, objectQuery.getJoins().get(0).getType());

	}
}
