package com.example.springbasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //write this, the quotation of import~~ is made by itself
public class BasicController {

    @GetMapping("/hi") //find greetings and return
    public String niceToMeetYou(Model model) {
        model.addAttribute("username","hagom"); //you can set attributes with model
        return "greetings"; //templates/greetings.mustache -> send to browser!
    }
    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname", "dotori");
        return "goodbye";
    }
}
