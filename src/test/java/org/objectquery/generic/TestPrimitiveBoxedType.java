package org.objectquery.generic;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;
import org.objectquery.BaseSelectQuery;
import org.objectquery.generic.domain.BoxedPrimitiveDomain;

public class TestPrimitiveBoxedType {

	@Test
	public void testPrimitiveBoxedType() {
		Date d = new Date();
		MockQueryBuilder qb = new MockQueryBuilder();
		BaseSelectQuery<BoxedPrimitiveDomain> bpd = new GenericSelectQuery<BoxedPrimitiveDomain, Object>(qb, BoxedPrimitiveDomain.class);
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
		bpd.eq(pd.getDate(), d);

		qb.build();

		assertEquals(" wrong number of condition", 16, qb.getConditionsString().size());
		assertEquals("atomicBoolean EQUALS false", qb.getConditionsString().get(0));
		assertEquals("atomicLong EQUALS 0", qb.getConditionsString().get(1));
		assertEquals("atomicInteger EQUALS 0", qb.getConditionsString().get(2));
		assertEquals("bool EQUALS false", qb.getConditionsString().get(3));
		assertEquals("bigDecimal EQUALS 0", qb.getConditionsString().get(4));
		assertEquals("bigInteger EQUALS 0", qb.getConditionsString().get(5));
		assertEquals("inte EQUALS 0", qb.getConditionsString().get(6));
		assertEquals("longe EQUALS 0", qb.getConditionsString().get(7));
		assertEquals("bytee EQUALS 0", qb.getConditionsString().get(8));
		assertEquals("chare EQUALS A", qb.getConditionsString().get(9));
		assertEquals("doublee EQUALS 0.0", qb.getConditionsString().get(10));
		assertEquals("floate EQUALS 0.0", qb.getConditionsString().get(11));
		assertEquals("shorte EQUALS 0", qb.getConditionsString().get(12));
		assertEquals("atomicBoolean EQUALS false", qb.getConditionsString().get(13));
		assertEquals("bool EQUALS false", qb.getConditionsString().get(14));
		assertEquals("date EQUALS " + d.toString(), qb.getConditionsString().get(15));
	}
}
