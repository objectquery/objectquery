package org.objectquery.generic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.ObjectQuery;
import org.objectquery.generic.GenericObjectQuery;
import org.objectquery.generic.domain.BoxedPrimitiveDomain;

public class TestPrimitiveBoxedType {

	@Test
	public void testPrimitiveBoxedType() {
		MockQueryBuilder qb = new MockQueryBuilder();
		ObjectQuery<BoxedPrimitiveDomain> bpd = new GenericObjectQuery<BoxedPrimitiveDomain>(qb, BoxedPrimitiveDomain.class);
		BoxedPrimitiveDomain pd = bpd.target();
		bpd.eq(pd.getAtomicBoolean(), new AtomicBoolean(false));
		bpd.eq(pd.getAtomicLong(), new AtomicLong(0));
		bpd.eq(pd.getAtomicInteger(), new AtomicInteger(0));
		bpd.eq(pd.getBool(), new Boolean(false));
		bpd.eq(pd.getBigDecimal(), new BigDecimal(0));
		bpd.eq(pd.getBigInteger(), new BigInteger("0"));
		bpd.eq(pd.getInte(), new Integer(0));
		bpd.eq(pd.getLonge(), new Long(0));
		bpd.eq(pd.getBytee(), new Byte((byte) 0));
		bpd.eq(pd.getChare(), new Character('A'));
		bpd.eq(pd.getDoublee(), new Double(0));
		bpd.eq(pd.getFloate(), new Float(0));
		bpd.eq(pd.getShorte(), new Short((short) 0));
		bpd.eq(pd.getAtomicBoolean(), new AtomicBoolean(false));
		bpd.eq(pd.getBool(), new Boolean(false));

		qb.build();

		Assert.assertEquals(" wrong number of condition", 15, qb.getConditionsString().size());
		Assert.assertEquals("atomicBoolean EQUALS false", qb.getConditionsString().get(0));
		Assert.assertEquals("atomicLong EQUALS 0", qb.getConditionsString().get(1));
		Assert.assertEquals("atomicInteger EQUALS 0", qb.getConditionsString().get(2));
		Assert.assertEquals("bool EQUALS false", qb.getConditionsString().get(3));
		Assert.assertEquals("bigDecimal EQUALS 0", qb.getConditionsString().get(4));
		Assert.assertEquals("bigInteger EQUALS 0", qb.getConditionsString().get(5));
		Assert.assertEquals("inte EQUALS 0", qb.getConditionsString().get(6));
		Assert.assertEquals("longe EQUALS 0", qb.getConditionsString().get(7));
		Assert.assertEquals("bytee EQUALS 0", qb.getConditionsString().get(8));
		Assert.assertEquals("chare EQUALS A", qb.getConditionsString().get(9));
		Assert.assertEquals("doublee EQUALS 0.0", qb.getConditionsString().get(10));
		Assert.assertEquals("floate EQUALS 0.0", qb.getConditionsString().get(11));
		Assert.assertEquals("shorte EQUALS 0", qb.getConditionsString().get(12));
		Assert.assertEquals("atomicBoolean EQUALS false", qb.getConditionsString().get(13));
		Assert.assertEquals("bool EQUALS false", qb.getConditionsString().get(14));
	}
}