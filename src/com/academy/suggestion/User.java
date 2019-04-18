package com.academy.suggestion;

enum Gender {
	F, M;
}

public abstract class User {

	private String name;
	private String username;
	private String password;
	private String email;
	private Gender gender;
	private int age;

	public User(String username, String password) {
		this.setUsername(username);
		this.password = password;
	}

	public User(String name, String username, String password, String email, Gender gender, int age) {

		this(username, password);
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.age = age;
	}

	public void showMyInfo(String name) {
		System.out.println("*** " + name + " Profile ****");
		System.out.println("Name : " + this.name);
		System.out.println("Username : " + this.username);
		System.out.println("Email : " + this.email);
		System.out.println("Gender : " + this.gender);
		System.out.println("Age : " + this.age);

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", gender=" + gender + ", age=" + age + "]";
	}

}