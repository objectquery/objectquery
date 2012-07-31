package org.objectquery.builder;

import java.util.ArrayList;
import java.util.List;

public class ConditionGroup implements ConditionElement, InternalConditionBuilder {

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

	public ConditionGroup newGroup(GroupType type) {
		ConditionGroup cg = new ConditionGroup(type);
		addCondition(cg);
		return cg;
	}
}
