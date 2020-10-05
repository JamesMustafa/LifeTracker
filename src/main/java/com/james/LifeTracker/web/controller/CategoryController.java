package com.james.LifeTracker.web.controller;

import com.james.LifeTracker.dto.binding.CategoryInputBindingModel;
import com.james.LifeTracker.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

//TODO: It is much more efficient if I do everything with modals and dynamic displays,
// but the thing is that I do not have any front-end framework to make this,
// and in this case the main point is the back-end work,
// so I will make it look properly on the back-end side, instead of the front-end !!!

@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String getCategories(Principal principal, Model model) {
        model.addAttribute("allCategories", this.categoryService.findCategoriesByUser(principal.getName()));
        return "category/index";
    }

    @GetMapping("/create")
    public String getCreateCategory(Model model) {
        model.addAttribute("categoryModel", new CategoryInputBindingModel());
        return "category/categoryCreate";
    }

    @PostMapping("/create")
    public String createCategory(@Valid CategoryInputBindingModel categoryModel,
                                 @ModelAttribute("userName") String userName,
                                 BindingResult bindingResult,
                                 Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryModel", categoryModel);
            return "category/index";
        }

        this.categoryService.createCategory(categoryModel, userName);

        return "redirect:/categories";
    }
}
