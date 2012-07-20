package org.objectquery.builder;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.builder.domain.PrimitiveDomain;

public class PrimitiveTypeTest {

	@Test
	public void testPrimitiveType() {
		TestQueryBuilder qb = new TestQueryBuilder();
		ObjectQuery<PrimitiveDomain> query = new AbstractObjectQuery<PrimitiveDomain>(qb, PrimitiveDomain.class);
		PrimitiveDomain pd = query.target();
		query.condition(query.box(pd.isBool()), ConditionType.EQUALS, false);
		query.condition(query.box(pd.getInte()), ConditionType.EQUALS, 0);
		query.condition(query.box(pd.getLonge()), ConditionType.EQUALS, 0L);
		query.condition(query.box(pd.getBytee()), ConditionType.EQUALS, (byte) 0);
		query.condition(query.box(pd.getChare()), ConditionType.EQUALS, 'A');
		query.condition(query.box(pd.getDoublee()), ConditionType.EQUALS, 0D);
		query.condition(query.box(pd.getFloate()), ConditionType.EQUALS, 0F);
		query.condition(query.box(pd.getShorte()), ConditionType.EQUALS, (short) 0);

		qb.build();

		Assert.assertEquals(" wrong number of condition", qb.getConditionsString().size(), 8);
		Assert.assertEquals("bool EQUALS false", qb.getConditionsString().get(0));
		Assert.assertEquals("inte EQUALS 0", qb.getConditionsString().get(1));
		Assert.assertEquals("longe EQUALS 0", qb.getConditionsString().get(2));
		Assert.assertEquals("bytee EQUALS 0", qb.getConditionsString().get(3));
		Assert.assertEquals("chare EQUALS A", qb.getConditionsString().get(4));
		Assert.assertEquals("doublee EQUALS 0.0", qb.getConditionsString().get(5));
		Assert.assertEquals("floate EQUALS 0.0", qb.getConditionsString().get(6));
		Assert.assertEquals("shorte EQUALS 0", qb.getConditionsString().get(7));
	}

}
