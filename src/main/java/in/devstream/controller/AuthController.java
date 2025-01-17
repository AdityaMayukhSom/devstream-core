package in.devstream.controller;

import in.devstream.openapi.api.AuthApi;
import in.devstream.openapi.model.*;
import in.devstream.service.AuthService;
import in.devstream.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController implements AuthApi {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<LoginResponse> authenticateUser(@Valid LoginRequest loginRequest) {
        return null;
    }

    @Override
    public ResponseEntity<RefreshTokenResponse> refreshAuthToken(@Valid RefreshTokenRequest refreshTokenRequest) {
        return null;
    }

    @Override
    public ResponseEntity<RegisterResponse> registerUser(@Valid RegisterRequest registerRequest) {

//        if (registerRequestDto != null && !userService.doesUserExistsByEmail(userDto.getEmail())) {
//            User user = UserMapper.MAPPER.toUser(userDto);
//            authService.registerUser(userDto);
//        } else {
//            bindingResult.rejectValue("email", "error.user", (userDto == null) ? "could not parse user object" : "an user exists with the given email");
//        }
//
//        return modelAndView;
        return null;
    }

    @Override
    public ResponseEntity<Void> resetPassword(@Valid ForgotPasswordRequest forgotPasswordRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> resetUserPassword(@Valid ResetPasswordRequest resetPasswordRequest) {
        return null;
    }


//    @GetMapping("/signup")
//    public ModelAndView signup() {
//        ModelAndView modelAndView = new ModelAndView();
//        UserDto userDto = new UserDto();
//        modelAndView.addObject(userDto);
//        modelAndView.setViewName("signup");
//        return modelAndView;
//    }

//    @GetMapping("/dashboard")
//    public ModelAndView dashboard() {
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null) {
//            modelAndView.addObject("message", "could not find an authenticated user, please login");
//            return modelAndView;
//        }
//
//        String userEmail = authentication.getName();
//
//        try {
//            UserDto userDto = userService.findUserByEmail(userEmail);
//            modelAndView.addObject("currentUser", userDto);
//            modelAndView.addObject("fullName", "welcome " + userDto.getFullname());
//            modelAndView.addObject("message", "content only available for users with admin priviledges");
//            modelAndView.setViewName("dashboard");
//        } catch (UserNotFoundException e) {
//            modelAndView.addObject("message", "could not find an user with existing email, please login with valid email");
//        }
//
//        return modelAndView;
//    }
//
//    @GetMapping(value = {"/", "/home"})
//    public ModelAndView home() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("home");
//        return modelAndView;
//    }





}
