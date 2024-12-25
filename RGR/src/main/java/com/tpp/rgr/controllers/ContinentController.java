package com.tpp.rgr.controllers;

import com.tpp.rgr.models.Continent;
import com.tpp.rgr.service.ContinentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/continents")
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    /**
     * Перевірка ролі користувача.
     */
    private boolean isAdmin(UserDetails userDetails) {
        return userDetails != null && userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    /**
     * Відображення списку континентів.
     */
    @GetMapping
    public String listContinents(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("continents", continentService.getAllContinents());
        model.addAttribute("isAdmin", isAdmin(userDetails));
        return "continent";  // Використовуємо шаблон continent.html
    }

    /**
     * Форма для додавання нового континенту.
     */
    @GetMapping("/add")
    public String addContinentForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (!isAdmin(userDetails)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Доступ заборонено");
        }
        model.addAttribute("continent", new Continent());
        return "add-continent";
    }

    /**
     * Обробка додавання нового континенту.
     */
    @PostMapping("/add")
    public String addContinent(@AuthenticationPrincipal UserDetails userDetails,
                               @Valid @ModelAttribute("continent") Continent continent,
                               BindingResult result) {
        if (!isAdmin(userDetails)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Доступ заборонено");
        }
        if (result.hasErrors()) {
            return "add-continent";
        }
        continentService.saveContinent(continent);
        return "redirect:/continents";
    }

    /**
     * Форма для редагування континенту.
     */
    @GetMapping("/edit/{id}")
    public String editContinentForm(@AuthenticationPrincipal UserDetails userDetails,
                                    @PathVariable("id") Integer id,
                                    Model model) {
        if (!isAdmin(userDetails)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Доступ заборонено");
        }
        Continent continent = continentService.findContinentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Континент не знайдено"));
        model.addAttribute("continent", continent);
        return "edit-continent";
    }

    /**
     * Обробка оновлення даних континенту.
     */
    @PostMapping("/update/{id}")
    public String updateContinent(@AuthenticationPrincipal UserDetails userDetails,
                                  @PathVariable("id") Integer id,
                                  @Valid @ModelAttribute("continent") Continent continent,
                                  BindingResult result) {
        if (!isAdmin(userDetails)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Доступ заборонено");
        }
        if (result.hasErrors()) {
            return "edit-continent";
        }
        continent.setContinentId(id);
        continentService.updateContinent(continent);
        return "redirect:/continents";
    }

    /**
     * Видалення континенту.
     */
    @GetMapping("/delete/{id}")
    public String deleteContinent(@AuthenticationPrincipal UserDetails userDetails,
                                  @PathVariable("id") Integer id) {
        if (!isAdmin(userDetails)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Доступ заборонено");
        }
        continentService.deleteContinent(id);
        return "redirect:/continents";
    }

    /**
     * Обробка помилок.
     */
    @ExceptionHandler(ResponseStatusException.class)
    public String handleNotFound(ResponseStatusException ex, Model model) {
        model.addAttribute("error", ex.getReason());
        return "error";
    }
}
