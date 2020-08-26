package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.List;

@Controller
public class CarsController {

    private CarService service;

    @Autowired
    public CarsController(CarService service) {
        this.service = service;
    }

    @Transactional
    @GetMapping(value = "/cars")
    public String listAll(Model model) {
        List<Car> cars = service.listThreeCars();
        model.addAttribute("message", "will be cars");
        return "cars";
    }
}




