package org.objectquery;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.objectquery.generic.GenericInsertQuery;
import org.objectquery.generic.GenericSelectQuery;
import org.objectquery.generic.GenericUpdateQuery;
import org.objectquery.generic.GenericeDeleteQuery;
import org.objectquery.generic.ObjectQueryException;

public abstract class QueryEngine<S> {
	private static final String IMPLEMENTATION_KEY = "org.objectquery.QueryEngine";

	@SuppressWarnings("unchecked")
	public static <T> QueryEngine<T> instance() {
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

	public <T> SelectQuery<T> newSelect(Class<T> target) {
		return new GenericSelectQuery<T>(target);
	}

	public <T> DeleteQuery<T> newDelete(Class<T> target) {
		return new GenericeDeleteQuery<T>(target);
	}

	public <T> UpdateQuery<T> newUpdate(Class<T> target) {
		return new GenericUpdateQuery<T>(target);
	}

	public <T> InsertQuery<T> newInsert(Class<T> target) {
		return new GenericInsertQuery<T>(target);
	}

	public abstract <RET extends List<?>> RET execute(SelectQuery<?> query, S engineSession);

	public abstract int execute(DeleteQuery<?> dq, S engineSession);

	public abstract boolean execute(InsertQuery<?> ip, S engineSession);

	public abstract int execute(UpdateQuery<?> query, S engineSession);

}
