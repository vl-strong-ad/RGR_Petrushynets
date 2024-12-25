package com.tpp.rgr.controllers;

import com.tpp.rgr.models.User;
import com.tpp.rgr.repository.UserRepository;

import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Відображення сторінки входу.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Відображення форми реєстрації.
     */
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    /**
     * Обробка помилки входу.
     */
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login-error";
    }

    /**
     * Обробка реєстрації нового користувача.
     *
     * @param username ім'я користувача.
     * @param password пароль користувача.
     * @param role роль користувача (наприклад, USER або ADMIN).
     * @return перенаправлення на сторінку входу після успішної реєстрації.
     */
    @PostMapping("/register")
    public String register(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
        return "redirect:/login";
    }
}
