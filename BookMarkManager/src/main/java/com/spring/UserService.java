package com.spring;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private BookMarkRepository bookMarkRepository;
  
  public User saveUser(User user) {
    return userRepository.save(user);
  }
  
  public void deleteUser(String userId) {
    userRepository.deleteById(userId);
  }
  
  public User updateUser(User user) {
    return userRepository.save(user);
  }
  
  public List<User> getUsers() {
    return userRepository.findAll();
  }
  
  public User getUserById(String userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      return optionalUser.get();
    } else {
      return null;
    }
  }
  
  public BookMark saveBookmark(BookMark bookMark) {
    return bookMarkRepository.save(bookMark);
  }
  
  public void deleteBookmark(int bookmarkId) {
    bookMarkRepository.deleteById(bookmarkId);
  }
  
  public BookMark updateBookmark(BookMark bookMark) {
    return bookMarkRepository.save(bookMark);
  }
  
  public List<BookMark> getBookmarksByUser(String userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      return user.getBookmarks();
    } else {
      return null;
    }
  }
}
