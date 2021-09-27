package de.adesso.techtalk.http.header.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("boot")
    public String boot() {
        return "boot";
    }
}
