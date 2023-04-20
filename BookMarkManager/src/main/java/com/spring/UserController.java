package com.spring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {

  @Autowired
  private UserService userService;
  
  @PostMapping("/register")
  public ResponseEntity<User> registerUser(@RequestBody User user) {
    User savedUser = userService.saveUser(user);
    return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
  }
  
  @PostMapping("/login")
  public ResponseEntity<User> loginUser(@RequestBody User user) {
    // Authenticate user with Spring Security and return user object if authenticated
    return null;
  }
  
  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable String userId) {
    User user = userService.getUserById(userId);
    if (user != null) {
      return new ResponseEntity<User>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
  }
  
  @GetMapping("/{userId}/bookmarks")
  public ResponseEntity<List<BookMark>> getBookmarksByUser(@PathVariable String userId) {
    List<BookMark> bookmarks = userService.getBookmarksByUser(userId);
    if (bookmarks != null) {
      return new ResponseEntity<List<BookMark>>(bookmarks, HttpStatus.OK);
    } else {
      return new ResponseEntity<List<BookMark>>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PostMapping("/{userId}/bookmarks")
  public ResponseEntity<BookMark> addBookmark(@PathVariable String userId, @RequestBody BookMark bookMark) {
    User user = userService.getUserById(userId);
    if (user != null) {
      bookMark.setUser(user);
      BookMark savedBookMark = userService.saveBookmark(bookMark);
      return new ResponseEntity<BookMark>(savedBookMark, HttpStatus.OK);
    } else {
      return new ResponseEntity<BookMark>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PutMapping("/{userId}/bookmarks/{bookmarkId}")
  public ResponseEntity<BookMark> updateBookmark(@PathVariable String userId, @PathVariable int bookmarkId, @RequestBody BookMark bookMark) {
    User user = userService.getUserById(userId);
    if (user != null) {
        Optional<BookMark> optionalBookMark = user.getBookmarks().stream().filter(b -> b.getId() == bookmarkId).findFirst();
        if (optionalBookMark.isPresent()) {
          BookMark existingBookMark = optionalBookMark.get();
          existingBookMark.setTitle(bookMark.getTitle());
          existingBookMark.setUrl(bookMark.getUrl());
          existingBookMark.setDescription(bookMark.getDescription());
          BookMark updatedBookMark = userService.updateBookmark(existingBookMark);
          return new ResponseEntity<BookMark>(updatedBookMark, HttpStatus.OK);
        } else {
          return new ResponseEntity<BookMark>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<BookMark>(HttpStatus.NOT_FOUND);
      }
    }
    
    @DeleteMapping("/{userId}/bookmarks/{bookmarkId}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable String userId, @PathVariable int bookmarkId) {
      User user = userService.getUserById(userId);
      if (user != null) {
        Optional<BookMark> optionalBookMark = user.getBookmarks().stream().filter(b -> b.getId() == bookmarkId).findFirst();
        if (optionalBookMark.isPresent()) {
          userService.deleteBookmark(bookmarkId);
          return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
          return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
      }
    }
  }

