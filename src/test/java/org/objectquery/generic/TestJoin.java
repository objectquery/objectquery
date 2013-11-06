package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.generic.domain.Person;

public class TestJoin {

	@Test
	public void testSimpleCreateJoin() {
		MockQueryBuilder qb = new MockQueryBuilder();
		GenericSelectQuery<Person> objectQuery = new GenericSelectQuery<Person>(qb, Person.class);
		Person join = objectQuery.join(Person.class);
		objectQuery.eq(objectQuery.target().getName(), join.getName());
		qb.build();

		Assert.assertEquals(1, qb.getConditionsString().size());

		Assert.assertEquals("A.name EQUALS AB0.name", qb.getConditionsString().get(0));
		Assert.assertEquals(1, objectQuery.getJoins().size());
		Assert.assertEquals(Person.class, objectQuery.getJoins().get(0).getRoot().getClazz());
		Assert.assertEquals("AB0", objectQuery.getJoins().get(0).getRoot().getName());
		Assert.assertEquals(JoinType.INNER, objectQuery.getJoins().get(0).getType());
		Assert.assertNull(objectQuery.getJoins().get(0).getJoinPath());

	}

	@Test
	public void testLinkedCreateJoin() {
		MockQueryBuilder qb = new MockQueryBuilder();
		GenericSelectQuery<Person> objectQuery = new GenericSelectQuery<Person>(qb, Person.class);
		Person join = objectQuery.join(objectQuery.target().getMom(), Person.class, JoinType.OUTER);
		objectQuery.eq(objectQuery.target().getName(), join.getName());
		qb.build();

		Assert.assertEquals(1, qb.getConditionsString().size());

		Assert.assertEquals("A.name EQUALS AB0.name", qb.getConditionsString().get(0));
		Assert.assertEquals(1, objectQuery.getJoins().size());
		Assert.assertEquals(Person.class, objectQuery.getJoins().get(0).getRoot().getClazz());
		Assert.assertEquals("AB0", objectQuery.getJoins().get(0).getRoot().getName());
		Assert.assertEquals("mom", objectQuery.getJoins().get(0).getJoinPath().getName());
		Assert.assertEquals("A", objectQuery.getJoins().get(0).getJoinPath().getParent().getName());
		Assert.assertEquals(JoinType.OUTER, objectQuery.getJoins().get(0).getType());

	}
}
