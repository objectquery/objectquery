package org.objectquery.generic;

import java.util.List;

import org.objectquery.DeleteQuery;
import org.objectquery.InsertQuery;
import org.objectquery.QueryEngine;
import org.objectquery.SelectMapQuery;
import org.objectquery.SelectQuery;
import org.objectquery.UpdateQuery;

public class SysTestQueryEngine extends QueryEngine<Object> {

	@Override
	public List<?> execute(SelectQuery<?> query, Object engineSession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int execute(DeleteQuery<?> dq, Object engineSession) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean execute(InsertQuery<?> ip, Object engineSession) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int execute(UpdateQuery<?> query, Object engineSession) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <M> List<M> execute(SelectMapQuery<?, M> query, Object engineSession) {
		// TODO Auto-generated method stub
		return null;
	}

}
