package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.config.DatabaseConfig;
import web.model.Car;
import web.service.CarService;
import web.service.CarServiceImp;

import java.util.List;

@Controller
public class CarsController {

    public CarService service;

    @Autowired
    public CarsController(CarService service) {
        this.service = service;
    }


   // @Transactional
    @GetMapping(value = "/cars")
    public String listAll(Model model) {
        System.out.println("test OK");

        Car car1 = new Car ("BMW",6,666);
        Car car2 = new Car ("Mersedes",600,777);
        Car car3 = new Car ("Hammer",100,1111);

        service.add(car1);
        service.add(car2);
        service.add(car3);

        List<Car> cars = service.listThreeCars();
        for (Car car : cars) {
            System.out.println("Id = "+car.getId());
            System.out.println("Name = "+car.getName());
            System.out.println("Series = "+car.getSeries());
            System.out.println("Number = "+car.getNumber());
            System.out.println();
        }
        model.addAttribute("message", "will be cars");
        return "cars";
    }
}




