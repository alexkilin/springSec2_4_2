package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class UsersController {
    @Autowired
    public UserService service;

    @GetMapping(value = "/users")
    public String listAll(Model model) {
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
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUserById(id);
        return "redirect:/admin/users";
    }

//    @GetMapping(value = "/update/{id}")
//    public String editUser(@PathVariable("id") Long id, Model model) {
//        User user = service.getUserById(id);
//        model.addAttribute("user", user);
//        return "users/update";
//    }

    @GetMapping(value = "/updateuser")
    public String editUser(@RequestParam(value = "id", required = false) Long id,
                           @RequestParam(value = "firstName", required = false) String firstName,
                           @RequestParam(value = "lastName", required = false) String lastName,
                           @RequestParam(value = "age", required = false) int age,
                           @RequestParam(value = "e-mail", required = false) String email,
                           @RequestParam(value = "username", required = false) String username,
                           @RequestParam(value = "password", required = false) String password,
                           @RequestParam(value = "roles", required = false) Set<Role> roles, Model model) {

        User user = new User(id, firstName, lastName, age, email, username, password, roles);
        model.addAttribute("user", user);

        return "users/update";
    }


    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute User user) {
        long n = 1;
        if (user.isAdminRole_DTO()) {
            user.getRoles().add(new Role(n,"ROLE_ADMIN"));
        }
        if (user.isUserRole_DTO()) {
            user.getRoles().add(new Role((n)+1L,"ROLE_USER"));
        }
        service.updateUser(user);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "/users/hello";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "/users/login";
    }


    @GetMapping(value = "/user/{id}")
    public String userInfo(@PathVariable("id") Long id, Model model) {
        UserDetails user = service.getUserById(id);
        model.addAttribute("user", user);
        return "users/userInfo";
    }

}

