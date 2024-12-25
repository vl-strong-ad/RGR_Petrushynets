package com.tpp.rgr.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * Відображення головної сторінки.
     *
     * @param userDetails інформація про поточного користувача (може бути null, якщо користувач не авторизований).
     * @param model модель для передачі даних у шаблон.
     * @return назва шаблону для відображення.
     */
    @GetMapping({"/", "/home"})
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("isAdmin", userDetails.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        } else {
            model.addAttribute("isAuthenticated", false);
        }

        // Додавання атрибутів для всіх даних, доступних на головній сторінці.
        // Наприклад, список регіонів, країн тощо.
        model.addAttribute("region", "Список регіонів доступний");
        model.addAttribute("continent", "Список країн доступний");
        model.addAttribute("allcontinents", "Список континентів доступний");

        return "home";
    }
}
