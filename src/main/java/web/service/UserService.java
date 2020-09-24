package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);
    void deleteUser(User user);
    List<User> getAllUsers();
    void updateUser(User user);
    User getUserByUserName (String userName);
    User getUserById(Long id);


}
