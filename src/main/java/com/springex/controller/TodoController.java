package com.springex.controller;
import com.springex.dto.PageRequestDTO;
import com.springex.dto.TodoDTO;
import com.springex.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

     private final TodoService todoService;

    @RequestMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){

        log.info(pageRequestDTO);

        // 검증 결과에 오류가 있다면, 페이지 요청이 유효하지 않는다면
        // PageRequestDTO를 생성해서 초기화
        // 기본 페이지 설정으로 다시 시도하거나 오류를 처리.
        if(bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register~~~~~");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes ) {
        log.info("POST todo register~~~~~");

        if(bindingResult.hasErrors()) {// 에러가 났을 경우에만 거침.
            log.info("has errors!!!!!!!");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/todo/register";// 검증에 문제가 있다면 다시 입력화면으로 리다이렉트.
        }

        log.info(todoDTO);
        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(@RequestParam(name ="tno") Long tno, PageRequestDTO pageRequestDTO,Model model) {

        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto",todoDTO);

    }
    @PostMapping("/remove")
    public String remove(@RequestParam(name ="tno") Long tno, PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {

        log.info("-------------remove------------");
        log.info("tno: " + tno);
        todoService.remove(tno);

        redirectAttributes.addAttribute("page",1);
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "redirect:/todo/list";
    }
    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO) {
        if(bindingResult.hasErrors()) {
            log.info("has errors~~~~~~~~~~~~~~");
            redirectAttributes.addFlashAttribute("errors",
                    bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());

        return "redirect:/todo/list";
    }
}