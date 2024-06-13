package com.springex.controller;
import com.springex.dto.TodoDTO;
import com.springex.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")  // localhost:8080/todo
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("/list")  // localhost:8080/todo/list
    public void list(Model model) {
        log.info("todo list...............");

        model.addAttribute("dtoList", todoService.getAll());
    }

    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register~~~~~");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes ) {
        log.info("POST todo register~~~~~");

        if(bindingResult.hasErrors()) {  // 에러가 났을 경우에만 거침
            log.info("has errors!!!!!!!");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/todo/register";  // 검증에 문제가 있다면 다시 입력화면으로 리다이렉트
        }

        log.info(todoDTO);
        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam(name ="tno") Long tno, Model model) {

        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto",todoDTO);

    }

    @PostMapping("/remove")
    public String remove(@RequestParam(name ="tno") Long tno,
                         RedirectAttributes redirectAttributes) {

        log.info("-------------remove------------");
        log.info("tno: " + tno);
        todoService.remove(tno);

        return "redirect:/todo/list";
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("has errors~~~~~~~~~~~~~~");
            redirectAttributes.addFlashAttribute("errors",
                    bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);

        return "redirect:/todo/list";
    }


}