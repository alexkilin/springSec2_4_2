package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;
@Service
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;


    public void add(User user) {
        userDao.add(user);
    }


    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }


    public List<User> listUsers() {
        return userDao.listUsers();
    }


    public void update(User user) {
        userDao.update(user);
    }


    public User readUserById(Long id) {
        return userDao.readUserById(id);
    }

}



