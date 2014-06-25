package org.objectquery.generic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.objectquery.BaseSelectQuery;
import org.objectquery.generic.domain.PrimitiveDomain;

public class TestPrimitiveType {

	@Test
	public void testPrimitiveType() {
		MockQueryBuilder qb = new MockQueryBuilder();
		BaseSelectQuery<PrimitiveDomain> query = new GenericSelectQuery<PrimitiveDomain, Object>(qb, PrimitiveDomain.class);
		PrimitiveDomain pd = query.target();
		query.eq(query.box(pd.isBool()), false);
		query.eq(query.box(pd.getInte()), 0);
		query.eq(query.box(pd.getLonge()), 0L);
		query.eq(query.box(pd.getBytee()), (byte) 0);
		query.eq(query.box(pd.getChare()), 'A');
		query.eq(query.box(pd.getDoublee()), 0D);
		query.eq(query.box(pd.getFloate()), 0F);
		query.eq(query.box(pd.getShorte()), (short) 0);

		qb.build();

		assertEquals(" wrong number of condition", qb.getConditionsString().size(), 8);
		assertEquals("bool EQUALS false", qb.getConditionsString().get(0));
		assertEquals("inte EQUALS 0", qb.getConditionsString().get(1));
		assertEquals("longe EQUALS 0", qb.getConditionsString().get(2));
		assertEquals("bytee EQUALS 0", qb.getConditionsString().get(3));
		assertEquals("chare EQUALS A", qb.getConditionsString().get(4));
		assertEquals("doublee EQUALS 0.0", qb.getConditionsString().get(5));
		assertEquals("floate EQUALS 0.0", qb.getConditionsString().get(6));
		assertEquals("shorte EQUALS 0", qb.getConditionsString().get(7));
	}

}
