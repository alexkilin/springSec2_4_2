package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }


    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    public User readUserById(Long id) {
        return userDao.readUserById(id);
    }
}



