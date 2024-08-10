package com.fpt.content_management.controller;

import com.fpt.content_management.dto.request.RegisterRequestDto;
import com.fpt.content_management.exception.MemberAlreadyExistException;
import com.fpt.content_management.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final String MESSAGE = "message";

    private final AuthService authService;

    @GetMapping("/login")
    public ModelAndView login (@ModelAttribute(MESSAGE) String message) {
        ModelAndView modelAndView = new ModelAndView("member/login");
        modelAndView.addObject(MESSAGE, message);
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/register")
    public ModelAndView register (@ModelAttribute("error") String message) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(message);
        modelAndView.addObject(MESSAGE, message);
        modelAndView.setViewName("member/register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register (@ModelAttribute @Valid RegisterRequestDto registerRequestDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        authService.register(registerRequestDto);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            modelAndView.setViewName("redirect:/auth/register");
            return modelAndView;
        }
        redirectAttributes.addFlashAttribute(MESSAGE, "Register success");
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @ExceptionHandler(MemberAlreadyExistException.class)
    public ModelAndView handleException(Exception e, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        modelAndView.setViewName("redirect:/auth/register");
        return modelAndView;
    }
}
