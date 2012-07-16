package org.objectquery.builder.domain;

import java.util.List;

public class Person {
	private String name;
	private List<Person> friends;
	private Person mum;
	private Person dud;
	private Home home;
	private Dog dog;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}

	public Person getMum() {
		return mum;
	}

	public void setMum(Person mum) {
		this.mum = mum;
	}

	public Person getDud() {
		return dud;
	}

	public void setDud(Person dud) {
		this.dud = dud;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

}
