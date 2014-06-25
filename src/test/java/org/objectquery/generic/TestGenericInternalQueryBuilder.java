package org.objectquery.generic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.objectquery.generic.domain.PersonDTO;

public class TestGenericInternalQueryBuilder {

	@Test
	public void testSimpleSetter() {
		PersonDTO dto = new PersonDTO();
		GenericInternalQueryBuilder.setMappingValue(dto, new PathItem(String.class, null, "name"), "the value");
		assertEquals(dto.getName(), "the value");
	}

	@Test(expected = ObjectQueryException.class)
	public void testSimpleFailSetter() {
		PersonDTO dto = new PersonDTO();
		GenericInternalQueryBuilder.setMappingValue(dto, new PathItem(String.class, null, "not-name"), "the value");
		assertEquals(dto.getName(), "the value");
	}

}
