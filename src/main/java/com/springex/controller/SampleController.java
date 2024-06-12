package com.springex.controller;

import com.springex.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello~~~");
    }
    // http://localhost:8080/ex1?name=aaa&age=20
    @GetMapping("/ex1")
    public void ex1(@RequestParam(name="name") String name, @RequestParam(name="age") String age) {
        log.info("ex1------------------------");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name="name", defaultValue = "AAA") String name,
                    @RequestParam(name="age", defaultValue="20") int age) {

        log.info("ex2--------------------");
        log.info("name: "+ name);
        log.info("age: "+ age);
    }

    @GetMapping("/ex3")
    public void ex3(@RequestParam(name="dueDate") LocalDate dueDate) {
        log.info("ex3-------------------------");
        log.info("dueDate: "+dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model) {

        log.info("-----------------------");
        model.addAttribute("message", "Hello World");
    }

    @GetMapping("/ex4_1")
    public void ex4Extra(@ModelAttribute("dto")TodoDTO todoDTO, Model model) {

        log.info(todoDTO);
    }

    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("name","ABC");
        redirectAttributes.addFlashAttribute("result","success");

        return "redirect:/ex6";
    }

    @GetMapping("/ex6")
    public void ex6() {

    }

    @GetMapping("/ex7")
    public void ex7(@RequestParam(name="p1") String p1, @RequestParam(name="p2") int p2) {
        log.info("p1--------"+p1);
        log.info("p2--------"+p2);
    }

}