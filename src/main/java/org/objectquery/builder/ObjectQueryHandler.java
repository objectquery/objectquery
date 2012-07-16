package org.objectquery.builder;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;

public class ObjectQueryHandler implements MethodHandler {

	private AbstractObjectQuery<?> abstractObjectQuery;
	private ObjectQueryHandler parent;
	private Class<?> clazz;
	private String name;

	public ObjectQueryHandler(Class<?> clazz, AbstractObjectQuery<?> abstractObjectQuery, ObjectQueryHandler parent, String name) {
		this.abstractObjectQuery = abstractObjectQuery;
		this.parent = parent;
		this.clazz = clazz;
	}

	public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
		Object returnValue = null;
		String name = thisMethod.getName();
		if (name.startsWith("get") && Character.isUpperCase(name.charAt(3))) {
			returnValue = abstractObjectQuery.proxy(thisMethod.getReturnType(), this, Character.toLowerCase(name.charAt(3)) + name.substring(4));
		} else if (name.startsWith("is") && Character.isUpperCase(name.charAt(2))) {
			returnValue = abstractObjectQuery.proxy(thisMethod.getReturnType(), this, Character.toLowerCase(name.charAt(2)) + name.substring(3));
		} else {
			throw new ObjectQueryException("Unsupported opertation this is an Object for Query", null);
		}
		return returnValue;
	}

	public ObjectQueryHandler getParent() {
		return parent;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public String getName() {
		return name;
	}
}
