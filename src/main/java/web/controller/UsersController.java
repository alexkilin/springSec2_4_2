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
       //User user1 = new User("Ivan", "Ivanov", "email", 30);
      // service.addUser(user1);
        List<User> users = service.getAllUsers();
        model.addAttribute("listOfUsers", users);
        return "/users/home";
    }

    @GetMapping(value = "/create")
    public String createUser(Model create_model) {
        User user = new User();
        create_model.addAttribute("user", user);
        return "/users/create";
    }

    @PostMapping(value = "/createUser")
    public String createUser(@ModelAttribute User user) {
        service.addUser(user);
        return "redirect:/users/users";
    }


    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(service.readUserById(id));
        return "redirect:/users/users";
    }

    @GetMapping(value = "/read")
    public String readAll(Model model) {
        List<User> users = service.getAllUsers();
        model.addAttribute("listOfUsers", users);
        return "/users/home";
    }

    @GetMapping(value = "/update/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = service.readUserById(id);
        model.addAttribute("user", user);
        return "users/update";
    }

    // 1 вариант
    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute User user) {
        service.updateUser(user);
        return "redirect:/users/users";
    }


//    2 вариант - получаем через @RequestParam параментры
//    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
//    public String updateUser(@RequestParam (value = "id",required = false) Long id,
//                             @RequestParam (value = "age",required = false) int age, Model model) {
//        System.out.println("id: " + id + " age: " + age);
//        User user = service.readUserById(id);
//        user.setAge(age);
//        System.out.println("new value of age: " + user.getAge());
//        service.updateUser(user);
//        List<User> users = service.getAllUsers();
//        model.addAttribute("listOfUsers", users);
//        return "users/home";


}
