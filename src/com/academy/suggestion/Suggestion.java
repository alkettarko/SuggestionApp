package com.academy.suggestion;

public class Suggestion {
	
	private Category category;
	private Client client;
	
	public Suggestion(Category category, Client client, Object object, int i, int i2) {
		this.category = category;
		this.client = client;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
