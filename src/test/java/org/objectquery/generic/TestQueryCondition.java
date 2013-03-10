package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.ObjectQuery;
import org.objectquery.generic.domain.Person;

public class TestQueryCondition {

	@Test
	public void testPathValueQueryBuild() {
		MockQueryBuilder builder = new MockQueryBuilder();
		GenericObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();
		query.eq(toSearch.getHome().getAddress(), toSearch.getDog().getHome().getAddress());
		query.eq(toSearch.getDud().getHome(), toSearch.getDog().getHome());
		query.eq(toSearch.getMum().getName(), toSearch.getDog().getOwner().getName());
		query.order(toSearch.getName());
		builder.build();
		Assert.assertEquals("There is more conditions than expected", builder.getConditionsString().size(), 3);
		Assert.assertEquals("There is more orders than expected", builder.getOrdersString().size(), 1);

		Assert.assertEquals("Not present expected condition", "home.address EQUALS dog.home.address", builder.getConditionsString().get(0));
		Assert.assertEquals("Not present expected condition", "dud.home EQUALS dog.home", builder.getConditionsString().get(1));
		Assert.assertEquals("Not present expected condition", "mum.name EQUALS dog.owner.name", builder.getConditionsString().get(2));
		Assert.assertEquals("Not present expected order", builder.getOrdersString().get(0), "name");
	}

	@Test
	public void testConditionsTypes() {
		MockQueryBuilder builder = new MockQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();
		query.eq(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.contains(toSearch.getFriends(), toSearch.getDud());
		query.in(toSearch.getMum(), toSearch.getDud().getFriends());
		query.like(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.gt(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.gtEq(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.lt(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.ltEq(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.notEq(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.notIn(toSearch.getMum(), toSearch.getDud().getFriends());
		query.notContains(toSearch.getMum().getFriends(), toSearch.getDud());
		query.notLike(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.likeNc(toSearch.getMum().getName(), toSearch.getDud().getName());
		query.notLikeNc(toSearch.getMum().getName(), toSearch.getDud().getName());
		builder.build();

		Assert.assertEquals("mum.name EQUALS dud.name", builder.getConditionsString().get(0));
		Assert.assertEquals("friends CONTAINS dud", builder.getConditionsString().get(1));
		Assert.assertEquals("mum IN dud.friends", builder.getConditionsString().get(2));
		Assert.assertEquals("mum.name LIKE dud.name", builder.getConditionsString().get(3));
		Assert.assertEquals("mum.name GREATER dud.name", builder.getConditionsString().get(4));
		Assert.assertEquals("mum.name GREATER_EQUALS dud.name", builder.getConditionsString().get(5));
		Assert.assertEquals("mum.name LESS dud.name", builder.getConditionsString().get(6));
		Assert.assertEquals("mum.name LESS_EQUALS dud.name", builder.getConditionsString().get(7));
		Assert.assertEquals("mum.name NOT_EQUALS dud.name", builder.getConditionsString().get(8));
		Assert.assertEquals("mum NOT_IN dud.friends", builder.getConditionsString().get(9));
		Assert.assertEquals("mum.friends NOT_CONTAINS dud", builder.getConditionsString().get(10));
		Assert.assertEquals("mum.name NOT_LIKE dud.name", builder.getConditionsString().get(11));
		Assert.assertEquals("mum.name LIKE_NOCASE dud.name", builder.getConditionsString().get(12));
		Assert.assertEquals("mum.name NOT_LIKE_NOCASE dud.name", builder.getConditionsString().get(13));

	}

	@Test
	public void testConditionsSubquery() {
		MockQueryBuilder builder = new MockQueryBuilder();
		ObjectQuery<Person> query = new GenericObjectQuery<Person>(builder, Person.class);
		Person toSearch = query.target();
		ObjectQuery<Person> subQuery = query.subQuery(Person.class);
		query.eq(toSearch.getMum(), subQuery);
		query.contains(toSearch.getFriends(), subQuery);
		query.in(toSearch.getMum(), subQuery);
		// query.like(toSearch.getMum(), new
		// GenericObjectQuery<Person>(Person.class));
		query.gt(toSearch.getMum(), subQuery);
		query.gtEq(toSearch.getMum(), subQuery);
		query.lt(toSearch.getMum(), subQuery);
		query.ltEq(toSearch.getMum(), subQuery);
		query.notEq(toSearch.getMum(), subQuery);
		query.notIn(toSearch.getMum(), subQuery);
		query.notContains(toSearch.getMum().getFriends(), subQuery);
		// query.notLike(toSearch.getMum().getName(),
		// toSearch.getDud().getName());
		// query.likeNc(toSearch.getMum().getName(),
		// toSearch.getDud().getName());
		// query.notLikeNc(toSearch.getMum().getName(),
		// toSearch.getDud().getName());
		builder.build();

		Assert.assertEquals("A.mum EQUALS select  from Person AA0", builder.getConditionsString().get(0));
		Assert.assertEquals("A.friends CONTAINS select  from Person AA0", builder.getConditionsString().get(1));
		Assert.assertEquals("A.mum IN select  from Person AA0", builder.getConditionsString().get(2));
		// Assert.assertEquals("mum.name LIKE dud.name",
		// builder.getConditionsString().get(3));
		Assert.assertEquals("A.mum GREATER select  from Person AA0", builder.getConditionsString().get(3));
		Assert.assertEquals("A.mum GREATER_EQUALS select  from Person AA0", builder.getConditionsString().get(4));
		Assert.assertEquals("A.mum LESS select  from Person AA0", builder.getConditionsString().get(5));
		Assert.assertEquals("A.mum LESS_EQUALS select  from Person AA0", builder.getConditionsString().get(6));
		Assert.assertEquals("A.mum NOT_EQUALS select  from Person AA0", builder.getConditionsString().get(7));
		Assert.assertEquals("A.mum NOT_IN select  from Person AA0", builder.getConditionsString().get(8));
		Assert.assertEquals("A.mum.friends NOT_CONTAINS select  from Person AA0", builder.getConditionsString().get(9));
		// Assert.assertEquals("mum.name NOT_LIKE dud.name",
		// builder.getConditionsString().get(11));
		// Assert.assertEquals("mum.name LIKE_NOCASE dud.name",
		// builder.getConditionsString().get(12));
		// Assert.assertEquals("mum.name NOT_LIKE_NOCASE dud.name",
		// builder.getConditionsString().get(13));

	}
}
