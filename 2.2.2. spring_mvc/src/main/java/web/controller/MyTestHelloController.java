package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class MyTestHelloController {

    @GetMapping("/test_hello")
    public String MyTestHello (@RequestParam(value = "name",required = false) String name,
                               @RequestParam (value = "surname",required = false) String surname, Model model){
       // System.out.println("Hello, "+ name + " " + surname);

        model.addAttribute("message", "Hello, " + name + " " + surname);
        return "first/test_hello";
    }



    @GetMapping("/test_hello2")
    public String MyTestHello2 (HttpServletRequest request){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        System.out.println("Hello " + name + " " + surname);

        return "first/test_hello2";
    }


    @GetMapping("/calculator")
         public String Calculator (@RequestParam(value = "a",required = false) int a,
            @RequestParam (value = "b",required = false) int b,
            @RequestParam (value = "action",required = false) String action, Model model_calculator){

        double result=0;
        switch (action){
            case ("multiplication"):
                result = a*b;
                break;
            case ("addition"):
                result = a+b;
                break;
            case ("subtraction"):
                result = a-b;
                break;
            case ("division"):
                result = a/(double)b;
                break;
        }
        model_calculator.addAttribute("message", result);
        return "first/calculator";
         }
}
