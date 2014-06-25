package org.objectquery.generic;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.objectquery.QueryEngine;

public class TestQueryEngineFactory {

	@Test
	public void testCreateFromDiscover() {
		QueryEngine<Object> q = QueryEngine.defaultInstance();
		System.out.println(q);
		assertTrue(q instanceof TestQueryEngine);
	}

	@Test
	public void testCreateFromDiscoverBySession() {
		QueryEngine<Object> q = QueryEngine.instance(Object.class);
		assertTrue(q instanceof TestQueryEngine);
	}

	@Test
	public void testCreateFromSys() {
		System.setProperty("org.objectquery.QueryEngine", "org.objectquery.generic.SysTestQueryEngine");
		QueryEngine<Object> q = QueryEngine.defaultInstance();
		assertTrue(q instanceof SysTestQueryEngine);
	}

	@After
	public void after() {
		System.clearProperty("org.objectquery.QueryEngine");
	}

}
