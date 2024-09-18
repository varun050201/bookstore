package com.storewithui.computerstore.controller;

import com.storewithui.computerstore.entity.User;
import com.storewithui.computerstore.repository.UserRepository;
import com.storewithui.computerstore.service.EmailService;
import com.storewithui.computerstore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView handleLogin(@RequestParam String username, @RequestParam String password) {
        if (loginService.authenticate(username, password)) {
            return new ModelAndView("redirect:/");
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationPage() {
        return new ModelAndView("registerlogin");
    }

    @PostMapping("/register")
    public ModelAndView handleRegistration(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            ModelAndView modelAndView = new ModelAndView("registerlogin");
            modelAndView.addObject("error", "Passwords do not match");
            return modelAndView;
        }

        if (userRepository.findByUsername(username) != null) {
            ModelAndView modelAndView = new ModelAndView("registerlogin");
            modelAndView.addObject("error", "Username already exists");
            return modelAndView;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // In a real application, hash the password
        user.setEmail(email);
        String verificationToken = UUID.randomUUID().toString(); // Generate a unique token
        user.setVerificationToken(verificationToken);
        userRepository.save(user);

        emailService.sendVerificationEmail(email, verificationToken);

        ModelAndView modelAndView = new ModelAndView("registerlogin");
        modelAndView.addObject("message", "Registration successful! Please check your email to verify your account.");
        return modelAndView;
    }

    @GetMapping("/verify-email")
    public ModelAndView verifyEmail(@RequestParam String token) {
        User user = userRepository.findByVerificationToken(token);
        if (user != null) {
            user.setVerified(true); // Set user as verified
            user.setVerificationToken(null); // Remove the token
            userRepository.save(user);
            return new ModelAndView("redirect:/login?message=Email verified successfully");
        } else {
            return new ModelAndView("redirect:/login?error=Invalid verification token");
        }
    }
}
