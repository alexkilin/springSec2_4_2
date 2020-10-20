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

    private UserDao userDao;

    @Autowired
    public UserServiceImp(@Qualifier("userDaoJpa") UserDao userDao) {
        this.userDao=userDao;
    }

    @Transactional
    public boolean addUser(User user) {
        userDao.addUser(user);
        return true;
    }

    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public User getUserById(Long id) {
        return userDao.readUserById(id);
    }

    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Transactional
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }
}



