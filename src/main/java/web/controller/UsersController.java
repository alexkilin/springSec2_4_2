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

        User user1 = new User("Ivan", "Ivanov", "email", 30);
        service.add(user1);
        List<User> users = service.listUsers();
        model.addAttribute("listOfUsers", users);
        return "/users/home";
    }

    @GetMapping(value = "/create")
    public String createUser(Model create_model) {
        User user = new User();
        create_model.addAttribute("user", user);
        return "/users/create";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user, Model model) {
        service.add(user);
        List<User> users = service.listUsers();
        model.addAttribute("listOfUsers", users);
        return "users/home";
    }


    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        String username = service.readUserById(id).getFirstName() +
                service.readUserById(id).getLastName();
        model.addAttribute("username", username);
        service.deleteUser(service.readUserById(id));
        return "/users/delete";
    }

    @GetMapping(value = "/read")
    public String readAll(Model model) {
        List<User> users = service.listUsers();
        model.addAttribute("listOfUsers", users);
        return "/users/home";
    }

    @GetMapping(value = "/update/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = service.readUserById(id);
        System.out.println("работа /update/{id} ");
        model.addAttribute("user", user);
        return "/users/update";
    }

    // 1 вариант
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute User user, Model model) {
        service.update(user);
        List<User> users = service.listUsers();
        model.addAttribute("listOfUsers", users);
        return "users/home";
    }


//    2 вариант - получаем через @RequestParam параментры
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String updateUser(@RequestParam (value = "id",required = false) Long id,
//                             @RequestParam (value = "age",required = false) int age, Model model) {
//        System.out.println("id: " + id + " age: " + age);
//        User user = service.readUserById(id);
//        user.setAge(age);
//        System.out.println("new value of age: " + user.getAge());
//        service.update(user);
//        List<User> users = service.listUsers();
//        model.addAttribute("listOfUsers", users);
//        return "users/home";


}
