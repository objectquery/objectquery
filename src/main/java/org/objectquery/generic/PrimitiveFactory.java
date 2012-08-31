package org.objectquery.generic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PrimitiveFactory {

	public static Object newNumberInstance(Class<?> clazz, byte val) {
		if (Long.class.equals(clazz) || Long.TYPE.equals(clazz)) {
			return new Long(val);
		} else if (Integer.class.equals(clazz) || Integer.TYPE.equals(clazz)) {
			return new Integer(val);
		} else if (Short.class.equals(clazz) || Short.TYPE.equals(clazz)) {
			return new Short(val);
		} else if (Byte.class.equals(clazz) || Byte.TYPE.equals(clazz)) {
			return new Byte(val);
		} else if (Float.class.equals(clazz) || Float.TYPE.equals(clazz)) {
			return new Float(val);
		} else if (Double.class.equals(clazz) || Double.TYPE.equals(clazz)) {
			return new Double(val);
		} else if (BigDecimal.class.equals(clazz)) {
			return new BigDecimal(val);
		} else if (BigInteger.class.equals(clazz)) {
			return new BigInteger(new Byte(val).toString());
		} else if (AtomicBoolean.class.equals(clazz)) {
			return new AtomicBoolean(val > 0);
		} else if (AtomicInteger.class.equals(clazz)) {
			return new AtomicInteger(val);
		} else if (AtomicLong.class.equals(clazz)) {
			return new AtomicLong(val);
		} else if (Character.class.equals(clazz) || Character.TYPE.equals(clazz)) {
			return new Character((char) val);
		} else if (Boolean.class.isAssignableFrom(clazz) || Boolean.TYPE.isAssignableFrom(clazz)) {
			return new Boolean(val > 0);
		}
		return null;
	}
}
