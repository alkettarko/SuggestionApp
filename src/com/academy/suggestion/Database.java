package com.academy.suggestion;

import java.util.ArrayList;
import java.util.List;

public class Database {

	private List<Client> clients = new ArrayList<Client>();
	private List<Admin> admins = new ArrayList<Admin>();
	private List<Category> categories = new ArrayList<Category>();
	private List<Suggestion> suggestions = new ArrayList<Suggestion>();

	public void initialize() {
		loadClients();
		loadAdmins();
		loadCategories();
		loadSubCategories();
	}

	public boolean existCategory(String name) {
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public boolean existClient(String name) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getUsername().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public void addClient(Client client) {
		clients.add(client);
	}

	public void addCategory(Category category) {
		categories.add(category);
	}

	public void addSuggestion(Suggestion suggestion) {
		suggestions.add(suggestion);
	}

	private void loadClients() {
		String[] usernames = { "John", "Mike", "Jill", "Anna", "David" };
		String[] passwords = { "0000", "1111", "2222", "3333", "4444" };
		clients = new ArrayList<Client>();
		for (int i = 0; i < usernames.length; i++) {
			Client client = new Client(usernames[i], passwords[i]);
			clients.add(client);
		}
	}

	private void loadAdmins() {
		String[] usernames = { "Joey", "Fill", "adm4", "adm5", "adm6" };
		String[] passwords = { "0000", "1111", "2222", "3333", "4444" };
		admins = new ArrayList<Admin>();
		for (int i = 0; i < usernames.length; i++) {
			Admin admin = new Admin(usernames[i], passwords[i]);
			admins.add(admin);
		}
	}

	private void loadCategories() {
		String[] names = { "Music", "Movies", "Games", "Trips", "Books" };
		categories = new ArrayList<Category>();
		for (int i = 0; i < names.length; i++) {
			Category category = new Category(names[i]);
			category.setName(names[i]);
			categories.add(category);
		}
	}

	private void loadSubCategories() {
		String[] subCategoryNames = { "rock", "pop", "R&B", "Jazz" };
		String[] subCategoryNames2 = { "drama", "action", "horror", "comedy" };
		Category music = categories.get(0);
		Category movies = categories.get(1);
		for (int i = 0; i < subCategoryNames.length; i++) {
			Category subCategory = new Category(subCategoryNames[i]);
			Post post = new Post(subCategory.getName() + "Post");
			subCategory.addPost(post);
			music.addSubCategory(subCategory);
		}
		for (int i = 0; i < subCategoryNames2.length; i++) {
			Category subCategory = new Category(subCategoryNames[i]);
			Post post = new Post(subCategory.getName() + "Post");
			subCategory.addPost(post);
			movies.addSubCategory(subCategory);
		}
	}

	public Client loginAsClient(String username, String password) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getUsername().equals(username) && clients.get(i).getPassword().equals(password)) {
				return clients.get(i);
			}
		}
		return null;
	}

	public Admin loginAsAdmin(String username, String password) {
		for (int i = 0; i < admins.size(); i++) {
			if (admins.get(i).getUsername().equals(username) && admins.get(i).getPassword().equals(password)) {
				return admins.get(i);
			}
		}
		return null;
	}

	public List<Client> getClients() {
		return clients;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

}
