package web.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;
//@Component
public class UserDaoImp implements UserDao{
    @Autowired
    @Qualifier("getSessionFactory")
    public SessionFactory sessionFactory;


    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }


    @Override
    public void deleteUser (User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public User readUserById (Long id) {
        User result;
        result = sessionFactory.openSession().get(User.class,id);
        return result;
    }

    @Override
    public User getUserByUserName(String username) {
//        if (!userMap.containsKey(name)) {
//            return null;
//        }
//
//        return userMap.get(name);
        return null;
    }

}