package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.generic.domain.Person;

/**
 * Test just the correct query type because the condition is already tested in
 * select.
 * 
 * 
 */
public class TestDeleteQuery {

	@Test
	public void testSimpleDelete() {

		GenericeDeleteQuery<Person> personQuery = new GenericeDeleteQuery<Person>(Person.class);
		personQuery.eq(personQuery.target().getName(), "test");

		GenericInternalQueryBuilder builder = (GenericInternalQueryBuilder) personQuery.getBuilder();
		Assert.assertEquals(builder.getQueryType(), QueryType.DELETE);
	}
}
