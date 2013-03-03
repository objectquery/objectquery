package org.objectquery.generic;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.WeakHashMap;

import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import org.objectquery.HavingCondition;
import org.objectquery.ObjectQuery;

public class GenericObjectQuery<T> extends QueryConditionImpl implements ObjectQuery<T> {

	// Pool of classes is weak but probably never will be cleared, to check!.
	private static final Map<Class<?>, Class<?>> pool = new WeakHashMap<Class<?>, Class<?>>();
	private T target;
	private Class<T> targetClass;
	InternalQueryBuilder builder;
	Map<Object, PathItem> unproxable;
	private Class<?> primitiveToBoxType;
	private Object primitiveToBoxValue;
	private PathItem primitiveToBoxPath;
	private final boolean subQuery;
	private int subCount = 0;

	public Object proxy(Class<?> clazz, PathItem parent, String name) {
		if (clazz.isPrimitive()) {
			Object result = PrimitiveFactory.newNumberInstance(clazz, (byte) 0);
			primitiveToBoxType = clazz;
			primitiveToBoxValue = result;
			PathItem item = new PathItem(clazz, parent, name);
			unproxable.put(result, item);
			primitiveToBoxPath = item;
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
		synchronized (pool) {
			Class<?> proxyClass = pool.get(clazz);
			if (proxyClass == null) {
				ProxyFactory pf = new ProxyFactory();
				if (clazz.isInterface())
					pf.setInterfaces(new Class[] { clazz });
				else
					pf.setSuperclass(clazz);
				proxyClass = pf.createClass();
				pool.put(clazz, proxyClass);
			}
			try {
				Object result = proxyClass.newInstance();
				((Proxy) result).setHandler(new ObjectQueryHandler(clazz, this, parent, name));
				return result;
			} catch (Exception e) {
				throw new ObjectQueryException("Error on creating object proxy", e);
			}
		}

	}

	@SuppressWarnings("unchecked")
	private GenericObjectQuery(InternalQueryBuilder builder, Class<T> clazz, Map<Object, PathItem> unproxable, boolean subQuery) {
		super(builder);
		this.builder = builder;
		this.target = (T) proxy(clazz, null, "");
		this.targetClass = clazz;
		this.unproxable = unproxable;
		this.subQuery = subQuery;
	}

	public GenericObjectQuery(Class<T> targetClass) {
		this(new GenericInternalQueryBuilder(GroupType.AND), targetClass);
	}

	public GenericObjectQuery(InternalQueryBuilder builder, Class<T> targetClass) {
		this(builder, targetClass, new IdentityHashMap<Object, PathItem>(), false);
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

	public PathItem getRootPathItem() {
		return ((ObjectQueryHandler) ((ProxyObject) target).getHandler()).getPath();
	}

	public void clear() {
		unproxable.clear();
		builder.clear();
	}

	public boolean isSubQuery() {
		return subQuery;
	}

	public <S> ObjectQuery<S> subQuery(Class<S> targetClass) {
		if (!subQuery)
			getRootPathItem().setName("A");
		GenericObjectQuery<S> subQ = new GenericObjectQuery<S>(new GenericInternalQueryBuilder(GroupType.AND), targetClass, unproxable, true);
		subQ.getRootPathItem().setName(getRootPathItem().getName() + "A" + (subCount++));
		return subQ;
	}
}
