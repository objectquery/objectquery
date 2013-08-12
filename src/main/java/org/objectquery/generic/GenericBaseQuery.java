package org.objectquery.generic;

import java.util.Map;
import java.util.WeakHashMap;

import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import org.objectquery.BaseQuery;

public class GenericBaseQuery<T> extends QueryConditionImpl implements BaseQuery<T> {

	// Pool of classes is weak but probably never will be cleared, to check!.
	private static final Map<Class<?>, Class<?>> pool = new WeakHashMap<Class<?>, Class<?>>();

	protected T target;
	protected Class<?> primitiveToBoxType;
	protected Object primitiveToBoxValue;
	protected PathItem primitiveToBoxPath;
	protected Map<Object, PathItem> unproxable;
	protected InternalQueryBuilder builder;
	protected Class<T> targetClass;

	@SuppressWarnings("unchecked")
	public GenericBaseQuery(InternalQueryBuilder builder, Class<T> clazz, Map<Object, PathItem> unproxable) {
		super(builder);
		this.builder = builder;
		this.target = (T) proxy(clazz, null, "");
		this.targetClass = clazz;
		this.unproxable = unproxable;
	}

	public T target() {
		return target;
	}

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
		synchronized (GenericBaseQuery.pool) {
			Class<?> proxyClass = GenericBaseQuery.pool.get(clazz);
			if (proxyClass == null) {
				ProxyFactory pf = new ProxyFactory();
				if (clazz.isInterface())
					pf.setInterfaces(new Class[] { clazz });
				else
					pf.setSuperclass(clazz);
				proxyClass = pf.createClass();
				GenericBaseQuery.pool.put(clazz, proxyClass);
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

	protected PathItem extractItem(Object object) {
		if (object == null)
			throw new ObjectQueryException("The given object is null");
		PathItem item;
		if (!(object instanceof ProxyObject)) {
			if ((item = unproxable.get(object)) == null) {
				if (isPrimitive(object.getClass()))
					throw new ObjectQueryException("The given object is an primitive type autoboxed use box() function to box primitive values");
				else
					throw new ObjectQueryException("The given object as order isn't a proxy, use target() method for start to take object for query");
			}
		} else {
			if (!(((ProxyObject) object).getHandler() instanceof ObjectQueryHandler))
				throw new ObjectQueryException(
						"The given object as condition isn't a objectquery proxy, use target() method for start to take object for query");
			item = ((ObjectQueryHandler) ((ProxyObject) object).getHandler()).getPath();
		}
		return item;
	}

	private boolean isPrimitive(Class<?> clazz) {
		return Long.TYPE.equals(clazz) || Integer.TYPE.equals(clazz) || Short.TYPE.equals(clazz) || Byte.TYPE.equals(clazz) || Float.TYPE.equals(clazz)
				|| Double.TYPE.equals(clazz) || Character.TYPE.equals(clazz) || Boolean.TYPE.isAssignableFrom(clazz);
	}

	@SuppressWarnings("unchecked")
	protected <Z> Z box(Class<Z> clazz, Object value) {
		if (primitiveToBoxType == null || primitiveToBoxValue == null || primitiveToBoxPath == null)
			throw new ObjectQueryException("not present a primitive call to box.");
		if (!clazz.equals(primitiveToBoxType))
			throw new ObjectQueryException("present a wrong primitive type that not match with call to box.");

		unproxable.put(primitiveToBoxValue, primitiveToBoxPath);
		return (Z) primitiveToBoxValue;
	}

	public InternalQueryBuilder getBuilder() {
		return builder;
	}

	public Class<T> getTargetClass() {
		return targetClass;
	}

	public PathItem getRootPathItem() {
		return ((ObjectQueryHandler) ((ProxyObject) target).getHandler()).getPath();
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

	public void clear() {
		unproxable.clear();
		builder.clear();
	}

}
