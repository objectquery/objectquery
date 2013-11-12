package org.objectquery;

import java.util.List;

import org.objectquery.generic.GenericInsertQuery;
import org.objectquery.generic.GenericSelectQuery;
import org.objectquery.generic.GenericUpdateQuery;
import org.objectquery.generic.GenericeDeleteQuery;

public abstract class QueryEngine<S> {
	private static final String IMPLEMENTAT_KEY = "org.objectquery.QueryEngine";

	@SuppressWarnings("unchecked")
	public static <T> QueryEngine<T> instance() {
		String className = System.getProperty(IMPLEMENTAT_KEY);
		if (className != null) {
			try {
				Class<?> cl = Class.forName(className);
				return (QueryEngine<T>) cl.newInstance();
			} catch (Exception e) {
				throw new RuntimeException("error to load class defined in " + IMPLEMENTAT_KEY + " system property", e);
			}
		}

		return null;
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
