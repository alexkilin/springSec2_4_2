package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import java.util.List;


@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    public UserService service;

    @GetMapping(value = "/users")
    public String listAll(Model model) {

        User user1 = new User("Ivan", "Ivanov","email", 30);
        service.add(user1);
        List<User> users = service.listUsers();
        model.addAttribute("listOfUsers", users);
        return "/users/home";
    }
    @GetMapping(value = "/create")
    public String createUser (Model create_model) {
        User user = new User();
        create_model.addAttribute("user",user);
        return "/users/create";
    }
    @GetMapping(value = "/delete")
    public String deleteUser (@PathVariable ("id") Long id , Model model) {
        User user = service.readUserById(id);
        model.addAttribute("user",user);
        System.out.println("delete id = " + id);
        return "/users/delete";
    }
    @GetMapping(value = "/read")
    public String readUser (@RequestParam(value = "id", required = false) Long id , Model read_model) {
        User user = service.readUserById(id);
        read_model.addAttribute("user",user);
        return "/users/read";
    }
    @GetMapping(value = "/update/{id}")
    public String editUser(@PathVariable ("id") Long id){
        User user = service.readUserById(id);
        System.out.println("работа /update/{id} ");
        //model.addAttribute("user", user);
        return "/users/update";
    }
    @RequestMapping(name = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {
        service.update(user);
        return "users/home";
    }



}


