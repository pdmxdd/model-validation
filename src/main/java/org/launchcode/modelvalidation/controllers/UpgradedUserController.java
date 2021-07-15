package org.launchcode.modelvalidation.controllers;

import org.launchcode.modelvalidation.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/user/upgraded")
public class UpgradedUserController {
    @GetMapping
    public String getUserLoginForm(Model model) {
        // this is how we bring our user fields directly to Thymeleaf! ${user.email} ${user.password}
        model.addAttribute(new User());
        return "upgradedUser/login";
    }

    @PostMapping
    public String postUserLoginForm(@ModelAttribute @Valid User user, Errors errors, Model model) {
        // check if any model validation errors occured
        if(errors.hasErrors()) {
            return "upgradedUser/login";
        }
        model.addAttribute("user", user);
        return "upgradedUser/loginSuccess";
    }
}
