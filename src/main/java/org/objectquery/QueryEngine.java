package org.objectquery;

import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.objectquery.generic.GenericInsertQuery;
import org.objectquery.generic.GenericSelectQuery;
import org.objectquery.generic.GenericUpdateQuery;
import org.objectquery.generic.GenericeDeleteQuery;
import org.objectquery.generic.ObjectQueryException;

public abstract class QueryEngine<S> {
	private static final String IMPLEMENTATION_KEY = "org.objectquery.QueryEngine";

	/**
	 * Create a new instance of the query engine for the specified session type.
	 * 
	 * the instance created is state-less and multi-threading safe, reuse it.
	 * 
	 * @param sessionType
	 *            the session type used for find the query engine.
	 * @return a new instance of the query engine.
	 */
	@SuppressWarnings("unchecked")
	public static <T> QueryEngine<T> instance(Class<T> sessionType) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Enumeration<URL> res = loader.getResources("META-INF/ObjectQueryEngine.properties");
			Properties p = new Properties();
			while (res.hasMoreElements()) {
				p.load(res.nextElement().openStream());
				String clazz = p.getProperty(sessionType.getName());
				if (clazz != null) {
					Class<?> cl = Class.forName(clazz);
					return (QueryEngine<T>) cl.newInstance();
				}
			}
		} catch (Exception e) {
			throw new ObjectQueryException("Error on QueryEngine lookup", e);
		}
		throw new ObjectQueryException("Impossible to find any QueryEngine implementation in the classpaht for the specifed session type");
	}

	/**
	 * Create an new instance of query engine for the engine implementation
	 * present in the classpath.
	 * 
	 * the instance created is state-less and multi-threading safe, reuse it.
	 * 
	 * 
	 * @return the new instance.
	 */
	@SuppressWarnings("unchecked")
	public static <T> QueryEngine<T> defaultInstance() {
		String className = System.getProperty(IMPLEMENTATION_KEY);
		if (className != null) {
			try {
				Class<?> cl = Class.forName(className);
				return (QueryEngine<T>) cl.newInstance();
			} catch (Exception e) {
				throw new RuntimeException("error to load class defined in " + IMPLEMENTATION_KEY + " system property", e);
			}
		}
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("META-INF/ObjectQueryEngine.properties");
		if (stream == null)
			throw new ObjectQueryException("Impossible to find any implementation of QueryEngine in the classpath");
		try {
			Properties prop = new Properties();
			prop.load(stream);
			Class<?> cl = Class.forName(prop.getProperty(IMPLEMENTATION_KEY));
			return (QueryEngine<T>) cl.newInstance();
		} catch (Exception e) {
			throw new ObjectQueryException("error to load class defined in the property " + IMPLEMENTATION_KEY
					+ " of the file META-INF/ObjectQueryEngine.properties", e);
		}
	}

	/**
	 * Create a new select query on the specified target
	 * 
	 * usage example: <code>
	 * QueryEngine&lt;YourSession&gt; engine = ....
	 * SelectQuery&lt;Persongt; select = engine.newSelect(Person.class);
	 * Person target =select.target();
	 * select.prj(target.getName());
	 * select.prj(target.getSurname()); 
	 * select.eq(target.getName(),"expected name"); 
	 * </code>
	 * 
	 * @param target
	 * @return new select query.
	 */
	public <T> SelectQuery<T> newSelect(Class<T> target) {
		return new GenericSelectQuery<T, Object>(target);
	}

	/**
	 * Create a new delete query on the specified target.
	 * 
	 * usage example: <code>
	 * QueryEngine&lt;YourSessiongt; engine = ....
	 * DeleteQuery&lt;Persongt; delete = engine.newDelete(Person.class);
	 * Person target =delete.target();
	 * delete.eq(target.getName(),"expected name"); 
	 * </code>
	 * 
	 * @param target
	 * @return new delete query.
	 */
	public <T> DeleteQuery<T> newDelete(Class<T> target) {
		return new GenericeDeleteQuery<T>(target);
	}

	/**
	 * Create a new update query on the specified target.
	 * 
	 * usage example: <code>
	 * QueryEngine&lt;YourSessiongt; engine = ....
	 * UpdateQuery&lt;Persongt; update = engine.newUpdate(Person.class);
	 * Person target =update.target(); 
	 * update.set(target.getName(),"new name");
	 * update.eq(target.getName(),"expected name"); 
	 * </code>
	 * 
	 * @param target
	 * @return new update query.
	 */
	public <T> UpdateQuery<T> newUpdate(Class<T> target) {
		return new GenericUpdateQuery<T>(target);
	}

	/**
	 * Create a new Insert query on the specified target.
	 * 
	 * usage example: <code>
	 * QueryEngine&lt;YourSessiongt; engine = ....
	 * InserQuery&lt;Persongt; insert = engine.newInsert(Person.class);
	 * Person target =insert.target(); 
	 * insert.set(target.getName(),"the name");
	 * insert.set(target.getSurname(),"the surname"); 
	 * </code>
	 * 
	 * @param target
	 * @return new insert query.
	 */
	public <T> InsertQuery<T> newInsert(Class<T> target) {
		return new GenericInsertQuery<T>(target);
	}

	/**
	 * Execute a select and return a list of result.
	 * 
	 * usage example: <code>
	 * QueryEngine engine = ... 
	 * SelectQuery select = ...
	 * Person target = select.target();
	 * select.eq(target.getName(),"expected name");
	 * List<Persongt; result = engine.execute(select,yourSessionInstance);
	 * </code>
	 * 
	 * @param query
	 *            to execute
	 * @param engineSession
	 *            current implementation session
	 * @return the result of the select
	 */
	public abstract <RET extends List<?>> RET execute(SelectQuery<?> query, S engineSession);

	/**
	 * Execute a delete and return the number of deleted records.
	 * 
	 * usage example: <code>
	 * QueryEngine engine = ... 
	 * DeleteQuery delete = ...
	 * Person target = delete.target();
	 * delete.eq(target.getName(),"expected name");
	 * engine.execute(delete,yourSessionInstance);
	 * </code>
	 * 
	 * @param query
	 *            to execute
	 * @param engineSession
	 *            current implementation session
	 * @return the number of deleted record.
	 */
	public abstract int execute(DeleteQuery<?> dq, S engineSession);

	/**
	 * Execute an insert
	 * 
	 * usage example: <code>
	 * QueryEngine engine = ... 
	 * InsertQuery insert = ...
	 * Person target = insert.target();
	 * insert.set(target.getName(),"new name");
	 * engine.execute(insert,yourSessionInstance);
	 * </code>
	 * 
	 * @param query
	 *            to execute
	 * @param engineSession
	 *            current implementation session
	 * @return true if the insert is executed correctly.
	 */
	public abstract boolean execute(InsertQuery<?> ip, S engineSession);

	/**
	 * Execute an update
	 * 
	 * usage example: <code>
	 * QueryEngine engine = ... 
	 * UpdateQuery update = ...
	 * Person target = update.target();
	 * update.set(target.getName(),"new name");
	 * update.eq(target.getName(),"current name");
	 * engine.execute(update,yourSessionInstance);
	 * </code>
	 * 
	 * @param query
	 *            to execute
	 * @param engineSession
	 *            current implementation session
	 * @return the number of updated record
	 */
	public abstract int execute(UpdateQuery<?> query, S engineSession);

}
