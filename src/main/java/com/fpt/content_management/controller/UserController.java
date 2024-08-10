package com.fpt.content_management.controller;

import com.fpt.content_management.dto.request.MemberUpdateRequestDto;
import com.fpt.content_management.dto.response.MemberResponseDto;
import com.fpt.content_management.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class UserController {

    private final MemberService memberService;

    private static final String MESSAGE = "message";

    @GetMapping
    public ModelAndView index (RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(MESSAGE, redirectAttributes.getFlashAttributes().get(MESSAGE));
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/detail")
    public ModelAndView update () {
        ModelAndView modelAndView = new ModelAndView();
        MemberResponseDto memberResponseDto = memberService.getMember();
        modelAndView.addObject(MESSAGE, memberResponseDto);
        modelAndView.addObject("member", memberResponseDto);
        modelAndView.addObject("memberUpdate", new MemberUpdateRequestDto());
        modelAndView.setViewName("member/detail");
        return modelAndView;
    }

    @PostMapping("/detail")
    public ModelAndView update (@Valid @ModelAttribute("memberUpdate") MemberUpdateRequestDto memberUpdateRequestDto,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(MESSAGE, "Update failed");
            modelAndView.setViewName("member/detail");
            return modelAndView;
        }
        memberService.updateMember(memberUpdateRequestDto);
        redirectAttributes.addFlashAttribute(MESSAGE, "Update successful");
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
