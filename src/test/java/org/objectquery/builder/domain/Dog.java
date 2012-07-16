package org.objectquery.builder.domain;

public class Dog {

	private String name;
	private Person owner;
	private Home home;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

}
