package org.objectquery.generic;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.objectquery.SelectMapQuery;
import org.objectquery.generic.domain.Person;
import org.objectquery.generic.domain.PersonDTO;

public class TestSelectMapQuery {

	@Test
	public void testSimpleSelectMap() {
		MockQueryBuilder mock = new MockQueryBuilder();
		SelectMapQuery<Person, PersonDTO> query = new GenericSelectQuery<Person, PersonDTO>(mock, Person.class, PersonDTO.class);
		query.prj(query.target().getName(), query.mapper().getName());

		mock.build();
		assertThat(mock.getProjectionsString(), CoreMatchers.hasItem("name name"));

	}

}
