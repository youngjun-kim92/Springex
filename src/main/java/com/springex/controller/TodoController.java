package com.springex.controller;
import com.springex.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/todo")  // localhost:8080/todo
@Log4j2
public class TodoController {

    @RequestMapping("/list")  // localhost:8080/todo/list
    public void list() {
        log.info("todo list...............");
    }

    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register~~~~~");
    }

    @PostMapping("/register")
    public void registerPost(TodoDTO todoDTO) {
        log.info("POST todo register~~~~~");
        log.info(todoDTO);
    }

}