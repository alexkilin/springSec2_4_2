package web.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Car;

import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class CarDaoImp implements CarDao{
    @Autowired
    public SessionFactory sessionFactory;


    public void add(Car car) {
        //System.out.println("test save OK");
        sessionFactory.getCurrentSession().save(car);
    }

    @SuppressWarnings("unchecked")
    public List<Car> listThreeCars() {
       TypedQuery <Car> query = sessionFactory.getCurrentSession().createQuery ("from Car");
       return query.getResultList();
    }


}