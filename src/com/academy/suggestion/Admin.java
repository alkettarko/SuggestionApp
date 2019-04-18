package com.academy.suggestion;

public class Admin extends User {

	public Admin(String username, String password) {
		super(username, password);
	}

	public Admin(String name, String username, String password, String email, Gender gender, int age) {
		super(name, username, password, email, gender, age);

	}

}
