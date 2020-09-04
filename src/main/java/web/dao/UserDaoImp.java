package web.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class UserDaoImp implements UserDao{
    @Autowired
    public SessionFactory sessionFactory;


    public void add (User user) {
        sessionFactory.getCurrentSession().save(user);
    }


    @Override
    public void deleteUser (User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void update (User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public User readUserById (Long id) {
        User result;
        result = sessionFactory.openSession().get(User.class,id);
        return result;
    }
}