package org.objectquery.generic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.objectquery.BaseSelectQuery;
import org.objectquery.SelectQuery;
import org.objectquery.generic.domain.Person;

public class TestQueryCondition {

	@Test
	public void testPathValueQueryBuild() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericSelectQuery<Person, Object> query = new GenericSelectQuery<Person, Object>(builder, Person.class);
		Person toSearch = query.target();
		query.eq(toSearch.getHome().getAddress(), toSearch.getDog().getHome().getAddress());
		query.eq(toSearch.getDad().getHome(), toSearch.getDog().getHome());
		query.eq(toSearch.getMom().getName(), toSearch.getDog().getOwner().getName());
		query.order(toSearch.getName());
		builder.build();
		assertEquals("There is more conditions than expected", builder.getConditionsString().size(), 3);
		assertEquals("There is more orders than expected", builder.getOrdersString().size(), 1);

		assertEquals("Not present expected condition", "home.address EQUALS dog.home.address", builder.getConditionsString().get(0));
		assertEquals("Not present expected condition", "dad.home EQUALS dog.home", builder.getConditionsString().get(1));
		assertEquals("Not present expected condition", "mom.name EQUALS dog.owner.name", builder.getConditionsString().get(2));
		assertEquals("Not present expected order", builder.getOrdersString().get(0), "name");
	}

	@Test
	public void testConditionsTypes() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(builder, Person.class);
		Person toSearch = query.target();
		query.eq(toSearch.getMom().getName(), toSearch.getDad().getName());
		query.contains(toSearch.getFriends(), toSearch.getDad());
		query.in(toSearch.getMom(), toSearch.getDad().getFriends());
		query.like(toSearch.getMom().getName(), toSearch.getDad().getName());
		query.gt(toSearch.getMom().getName(), toSearch.getDad().getName());
		query.gtEq(toSearch.getMom().getName(), toSearch.getDad().getName());
		query.lt(toSearch.getMom().getName(), toSearch.getDad().getName());
		query.ltEq(toSearch.getMom().getName(), toSearch.getDad().getName());
		query.notEq(toSearch.getMom().getName(), toSearch.getDad().getName());
		query.notIn(toSearch.getMom(), toSearch.getDad().getFriends());
		query.notContains(toSearch.getMom().getFriends(), toSearch.getDad());
		query.notLike(toSearch.getMom().getName(), toSearch.getDad().getName());
		query.likeNc(toSearch.getMom().getName(), toSearch.getDad().getName());
		query.notLikeNc(toSearch.getMom().getName(), toSearch.getDad().getName());
		query.between(query.box(toSearch.getHome().getPrice()), query.box(toSearch.getMom().getHome().getPrice()),
				query.box(toSearch.getDad().getHome().getPrice()));
		builder.build();

		assertEquals(15, builder.getConditionsString().size());
		assertEquals("mom.name EQUALS dad.name", builder.getConditionsString().get(0));
		assertEquals("friends CONTAINS dad", builder.getConditionsString().get(1));
		assertEquals("mom IN dad.friends", builder.getConditionsString().get(2));
		assertEquals("mom.name LIKE dad.name", builder.getConditionsString().get(3));
		assertEquals("mom.name GREATER dad.name", builder.getConditionsString().get(4));
		assertEquals("mom.name GREATER_EQUALS dad.name", builder.getConditionsString().get(5));
		assertEquals("mom.name LESS dad.name", builder.getConditionsString().get(6));
		assertEquals("mom.name LESS_EQUALS dad.name", builder.getConditionsString().get(7));
		assertEquals("mom.name NOT_EQUALS dad.name", builder.getConditionsString().get(8));
		assertEquals("mom NOT_IN dad.friends", builder.getConditionsString().get(9));
		assertEquals("mom.friends NOT_CONTAINS dad", builder.getConditionsString().get(10));
		assertEquals("mom.name NOT_LIKE dad.name", builder.getConditionsString().get(11));
		assertEquals("mom.name LIKE_NOCASE dad.name", builder.getConditionsString().get(12));
		assertEquals("mom.name NOT_LIKE_NOCASE dad.name", builder.getConditionsString().get(13));
		assertEquals("home.price BETWEEN mom.home.price AND dad.home.price", builder.getConditionsString().get(14));

	}

	@Test
	public void testConditionsSubquery() {
		MockQueryBuilder builder = new MockQueryBuilder();
		SelectQuery<Person> query = new GenericSelectQuery<Person, Object>(builder, Person.class);
		Person toSearch = query.target();
		BaseSelectQuery<Person> subQuery = query.subQuery(Person.class);
		query.eq(toSearch.getMom(), subQuery);
		query.contains(toSearch.getFriends(), subQuery);
		query.in(toSearch.getMom(), subQuery);
		// query.like(toSearch.getMum(), new
		// GenericObjectQuery<Person>(Person.class));
		query.gt(toSearch.getMom(), subQuery);
		query.gtEq(toSearch.getMom(), subQuery);
		query.lt(toSearch.getMom(), subQuery);
		query.ltEq(toSearch.getMom(), subQuery);
		query.notEq(toSearch.getMom(), subQuery);
		query.notIn(toSearch.getMom(), subQuery);
		query.notContains(toSearch.getMom().getFriends(), subQuery);
		// query.notLike(toSearch.getMum().getName(),
		// toSearch.getDud().getName());
		// query.likeNc(toSearch.getMum().getName(),
		// toSearch.getDud().getName());
		// query.notLikeNc(toSearch.getMum().getName(),
		// toSearch.getDud().getName());
		builder.build();

		assertEquals("A.mom EQUALS select  from Person AA0", builder.getConditionsString().get(0));
		assertEquals("A.friends CONTAINS select  from Person AA0", builder.getConditionsString().get(1));
		assertEquals("A.mom IN select  from Person AA0", builder.getConditionsString().get(2));
		// assertEquals("mum.name LIKE dud.name",
		// builder.getConditionsString().get(3));
		assertEquals("A.mom GREATER select  from Person AA0", builder.getConditionsString().get(3));
		assertEquals("A.mom GREATER_EQUALS select  from Person AA0", builder.getConditionsString().get(4));
		assertEquals("A.mom LESS select  from Person AA0", builder.getConditionsString().get(5));
		assertEquals("A.mom LESS_EQUALS select  from Person AA0", builder.getConditionsString().get(6));
		assertEquals("A.mom NOT_EQUALS select  from Person AA0", builder.getConditionsString().get(7));
		assertEquals("A.mom NOT_IN select  from Person AA0", builder.getConditionsString().get(8));
		assertEquals("A.mom.friends NOT_CONTAINS select  from Person AA0", builder.getConditionsString().get(9));
		// assertEquals("mum.name NOT_LIKE dud.name",
		// builder.getConditionsString().get(11));
		// assertEquals("mum.name LIKE_NOCASE dud.name",
		// builder.getConditionsString().get(12));
		// assertEquals("mum.name NOT_LIKE_NOCASE dud.name",
	}
}
