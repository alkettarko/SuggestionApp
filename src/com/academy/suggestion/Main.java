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

// TODO VALIDIME mos te jete 2 here 1 kategori metoda
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

	public static void printCategories() {

		if (database.getCategories().isEmpty()) {
			System.out.println("The Categories list is empty.");
		} else {
			for (int i = 0; i < database.getCategories().size(); i++) {
				System.out.println("Id = " + i + " Name= " + database.getCategories().get(i).getName());
			}
		}
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
								loggedClient.showMyInfo(); // mungon inplementimi
								break;
							case 2:
								printCategories();
								break;
							case 3:// suggest category
								/**
								 * futni emrin krijo kategori me ate emer krijo suggest me ate kategori shto te
								 * lista suggestion
								 */
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
									// 1 shfaq kategorite
									// zgjidh kategori
									// Index nuk duhet te jete me i madh se gjatsia e listes me kategori
									// shto subkategori
									// database.getcategories.get(index).addsubcategory(subcateggory)
								break;

							case 3: // create post
								/**
								 * print kategori zgjidh katego print nenkategori zgjidh nenkate fut te dhenate
								 * e post
								 */

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
