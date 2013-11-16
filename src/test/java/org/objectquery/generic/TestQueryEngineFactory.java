package org.objectquery.generic;

import org.junit.Assert;
import org.junit.Test;
import org.objectquery.QueryEngine;

public class TestQueryEngineFactory {

	@Test
	public void testCreateFromDiscover() {
		QueryEngine<Object> q = QueryEngine.instance();
		Assert.assertTrue(q instanceof TestQueryEngine);
	}
	
	@Test
	public void testCreateFromSys() {
		System.setProperty("org.objectquery.QueryEngine", "org.objectquery.generic.SysTestQueryEngine");
		QueryEngine<Object> q = QueryEngine.instance();
		Assert.assertTrue(q instanceof SysTestQueryEngine);
	}
}
