package com.spring;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookmarkuser")
public class User {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int userid;
	String name;
	
	@Id
	String username;
	String password;
	
	boolean isadmin;
	@OneToMany(mappedBy="user")
	List<BookMark> bookmarks;
	
	public User() {
		
	}
	
	public User(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
	
	}



	public int getUserid() {
		return userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<BookMark> getBookmarks() {
		return bookmarks;
	}
	
	public void addBookMark(BookMark bookMark) {
		
		this.bookmarks.add(bookMark);
		
	}

	public void setBookmarks(List<BookMark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public boolean isIsadmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}
	
	

}
