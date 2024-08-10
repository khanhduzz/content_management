package com.fpt.content_management.controller;

import com.fpt.content_management.dto.filter.ContentFilter;
import com.fpt.content_management.dto.request.ContentCreateDto;
import com.fpt.content_management.dto.response.ContentResponseDto;
import com.fpt.content_management.service.ContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    private static final String MESSAGE = "message";
    private static final String ADD_CONTENT = "member/add-content";

    @GetMapping()
    public ModelAndView getContents (@ModelAttribute(MESSAGE) String message,
                                     @Valid @ModelAttribute("contentFilter") ContentFilter contentFilter) {
        ModelAndView modelAndView = new ModelAndView("member/contents");
        modelAndView.addObject(MESSAGE, message);
        Page<ContentResponseDto> contents = contentService.getContents(contentFilter);
        modelAndView.addObject("contents", contents);
        modelAndView.addObject("contentFilter", new ContentFilter());
        modelAndView.addObject("content", new ContentCreateDto());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addContent () {
        ModelAndView modelAndView = new ModelAndView(ADD_CONTENT);
        modelAndView.addObject("addContent", new ContentCreateDto());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getContent (@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        ContentResponseDto contentResponseDto = contentService.getContent(id);
        modelAndView.addObject("content", contentResponseDto);
        modelAndView.addObject("contentUpdate", new ContentCreateDto());
        modelAndView.setViewName("member/edit-content");
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView editContent (@PathVariable("id") Long id,
                                     @Valid @ModelAttribute("contentUpdate") ContentCreateDto contentCreateDto,
                                     BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("member/edit-content");
        }
        ContentResponseDto content = contentService.updateContent(id, contentCreateDto);
        modelAndView.addObject("content", content);
        modelAndView.setViewName("redirect:/contents");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addContent(@ModelAttribute("addContent") @Valid ContentCreateDto addContent,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADD_CONTENT);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(MESSAGE, "Error when creating content");
            modelAndView.setViewName(ADD_CONTENT);
        }
        contentService.addContent(addContent);
        redirectAttributes.addFlashAttribute(MESSAGE, "Content added successfully");
        modelAndView.setViewName("redirect:/contents");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteContent (@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        contentService.deleteContent(id);
        modelAndView.setViewName("redirect:/contents");
        return modelAndView;
    }
}
