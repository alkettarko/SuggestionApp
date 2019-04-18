package com.academy.suggestion;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {

	private List<Category> subscribedCategories = new ArrayList<Category>();

	public Client(String username, String password) {
		super(username, password);
	}

	public Client(String name, String username, String password, String email, Gender gender, int age) {
		super(name, username, password, email, gender, age);
	}

	public void subscribeCategory(Category category) {
		subscribedCategories.add(category);

	}

	public void printSubscribedCategory() {
		if (subscribedCategories.isEmpty()) {
			System.out.println("You dont have any Subscribed categories ");
		} else {
			for (int i = 0; i < subscribedCategories.size(); i++) {
				System.out.println(i + "Name= " + subscribedCategories.get(i).getName());
			}
		}
	}

	public boolean hasSubscribedCategories() {
		return subscribedCategories.size() > 0;
	}

	public List<Category> getSubscribedCategories() {
		return subscribedCategories;
	}

	public void setSubscribedCategories(List<Category> subscribedCategories) {
		this.subscribedCategories = subscribedCategories;
	}

	@Override
	public String toString() {
		return "Client [subscribedCategories=" + subscribedCategories + "]";
	}

}
