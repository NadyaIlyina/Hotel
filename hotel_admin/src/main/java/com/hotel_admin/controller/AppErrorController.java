package com.hotel_admin.controller;

import com.hotel_admin.model.CategoryRooms;
import com.hotel_admin.service.CategoriesService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class AppErrorController implements ErrorController {
    private CategoriesService categoriesService;

    public AppErrorController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @RequestMapping("/error")
    public String handleError(Model model) {
        Collection<CategoryRooms> categories = categoriesService.findAll();
        model.addAttribute("categories", categories);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
