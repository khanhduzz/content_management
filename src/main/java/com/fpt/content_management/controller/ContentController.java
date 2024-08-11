package com.fpt.content_management.controller;

import com.fpt.content_management.dto.filter.ContentFilter;
import com.fpt.content_management.dto.request.ContentCreateDto;
import com.fpt.content_management.dto.request.ContentUpdateDto;
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
    private static final String CONTENT = "content";
    private static final String ADD_CONTENT = "member/add-content";
    private static final String REDIRECT_CONTENT = "redirect:/contents";
    private static final String ACTIVE_TAB = "activeTab";

    @GetMapping()
    public ModelAndView getContents(@ModelAttribute(MESSAGE) String message,
                                    @Valid @ModelAttribute("contentFilter") ContentFilter contentFilter) {
        ModelAndView modelAndView = new ModelAndView("member/contents");
        modelAndView.addObject(MESSAGE, message);
        Page<ContentResponseDto> contents = contentService.getContents(contentFilter);
        modelAndView.addObject("contents", contents);
        modelAndView.addObject("contentFilter", new ContentFilter());
        modelAndView.addObject(CONTENT, new ContentCreateDto());
        modelAndView.addObject(ACTIVE_TAB, "contents");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addContent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("addContent", new ContentCreateDto());
        modelAndView.setViewName(ADD_CONTENT);
        modelAndView.addObject(ACTIVE_TAB, "add");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getContent(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        ContentResponseDto contentResponseDto = contentService.getContent(id);
        modelAndView.addObject(CONTENT, contentResponseDto);
        modelAndView.addObject("contentUpdate", new ContentUpdateDto());
        modelAndView.setViewName("member/edit-content");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addContent(@ModelAttribute("addContent") @Valid ContentCreateDto addContent,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADD_CONTENT);
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        contentService.addContent(addContent);
        redirectAttributes.addFlashAttribute(MESSAGE, "Content added successfully");
        modelAndView.setViewName(REDIRECT_CONTENT);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView updateContent(@PathVariable("id") Long id,
                                      @ModelAttribute("contentUpdate")
                                      @Valid ContentUpdateDto contentUpdateDto,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("member/edit-content");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject(CONTENT, contentService.getContent(id));
            return modelAndView;
        }
        contentService.updateContent(id, contentUpdateDto);
        redirectAttributes.addFlashAttribute(MESSAGE, "Content updated successfully");
        modelAndView.setViewName(REDIRECT_CONTENT);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteContent(@PathVariable Long id,
                                      RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        contentService.deleteContent(id);
        redirectAttributes.addFlashAttribute(MESSAGE, "Content deleted successfully");
        modelAndView.setViewName(REDIRECT_CONTENT);
        return modelAndView;
    }
}
