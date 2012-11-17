package org.objectquery.generic;

import java.util.IdentityHashMap;
import java.util.Map;

import org.objectquery.HavingCondition;
import org.objectquery.ObjectQuery;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

public class GenericObjectQuery<T> extends QueryConditionImpl implements ObjectQuery<T> {

	private T target;
	private Class<T> targetClass;
	InternalQueryBuilder builder;
	Map<Object, PathItem> unproxable = new IdentityHashMap<Object, PathItem>();
	private Class<?> primitiveToBoxType;
	private Object primitiveToBoxValue;
	private PathItem primitiveToBoxPath;

	public Object proxy(Class<?> clazz, PathItem parent, String name) {
		if (clazz.isPrimitive()) {
			Object result = PrimitiveFactory.newNumberInstance(clazz, (byte) 0);
			primitiveToBoxType = clazz;
			primitiveToBoxValue = result;
			unproxable.put(result, new PathItem(clazz, parent, name));
			primitiveToBoxPath = new PathItem(clazz, parent, name);
			return result;
		}
		if (String.class.isAssignableFrom(clazz)) {
			String s = new String();
			unproxable.put(s, new PathItem(clazz, parent, name));
			return s;
		} else if (Number.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz) || Boolean.class.isAssignableFrom(clazz)) {
			Object result = PrimitiveFactory.newNumberInstance(clazz, (byte) 0);
			unproxable.put(result, new PathItem(clazz, parent, name));
			return result;
		}
		// TODO:manage array;
		try {
			ProxyFactory pf = new ProxyFactory();
			if (clazz.isInterface())
				pf.setInterfaces(new Class[] { clazz });
			else
				pf.setSuperclass(clazz);

			return pf.create(new Class[0], new Object[0], new ObjectQueryHandler(clazz, this, parent, name));
		} catch (Exception e) {
			throw new ObjectQueryException("Error on creating object proxy", e);
		}

	}

	public GenericObjectQuery(Class<T> clazz) {
		this(new GenericInternalQueryBuilder(GroupType.AND), clazz);
	}

	@SuppressWarnings("unchecked")
	public GenericObjectQuery(InternalQueryBuilder builder, Class<T> clazz) {
		super(builder);
		this.builder = builder;
		this.target = (T) proxy(clazz, null, "");
		this.targetClass = clazz;
	}

	public T target() {
		return target;
	}

	public void prj(Object projection) {
		prj(projection, null);
	}

	public void prj(Object projection, ProjectionType type) {
		builder.projection(extractItem(projection), type);
	}

	private PathItem extractItem(Object object) {
		if (object == null)
			throw new ObjectQueryException("The given object is null", null);
		PathItem item;
		if (!(object instanceof ProxyObject)) {
			if ((item = unproxable.get(object)) == null) {
				if (isPrimitive(object.getClass()))
					throw new ObjectQueryException("The given object is an primitive type autoboxed use box() function to box primitive values", null);
				else
					throw new ObjectQueryException("The given object as order isn't a proxy, use target() method for start to take object for query", null);
			}
		} else {
			if (!(((ProxyObject) object).getHandler() instanceof ObjectQueryHandler))
				throw new ObjectQueryException(
						"The given object as condition isn't a objectquery proxy, use target() method for start to take object for query", null);
			item = ((ObjectQueryHandler) ((ProxyObject) object).getHandler()).getPath();
		}
		return item;
	}

	public void order(Object order) {
		order(order, null, null);
	}

	public void order(Object order, OrderType type) {
		order(order, null, type);
	}

	public void order(Object order, ProjectionType projectionType, OrderType type) {
		builder.order(extractItem(order), projectionType, type);
	}

	private boolean isPrimitive(Class<?> clazz) {
		return Long.TYPE.equals(clazz) || Integer.TYPE.equals(clazz) || Short.TYPE.equals(clazz) || Byte.TYPE.equals(clazz) || Float.TYPE.equals(clazz)
				|| Double.TYPE.equals(clazz) || Character.TYPE.equals(clazz) || Boolean.TYPE.isAssignableFrom(clazz);
	}

	@SuppressWarnings("unchecked")
	private <Z> Z box(Class<Z> clazz, Object value) {
		if (primitiveToBoxType == null || primitiveToBoxValue == null || primitiveToBoxPath == null)
			throw new ObjectQueryException("not present a primitive call to box.", null);
		if (!clazz.equals(primitiveToBoxType))
			throw new ObjectQueryException("present a wrong primitive type that not match with call to box.", null);

		unproxable.put(primitiveToBoxValue, primitiveToBoxPath);
		return (Z) primitiveToBoxValue;
	}

	public Boolean box(boolean b) {
		return box(Boolean.TYPE, b);
	}

	public Byte box(byte b) {
		return box(Byte.TYPE, b);
	}

	public Character box(char c) {
		return box(Character.TYPE, c);
	}

	public Double box(double d) {
		return box(Double.TYPE, d);
	}

	public Float box(float f) {
		return box(Float.TYPE, f);
	}

	public Integer box(int i) {
		return box(Integer.TYPE, i);
	}

	public Long box(long l) {
		return box(Long.TYPE, l);
	}

	public Short box(short s) {
		return box(Short.TYPE, s);
	}

	public InternalQueryBuilder getBuilder() {
		return builder;
	}

	public Class<T> getTargetClass() {
		return targetClass;
	}

	public HavingCondition having(Object projection, ProjectionType type) {
		return new GenericHavingCondition(builder, extractItem(projection), type);
	}

	public void clear() {
		unproxable.clear();
	}
}
