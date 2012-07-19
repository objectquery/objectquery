package org.objectquery.builder;

import java.util.IdentityHashMap;
import java.util.Map;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

public class AbstractObjectQuery<T> extends QueryConditionImpl implements ObjectQuery<T> {

	private T target;
	InternalQueryBuilder builder;
	Map<Object, PathItem> unproxable = new IdentityHashMap<Object, PathItem>();
	private Class<?> primitiveToBox;

	public Object proxy(Class<?> clazz, ObjectQueryHandler parent, String name) {
		if (clazz.isPrimitive()) {
			Object result = PrimitiveFactory.newNumberInstance(clazz, (byte) 0);
			primitiveToBox = clazz;
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

}
