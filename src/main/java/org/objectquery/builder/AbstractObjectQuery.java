package org.objectquery.builder;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

public class AbstractObjectQuery<T> implements ObjectQuery<T> {

	private T target;
	private InternalQueryBuilder builder;

	public Object proxy(Class<?> clazz, ObjectQueryHandler parent, String name) {
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
		this.builder = builder;
		this.target = (T) proxy(clazz, null, "");
	}

	public T target() {
		return target;
	}

	public void projection(Object projection) {
		if (!(projection instanceof ProxyObject))
			throw new ObjectQueryException("The given object as projection isn't a proxy, use target() method for start to take object for query", null);
	}

	public void projection(Object projection, ProjectionType type) {
		if (!(projection instanceof ProxyObject))
			throw new ObjectQueryException("The given object as projection isn't a proxy, use target() method for start to take object for query", null);
	}

	public <C> void condition(C target, ConditionType type, C value) {
		if (!(target instanceof ProxyObject))
			throw new ObjectQueryException("The given object as condition isn't a proxy, use target() method for start to take object for query", null);
		if (type == null)
			throw new ObjectQueryException("The given type of condition is null", null);
	}

	public void order(Object order) {
		if (!(order instanceof ProxyObject))
			throw new ObjectQueryException("The given object as order isn't a proxy, use target() method for start to take object for query", null);
	}

	public void order(Object order, OrderType type) {
		if (!(order instanceof ProxyObject))
			throw new ObjectQueryException("The given object as order isn't a proxy, use target() method for start to take object for query", null);
	}

}
