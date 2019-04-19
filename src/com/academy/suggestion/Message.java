package com.academy.suggestion;

public class Message {

	public static void printWelcomeMenu() {
		System.out.println(" ");
		System.out.println("Welcome!");
		System.out.println("1 - Login");
		System.out.println("2 - Register");
		System.out.println("0 - Exit");

	}

	public static void printGoodBye() {
		System.out.println(" ");
		System.out.println(" *** Goodbye ***");
	}

	public static void printLoginMenu() {
		System.out.println(" ");
		System.out.println("*** Login Menu ***");
		System.out.println("1 - Login as a Client");
		System.out.println("2 - Login as Admin");
		System.out.println("0 - Back to Welcome Menu");
	}

	public static void printLoginForm() {
		System.out.println(" ");
		System.out.println("*** Login ***");
		System.out.println("Enter your Username: ");
		System.out.println("Enter your Password: ");
	}

	public static void printRegisterMenu() {
		System.out.println(" ");
		System.out.println("*** Register Menu ***");
		System.out.println("Enter your information below: First Username then the password: ");
		System.out.println();
	}

	public static void printClientHomePage(String name) {
		System.out.println(" ");
		System.out.println("*** " + name + " HomePage ***");
		System.out.println("1 - View Profile");
		System.out.println("2 - Edit Profile");
		System.out.println("3 - View Categories");
		System.out.println("4 - Suggest Categories");
		System.out.println("5 - Like Post ");
		System.out.println("6 - View Messages");
		System.out.println("0 - Log Out");

	}

	public static void printAdminHomePage() {
		System.out.println(" ");
		System.out.println("*** Admin HomePage ***");
		System.out.println("1 - Create Category");
		System.out.println("2 - Create SubCategory");
		System.out.println("3 - Create Post");
		System.out.println("4 - Manage Suggestion Requests");

	}

}
