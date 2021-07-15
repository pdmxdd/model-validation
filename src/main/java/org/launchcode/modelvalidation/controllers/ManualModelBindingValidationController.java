package org.launchcode.modelvalidation.controllers;

import org.launchcode.modelvalidation.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/manual/model")
public class ManualModelBindingValidationController {
    @GetMapping
    public String getUserLoginForm() {
        return "manual/model/login";
    }

    @PostMapping
    public String postUserLoginForm(@ModelAttribute User user, Model model) {
        // access the raw query string (email) and write the logic to check it against our expectations:
        if(!user.getEmail().matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
            // not a valid email address
            // add an error to our model so we can print out the validation error:
            model.addAttribute("error", "Email address is not valid");
            // return our login template again
            return "manual/model/login";
        }

        // access the raw query string (password) and write the logic to check it against our expectations:
        if(!(user.getPassword().length() > 5)) {
            // password not long enough
            // add an error to our model so we can print out the validation error:
            model.addAttribute("error", "Password is not long enough. Must be at least 6 characters long");
            return "manual/model/login";
        }
        return "manual/model/loginSuccess";
    }
}
