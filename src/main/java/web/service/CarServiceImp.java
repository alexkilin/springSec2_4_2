package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.CarDao;
import web.dao.CarDaoImp;
import web.model.Car;

import java.util.List;
@Service
public class CarServiceImp implements CarService {
    @Autowired
    private CarDao carDao;

    @Transactional
    public void add(Car car){
        carDao.add(car);
    }

    @Transactional(readOnly = true)
    public List<Car> listThreeCars(){
     return carDao.listThreeCars();
    }
}



