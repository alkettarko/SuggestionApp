package com.academy.suggestion;

public abstract class User {
	private int id;
	private String name;
	private String username;
	private String password;
	private String email;
	private String gender;
	private int age;
	
	public User(String username, String password) {
		this.setUsername(username);
		this.password = password;
	}
	
	public User(String name, String username, String password, String email, String gender, int age) {

		this(username, password);
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", email="
				+ email + ", gender=" + gender + ", age=" + age + "]";
	}
	
	

	
	

}