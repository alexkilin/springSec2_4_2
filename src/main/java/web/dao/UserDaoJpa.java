package web.dao;
import org.springframework.stereotype.Component;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoJpa implements UserDao {

@PersistenceContext(unitName = "entityManagerFactory")
private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(User user) {
        User currentUser = readUserById(user.getId());
        entityManager.remove(currentUser);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User readUserById(Long id) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public User getUserByUserName(String username) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.username = :name", User.class);
        q.setParameter("name", username);
        return q.getResultList().stream().findAny().orElse(null);
    }


}
