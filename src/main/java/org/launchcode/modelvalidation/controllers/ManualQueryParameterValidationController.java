package org.launchcode.modelvalidation.controllers;

import org.launchcode.modelvalidation.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/manual/query")
public class ManualQueryParameterValidationController {

    @GetMapping
    public String getUserLoginForm() {
        return "manual/query/login";
    }

    @PostMapping
    public String postUserLoginForm(@RequestParam String email, @RequestParam String password, Model model) {
        // access the raw query string (email) and write the logic to check it against our expectations:
        if(!email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
            // not a valid email address
            // add an error to our model so we can print out the validation error:
            model.addAttribute("error", "Email address is not valid");
            // return our login template again
            return "manual/query/login";
        }

        // access the raw query string (password) and write the logic to check it against our expectations:
        if(!(password.length() > 5)) {
            // password not long enough
            // add an error to our model so we can print out the validation error:
            model.addAttribute("error", "Password is not long enough. Must be at least 6 characters long");
            return "manual/query/login";
        }
        User exampleUser = new User(email, password);
        model.addAttribute("user", exampleUser);
        return "manual/query/loginSuccess";
    }
}
