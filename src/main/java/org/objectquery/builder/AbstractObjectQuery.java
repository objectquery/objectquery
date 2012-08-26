package org.objectquery.builder;

import java.util.IdentityHashMap;
import java.util.Map;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

public class AbstractObjectQuery<T> extends QueryConditionImpl implements ObjectQuery<T> {

	private T target;
	private Class<T> targetClass;
	InternalQueryBuilder builder;
	Map<Object, PathItem> unproxable = new IdentityHashMap<Object, PathItem>();
	private Class<?> primitiveToBoxType;
	private Object primitiveToBoxValue;
	private PathItem primitiveToBoxPath;

	public Object proxy(Class<?> clazz, ObjectQueryHandler parent, String name) {
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
			pf.setSuperclass(clazz);
			return pf.create(new Class[0], new Object[0], new ObjectQueryHandler(clazz, this, parent, name));
		} catch (Exception e) {
			throw new ObjectQueryException("Error on creating object proxy", e);
		}

	}

	@SuppressWarnings("unchecked")
	public AbstractObjectQuery(InternalQueryBuilder builder, Class<T> clazz) {
		super(builder);
		this.builder = builder;
		this.target = (T) proxy(clazz, null, "");
		this.targetClass = clazz;
	}

	public T target() {
		return target;
	}

	public void projection(Object projection) {
		PathItem item = null;
		if (!(projection instanceof ProxyObject)) {
			if ((item = unproxable.get(projection)) == null)
				throw new ObjectQueryException("The given object as projection isn't a proxy, use target() method for start to take object for query", null);
			else
				builder.projection(item);
		} else
			builder.projection((PathItem) ((ProxyObject) projection).getHandler());
	}

	public void projection(Object projection, ProjectionType type) {
		PathItem item = null;
		if (!(projection instanceof ProxyObject)) {
			if ((item = unproxable.get(projection)) == null)
				throw new ObjectQueryException("The given object as projection isn't a proxy, use target() method for start to take object for query", null);
			else
				builder.projection(item, type);
		} else
			builder.projection((PathItem) ((ProxyObject) projection).getHandler(), type);
	}

	public void order(Object order) {
		PathItem item = null;
		if (!(order instanceof ProxyObject)) {
			if ((item = unproxable.get(order)) == null)
				throw new ObjectQueryException("The given object as order isn't a proxy, use target() method for start to take object for query", null);
			else
				builder.order(item);
		} else
			builder.order((PathItem) ((ProxyObject) order).getHandler());
	}

	public void order(Object order, OrderType type) {
		PathItem item = null;
		if (!(order instanceof ProxyObject)) {
			if ((item = unproxable.get(order)) == null)
				throw new ObjectQueryException("The given object as order isn't a proxy, use target() method for start to take object for query", null);
			else
				builder.order(item, type);
		} else
			builder.order((PathItem) ((ProxyObject) order).getHandler(), type);
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

	protected Class<T> getTargetClass() {
		return targetClass;
	}
}
