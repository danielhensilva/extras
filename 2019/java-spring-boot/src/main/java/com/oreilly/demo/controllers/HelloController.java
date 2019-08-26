package com.oreilly.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(
            Model model,
            @RequestParam(defaultValue = "world", required = false) String name) {
        model.addAttribute("user", name);
        return "hello";
    }

}
