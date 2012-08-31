package org.objectquery.generic;

import java.lang.reflect.Method;


import javassist.util.proxy.MethodHandler;

public class ObjectQueryHandler extends PathItem implements MethodHandler {

	private GenericObjectQuery<?> abstractObjectQuery;

	public ObjectQueryHandler(Class<?> clazz, GenericObjectQuery<?> abstractObjectQuery, ObjectQueryHandler parent, String name) {
		super(clazz, parent, name);
		this.abstractObjectQuery = abstractObjectQuery;
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

}
