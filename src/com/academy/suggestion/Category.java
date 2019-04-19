package com.academy.suggestion;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private String name;
	private String description;
	private List<Category> subCategories = new ArrayList<Category>();
	private List<Post> posts = new ArrayList<Post>();

	public Category(String name) {
		this.name = name;
	}

	public void addSubCategory(Category category) {
		subCategories.add(category);
	}

	public void printPost() {
		if (posts.isEmpty()) {
			System.out.println("The current Category has no posts");
		} else {
			for (int i = 0; i < posts.size(); i++) {
				System.out.println("Id = " + i + " Name= " + posts.get(i).getTitle());
			}
		}
	}

	public void printSubCategeory() {
		if (subCategories.isEmpty()) {
			System.out.println("The current Category has not subCategories");
		} else {
			for (int i = 0; i < subCategories.size(); i++) {
				System.out.println("Id = " + i + " Name= " + subCategories.get(i).getName());
			}
		}
	}

	public void addPost(Post post) {
		posts.add(post);
	}

	public List<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", description=" + description + ", subCategories=" + subCategories
				+ ", posts=" + posts + "]";
	}

}
