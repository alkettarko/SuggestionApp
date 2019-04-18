package com.academy.suggestion;

import java.util.InputMismatchException;
import java.util.Scanner;
import static com.academy.suggestion.Message.*;

public class Main {

	private static Scanner scan = new Scanner(System.in);
	private static Database database = new Database();
	private static int INPUT = -1;
	private static String emailRegEx = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	private static String onlyLetterRegex = "[a-zA-Z]+";

	private static void resetInput() {
		INPUT = -1;
	}

	public static void register() {
		System.out.println("*** Registration Menu ***");
		System.out.println("Enter Name");
		String name = scan.next();
		System.out.println("Enter Username");
		String username = scan.next();
		// create method exist user at database

		System.out.println("Enter Password");
		String password = scan.next();
		System.out.println("Reenter Password");
		String repeatedPassword = scan.next();
		System.out.println("Enter Email");
		String email = scan.next();
		System.out.println("Enter Gender");
		String gender = scan.next();
		System.out.println("Enter Age");
		int age = 0;
		String errors = "";
		try {
			age = scan.nextInt();
			if (!(age > 10 && age < 100)) {
				errors = errors + "Age must be between 10 - 100! \n";
			}
		} catch (InputMismatchException e) {
			errors = errors + "Age must be a number! \n";
		}

		if (!(name.matches(onlyLetterRegex))) {
			errors = errors + "Name must contain only characters \n";
		}
		if (!password.equals(repeatedPassword)) {
			errors = errors + "You must enter the same password! \n";
		}
		if (password.length() < 5) {
			errors = errors + "Password must not be under 5 characters! \n";
		}

		if (!email.matches(emailRegEx)) {
			errors = errors + "Email is not in the right format! \n";
		}

		if (!(gender.equals(Gender.F.toString()) || gender.equals(Gender.M.toString()))) {
			errors = errors + "Gender must be F or M! \n";
		}

		if (errors.length() == 0) {

			Client client = new Client(username, password);
			client.setName(name);
			client.setPassword(password);
			client.setEmail(email);
			client.setGender(Gender.M);
			if (gender.equals(Gender.F.toString())) {
				client.setGender(Gender.F);
			}
			client.setAge(age);

			database.addClient(client);
		} else {
			System.out.println("Registration Failed!");
			System.out.println(errors);
		}

	}

	private static void createCategory() {
		System.out.println("Enter the Name of the Category");
		String categoryName = scan.next();
		System.out.println("Enter the Description of the Category");
		String descriptionName = scan.next();
		if (database.existCategory(categoryName)) {
			System.out.println("Creation Failed. The name already exists.");
		} else {
			Category category = new Category(categoryName);
			category.setDescription(descriptionName);
			database.addCategory(category);
			System.out.println("Category: " + category + " was added successfully.");
		}
	}

	private static void createSubCategory() {
		System.out.println("*** Choose to Which Category would you want to add SubCategory ***");
		printCategories();
		int index = scan.nextInt();
		if (index > database.getCategories().size()) {
			System.out.println("The number you entered is bigger than the List");
		} else {
			System.out.println("Enter the Name of the new SubCategory: ");
			String subCategoryName = scan.next();
			Category subCategory = new Category(subCategoryName);
			database.getCategories().get(index).addSubCategory(subCategory);
			System.out.println("SubCategory  created succesfully. ");
		}
	}

	public static void createPost() {
		System.out.println("*** Create Post ***");
		System.out.println("Choose the Category: ");
		printCategories();
		int categoryIndex = scan.nextInt();
		if (categoryIndex > database.getCategories().size()) {
			System.out.println("The number you entered is bigger than the List");
		} else {
			System.out.println("Choose the SubCategory: ");
			printSubCategories();
			int subIndex = scan.nextInt();
			if (subIndex > database.getCategories().get(subIndex).getSubCategories().size()) {
				System.out.println("The number you entered is bigger than the List");
			} else {
				System.out.println("Enter the name of the post: ");
				String postName = scan.next();
				System.out.println("Enter the description of the post: ");
				String postDescription = scan.next();
				Post post = new Post(postName, postDescription);
				database.getCategories().get(categoryIndex).getSubCategories().get(subIndex).addPost(post);
				System.out.println("Post added Successfully. ");
			}
		}
	}

	public static void printCategories() {

		if (database.getCategories().isEmpty()) {
			System.out.println("The Categories list is empty.");
		} else {
			for (int i = 0; i < database.getCategories().size(); i++) {
				System.out.println("Id = " + i + " Name= " + database.getCategories().get(i).getName());
			}
		}
	}

	public static void printSubCategories() {
		for (int i = 0; i < database.getCategories().get(i).getSubCategories().size(); i++) {
			System.out.println(
					"Id = " + i + " Name= " + database.getCategories().get(i).getSubCategories().get(i).getName());
		}
	}

	public static void suggestCategory(Client loggedClient) {
		System.out.println("Enter Category Name: ");
		String categoryName = scan.next();
		Category category = new Category(categoryName);
		Suggestion suggestion = new Suggestion(category, loggedClient);
		database.addSuggestion(suggestion);
		System.out.println("Suggestion added successfully! ");

	}

	public static void main(String[] args) {

		System.out.println("The database is loading...");
		database.initialize();
		System.out.println("The database is loaded successfully.");
		do {
			printWelcomeMenu();
			INPUT = scan.nextInt();
			switch (INPUT) {

			case 1: // Login Menu
				do {
					printLoginMenu();
					INPUT = scan.nextInt();
					switch (INPUT) {
					case 1: // Login as Client

						printLoginForm();
						String username = scan.next();
						String password = scan.next();
						Client loggedClient = database.loginAsClient(username, password);
						// Check if the user is found
						if (loggedClient == null) {
							System.out.println("User not found");
						} else { // When user is found
							printClientHomePage(loggedClient.getUsername());
							INPUT = scan.nextInt();
							switch (INPUT) {
							case 1: // View profile
								loggedClient.showMyInfo(loggedClient.getName());
								break;
							case 2: // view categories
								printCategories();
								break;
							case 3:// suggest category
								suggestCategory(loggedClient);
								break;
							}
						}
						break;
					case 2: // Login as Admin
						printLoginForm();
						String username2 = scan.next();
						String password2 = scan.next();
						Admin loggedAdmin = database.loginAsAdmin(username2, password2);
						if (loggedAdmin == null) {
							System.out.println("User not found");
						} else { // Admin HomePage
							printAdminHomePage();
							INPUT = scan.nextInt();
							switch (INPUT) {
							case 1: // Create Category
								createCategory();
								break;
							case 2:// create subcategory
								createSubCategory();
								break;
							case 3: // create post
								createPost();
								break;
							}
						}
						break;
					}
				} while (INPUT != 0);
				resetInput();
				break;
			case 2: // Register Menu
				register();
				break;
			case 0:
				printGoodBye();
				break;
			default:
				System.out.println("Invalid input value!");
				break;
			}
		} while (INPUT != 0);
		scan.close();
	}

}
