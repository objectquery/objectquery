package org.objectquery.generic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.objectquery.generic.domain.AddressDTO;
import org.objectquery.generic.domain.PersonDTO;

public class TestGenericInternalQueryBuilder {

	@Test
	public void testSimpleSetter() throws Exception {
		PersonDTO dto = new PersonDTO();
		GenericInternalQueryBuilder.setMappingValue(dto, new PathItem(String.class, null, "name"), "the value");
		assertEquals(dto.getName(), "the value");
	}

	@Test(expected = Exception.class)
	public void testFailSimpleSetter() throws Exception {
		PersonDTO dto = new PersonDTO();
		GenericInternalQueryBuilder.setMappingValue(dto, new PathItem(String.class, null, "not-name"), "the value");
		assertEquals(dto.getName(), "the value");
	}

	@Test
	public void testSimpleGetValue() throws Exception {
		PersonDTO dto = new PersonDTO();
		dto.setName("the value");
		String name = (String) GenericInternalQueryBuilder.getValue(dto, new PathItem(String.class, null, "name"));
		assertEquals(name, "the value");
	}

	@Test(expected = NoSuchMethodException.class)
	public void testFailSimpleGetValue() throws Exception {
		PersonDTO dto = new PersonDTO();
		GenericInternalQueryBuilder.getValue(dto, new PathItem(String.class, null, "none"));
	}

	@Test
	public void testGetParent() throws Exception {
		PersonDTO dto = new PersonDTO();
		Object parent = GenericInternalQueryBuilder.getParentObject(dto, new PathItem(AddressDTO.class, new PathItem(PersonDTO.class, null, ""), "addressDTO"));
		assertTrue(parent instanceof AddressDTO);
	}

	@Test(expected = NoSuchMethodException.class)
	public void testFailGetParent() throws Exception {
		PersonDTO dto = new PersonDTO();
		Object parent = GenericInternalQueryBuilder.getParentObject(dto, new PathItem(AddressDTO.class, new PathItem(PersonDTO.class, null, ""), "none"));
		assertTrue(parent instanceof AddressDTO);
	}

	@Test
	public void testRecursiveSet() {
		Map<String, Object> values = new HashMap<>();
		values.put("addressDTO_street", "hello");
		List<Projection> projections = new ArrayList<>();
		projections.add(new Projection(new PathItem(null, null, null), new PathItem(String.class, new PathItem(AddressDTO.class, new PathItem(PersonDTO.class,
				null, ""), "addressDTO"), "street"), null));
		PersonDTO restult = GenericInternalQueryBuilder.setMapping(PersonDTO.class, projections, values);
		assertNotNull(restult);
		assertNotNull(restult.getAddressDTO());
		assertEquals(restult.getAddressDTO().getStreet(), "hello");
	}
}
