package org.objectquery.generic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import org.junit.Test;
import org.objectquery.BaseSelectQuery;
import org.objectquery.SelectQuery;
import org.objectquery.generic.domain.Cat;
import org.objectquery.generic.domain.Dog;
import org.objectquery.generic.domain.Person;

public class TestGenericQuery {

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectCondition() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.condition(new Object(), null, null, null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testInvalidCall() {
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.target().setDog(null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectProjection() {
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.prj(new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectProjectionType() {
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.prj(new Object(), null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectOrder() {
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.order(new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongObjectOrderType() {
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.order(new Object(), null);
	}

	@Test(expected = NullPointerException.class)
	public void testWrongTypeCondition() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.condition(query.target(), null, null, null);
	}

	@Test(expected = NullPointerException.class)
	public void testWrongNullCondition() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.condition(null, null, null, null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongValueTypeCondition() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.eq((Object) query.target(), new Object());
	}

	@Test(expected = ObjectQueryException.class)
	public void testInWrongValueTypeCondition() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.condition(query.target(), ConditionType.IN, new Object(), null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testNotInWrongValueTypeCondition() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.condition(query.target(), ConditionType.NOT_IN, new Object(), null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testContainsWrongValueTypeCondition() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.condition(query.target(), ConditionType.CONTAINS, new Object(), null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testNotContainsWrongValueTypeCondition() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.condition(query.target(), ConditionType.NOT_CONTAINS, new Object(), null);
	}

	@Test(expected = NullPointerException.class)
	public void testNullProjection() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.prj(null);
	}

	@Test(expected = NullPointerException.class)
	public void testNullOrder() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.order(null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongProxy() throws Exception {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		ProxyFactory pf = new ProxyFactory();
		Object o = pf.create(null, null, new MethodHandler() {

			public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
				return null;
			}
		});
		query.eq(o, (Object) null);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongProxyPrjection() throws Exception {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		ProxyFactory pf = new ProxyFactory();
		Object o = pf.create(null, null, new MethodHandler() {

			public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
				return null;
			}
		});
		query.prj(o);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(expected = ObjectQueryException.class)
	public void testWrongNestedQueryType() throws Exception {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.eq(query.target().getDog(), (BaseSelectQuery) new GenericSelectQuery<Person, Object>(Person.class));
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongNestedQueryProjection() throws Exception {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		SelectQuery<Dog> dogq = new GenericSelectQuery<Dog, Object>(Dog.class);
		dogq.prj(dogq.target().getOwner());
		query.eq(query.target().getDog(), dogq);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongBoxValue() throws Exception {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.box(3);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongBoxType() throws Exception {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		double price = query.target().getHome().getWeight();
		query.target().getHome().getWeight();
		query.box(price);
	}

	@Test(expected = ObjectQueryException.class)
	public void testWrongWithoutBox() throws Exception {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.eq(query.target().getHome().getWeight(), 3);
	}

	@Test
	public void testSimpleQueryBuild() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(builder, Person.class);
		Person toSearch = query.target();
		query.eq(toSearch.getHome().getAddress(), "rue d'anton");
		query.eq(toSearch.getMom().getName(), "elisabeth");
		query.order(toSearch.getName());

		builder.build();
		assertEquals("There is more conditions than expected", builder.getConditionsString().size(), 2);
		assertEquals("There is more orders than expected", builder.getOrdersString().size(), 1);

		assertTrue("Not present expected condition", builder.getConditionsString().contains("home.address EQUALS rue d'anton"));
		assertTrue("Not present expected condition", builder.getConditionsString().contains("mom.name EQUALS elisabeth"));
		assertTrue("Not present expected order", builder.getOrdersString().contains("name"));
	}

	@Test
	public void testInterfaceQuery() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Cat> query = new GenericSelectQuery<Cat, Object>(builder, Cat.class);
		query.eq(query.target().getName(), "viviane");
		builder.build();
		assertEquals("There is more conditions than expected", builder.getConditionsString().size(), 1);
		assertTrue("Not present expected condition", builder.getConditionsString().contains("name EQUALS viviane"));
	}

	@Test(expected = ObjectQueryException.class)
	public void testInvalidSubQueryCondition() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.eq(query.target(), new GenericSelectQuery<Person, Object>(Person.class));
	}

	@Test(expected = ObjectQueryException.class)
	public void testInvalidSubQueryProjection() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.prj(new GenericSelectQuery<Person, Object>(Person.class));
	}

	@Test(expected = ObjectQueryException.class)
	public void testInvalidSubQueryOrder() {
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(Person.class);
		query.order(new GenericSelectQuery<Person, Object>(Person.class));
	}

	@Test
	public void testClean() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(builder, Person.class);
		Person toSearch = query.target();
		query.prj(toSearch.getHome().getAddress());
		query.prj(query.subQuery(Person.class));
		query.eq(toSearch.getHome().getAddress(), "rue d'anton");
		query.eq(toSearch.getMom().getName(), "elisabeth");
		query.eq(toSearch.getMom().getName(), toSearch.getDad().getName());
		query.eq(toSearch.getMom(), query.subQuery(Person.class));
		query.having(toSearch.getMom().getName(), ProjectionType.COUNT).eq(query.box(toSearch.getHome().getPrice()));
		query.order(toSearch.getName());
		query.order(query.subQuery(Person.class));

		builder.build();
		query.clear();
	}

}
