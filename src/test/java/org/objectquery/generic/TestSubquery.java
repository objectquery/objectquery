package org.objectquery.generic;

import org.objectquery.ObjectQuery;
import org.objectquery.generic.domain.Person;

public class TestSubquery {

	public void testSubquerySimple() {
		MockQueryBuilder builder = new MockQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);

		ObjectQuery<Person> dudQuery = new GenericObjectQuery<Person>(Person.class);
		dudQuery.eq(dudQuery.target().getName(), "test");
		query.eq(query.target().getDud(), dudQuery);
		
		builder.build();
	}

}
