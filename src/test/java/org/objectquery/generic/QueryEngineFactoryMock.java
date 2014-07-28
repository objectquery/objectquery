package org.objectquery.generic;

import org.objectquery.QueryEngine;
import org.objectquery.QueryEngineFactory;

public class QueryEngineFactoryMock implements QueryEngineFactory {

	@Override
	public <S> QueryEngine<S> createQueryEngine(Class<S> targetSession) {
		if (Object.class.equals(targetSession))
			return createDefaultQueryEngine();
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> QueryEngine<T> createDefaultQueryEngine() {
		return (QueryEngine<T>) new TestQueryEngine();
	}

}
