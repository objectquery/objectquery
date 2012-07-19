package org.objectquery.builder;

import java.util.ArrayList;
import java.util.List;

public class ConditionGroup implements ConditionElement, InternalConditionBuilder {

	public enum GroupType {
		AND, OR, NOT
	};

	private List<ConditionElement> conditions = new ArrayList<ConditionElement>();
	private GroupType type;

	public ConditionGroup(GroupType type) {
		this.type = type;
	}

	public GroupType getType() {
		return type;
	}

	public List<ConditionElement> getConditions() {
		return conditions;
	}

	void addCondition(ConditionElement element) {
		conditions.add(element);
	}

	public void condition(PathItem item, ConditionType type, Object value) {
		addCondition(new ConditionItem(item, type, value));
	}

	public ConditionGroup and() {
		ConditionGroup cg = new ConditionGroup(GroupType.AND);
		addCondition(cg);
		return cg;
	}

	public ConditionGroup or() {
		ConditionGroup cg = new ConditionGroup(GroupType.OR);
		addCondition(cg);
		return cg;
	}

	public ConditionGroup not() {
		ConditionGroup cg = new ConditionGroup(GroupType.NOT);
		addCondition(cg);
		return cg;
	}

}
