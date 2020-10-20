package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.UserService;
import java.security.Principal;

@Controller
public class UserController {

    public UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service=service;
    }

    @GetMapping(value = "/user")
    public String userInfo(Model model, Principal principal) {
        web.model.User currentUser = service.getUserByUserName(principal.getName());
        model.addAttribute("user", currentUser);
        return "users/userInfo";
    }
}