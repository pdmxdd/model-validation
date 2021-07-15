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
@RequestMapping(value = "user")
public class UserController {
    @GetMapping
    public String getUserLoginForm() {
        return "user/login";
    }

    @PostMapping
    public String postUserLoginForm(@ModelAttribute @Valid User user, Errors errors, Model model) {
        // check if any model validation errors occured
        if(errors.hasErrors()) {
            // if they did create an arrayList of errorMessages
            ArrayList<String> errorMessages = new ArrayList<>();
            for(ObjectError error : errors.getAllErrors()) {
                // loop through all the error messages and add them to the arrayList
                errorMessages.add(error.getDefaultMessage());
            }
            // add the arrayList errors to the model so they can be built correctly by thymeleaf
            model.addAttribute("errors", errorMessages);
            return "user/login";
        }
        model.addAttribute("user", user);
        return "user/loginSuccess";
    }
}
