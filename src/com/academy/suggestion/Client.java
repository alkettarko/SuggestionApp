package com.academy.suggestion;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {

	private List<Post> likedPosts = new ArrayList<Post>();
	private List<String> messages = new ArrayList<String>();

	public Client(String username, String password) {
		super(username, password);
	}

	public Client(String name, String username, String password, String email, Gender gender, int age) {
		super(name, username, password, email, gender, age);
	}

	public void likePost(Post post) {
		likedPosts.add(post);

	}

	public void printMessages() {
		if (messages.isEmpty()) {
			System.out.println("You dont have any messages ");
		} else {
			for (int i = 0; i < messages.size(); i++) {
				System.out.println(messages);
			}
		}
	}

	public void addMessage(String message) {
		messages.add(message);
	}

	public void printLikedPost() {
		if (likedPosts.isEmpty()) {
			System.out.println("You dont have any liked posts ");
		} else {
			for (int i = 0; i < likedPosts.size(); i++) {
				System.out.println("Id = " + i + " Title = " + likedPosts.get(i).getTitle());
			}
		}
	}

	public List<Post> getLikedPosts() {
		return likedPosts;
	}

	public void setLikedPosts(List<Post> likedPosts) {
		this.likedPosts = likedPosts;
	}

	@Override
	public String toString() {
		return "Client [likedPosts=" + likedPosts + ", messages=" + messages + "]";
	}

}
