package org.objectquery.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.ObjectQuery;
import org.objectquery.generic.ConditionType;
import org.objectquery.generic.GenericObjectQuery;
import org.objectquery.generic.ObjectQueryException;
import org.objectquery.generic.domain.Person;

public class TestGenericObjectQuery {

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectCondition() {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.condition(new Object(), null, null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectProjection() {
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.prj(new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectProjectionType() {
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.prj(new Object(), null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectOrder() {
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.order(new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectOrderType() {
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.order(new Object(), null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongTypeCondition() {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.condition(query.target(), null, null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongNullCondition() {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.condition(null, null, null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongValueTypeCondition() {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.eq((Object) query.target(), new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testInWrongValueTypeCondition() {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.condition(query.target(), ConditionType.IN, new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testNotInWrongValueTypeCondition() {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.condition(query.target(), ConditionType.NOT_IN, new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testContainsWrongValueTypeCondition() {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.condition(query.target(), ConditionType.CONTAINS, new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testNotContainsWrongValueTypeCondition() {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.condition(query.target(), ConditionType.NOT_CONTAINS, new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testNullProjection() {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.prj(null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testNullOrder() {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		query.order(null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongProxy() throws Exception {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		ProxyFactory pf = new ProxyFactory();
		Object o = pf.create(null, null, new MethodHandler() {

			public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
				return null;
			}
		});
		query.eq(o, null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongProxyPrjection() throws Exception {
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(null, Person.class);
		ProxyFactory pf = new ProxyFactory();
		Object o = pf.create(null, null, new MethodHandler() {

			public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
				return null;
			}
		});
		query.prj(o);
	}

	@Test
	public void testSimpleQueryBuild() {
		MockQueryBuilder builder = new MockQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();
		query.eq(toSearch.getHome().getAddress(), "rue d'anton");
		query.eq(toSearch.getMum().getName(), "elisabeth");
		query.order(toSearch.getName());

		builder.build();
		Assert.assertEquals("There is more conditions than expected", builder.getConditionsString().size(), 2);
		Assert.assertEquals("There is more orders than expected", builder.getOrdersString().size(), 1);

		Assert.assertTrue("Not present expected condition", builder.getConditionsString().contains("home.address EQUALS rue d'anton"));
		Assert.assertTrue("Not present expected condition", builder.getConditionsString().contains("mum.name EQUALS elisabeth"));
		Assert.assertTrue("Not present expected order", builder.getOrdersString().contains("name"));
	}

}
