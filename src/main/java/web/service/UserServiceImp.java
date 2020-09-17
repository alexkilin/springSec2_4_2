package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {
    @Autowired
    @Qualifier("userDaoJpa")
    private UserDao userDao;

    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }


    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    public User readUserById(Long id) {
        return userDao.readUserById(id);
    }
}



