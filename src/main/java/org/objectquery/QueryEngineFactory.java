package org.objectquery;

/**
 * Implement this for add query engine implementations.
 * 
 * @author tglman
 *
 */
public interface QueryEngineFactory {

	/**
	 * Create an engine implementation for the specified session type. if the
	 * implementation not support the type return null.
	 * 
	 * @param targetSession
	 *            type of the session that should be supported by the engine.
	 * @return the engine or null.
	 */
	<S> QueryEngine<S> createQueryEngine(Class<S> targetSession);

	/**
	 * Create the default Query engine for the specified factory.
	 * 
	 * @return the query engine instance or null in case is not supported.
	 */
	<T> QueryEngine<T> createDefaultQueryEngine();

}
