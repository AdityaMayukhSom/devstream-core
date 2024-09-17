package in.devstream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import in.devstream.dto.UserDto;
import in.devstream.exception.UserNotFoundException;
import in.devstream.service.AuthService;
import in.devstream.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        UserDto userDto = new UserDto();
        modelAndView.addObject(userDto);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView createUser(UserDto userDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (!userService.doesUserExistsByEmail(userDto.getEmail())) {
            authService.registerUser(userDto);
            modelAndView.addObject("successMessage", "user has been created successfully");
            modelAndView.addObject("user", new UserDto());
            modelAndView.setViewName("login");
        } else {
            bindingResult.rejectValue("email", "error.user", "an user exists with the given email");
        }

        return modelAndView;

    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            modelAndView.addObject("message", "could not find an authenticated user, please login");
            return modelAndView;
        }

        String userEmail = authentication.getName();

        try {
            UserDto userDto = userService.findUserByEmail(userEmail);
            modelAndView.addObject("currentUser", userDto);
            modelAndView.addObject("fullName", "welcome " + userDto.getFullname());
            modelAndView.addObject("message", "content only available for users with admin priviledges");
            modelAndView.setViewName("dashboard");
        } catch (UserNotFoundException e) {
            modelAndView.addObject("message", "could not find an user with existing email, please login with valid email");
        }

        return modelAndView;
    }

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
