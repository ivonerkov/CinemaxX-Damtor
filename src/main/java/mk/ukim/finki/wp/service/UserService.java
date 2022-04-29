package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    List<User> listAll();
    User create(String username, String password, String role);
}
