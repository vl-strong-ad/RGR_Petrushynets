package com.tpp.rgr.controllers;

import com.tpp.rgr.models.AllCountries;
import com.tpp.rgr.service.AllCountriesService;
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
@RequestMapping("/allcountries")
public class AllCountriesController {

    @Autowired
    private AllCountriesService allCountryService;

    /**
     * Перевірка, чи є користувач адміністратором.
     */
    private boolean isAdmin(UserDetails userDetails) {
        return userDetails != null && userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    /**
     * Відображення списку всіх країн.
     */
    @GetMapping
    public String listAllCountries(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("allcountries", allCountryService.getAllCountries());
        model.addAttribute("isAdmin", isAdmin(userDetails));
        return "allcountries";
    }

    /**
     * Форма для додавання нової країни.
     */
    @GetMapping("/add")
    public String addAllCountriesForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (!isAdmin(userDetails)) {
            return "access-denied";
        }
        model.addAttribute("allcountries", new AllCountries());
        return "add-allcountries";
    }

    /**
     * Обробка додавання нової країни.
     */
    @PostMapping("/add")
    public String addAllCountries(@AuthenticationPrincipal UserDetails userDetails,
                                  @Valid @ModelAttribute("allcountries") AllCountries allCountry,
                                  BindingResult result) {
        if (!isAdmin(userDetails)) {
            return "access-denied";
        }
        if (result.hasErrors()) {
            return "add-allcountries";
        }
        allCountryService.saveCountry(allCountry);
        return "redirect:/allcountries";
    }

    /**
     * Форма для редагування країни.
     */
    @GetMapping("/edit/{id}")
    public String editAllCountriesForm(@AuthenticationPrincipal UserDetails userDetails,
                                       @PathVariable("id") Integer id,
                                       Model model) {
        if (!isAdmin(userDetails)) {
            return "access-denied";
        }
        AllCountries allCountry = allCountryService.findCountryById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Країну не знайдено"));
        model.addAttribute("allcountries", allCountry);
        return "edit-allcountries";
    }

    /**
     * Обробка оновлення даних країни.
     */
    @PostMapping("/update/{id}")
    public String updateAllCountries(@AuthenticationPrincipal UserDetails userDetails,
                                     @PathVariable("id") Integer id,
                                     @Valid @ModelAttribute("allcountries") AllCountries allCountry,
                                     BindingResult result) {
        if (!isAdmin(userDetails)) {
            return "access-denied";
        }
        if (result.hasErrors()) {
            return "edit-allcountries";
        }
        allCountry.setId(id);
        allCountryService.updateCountry(allCountry);
        return "redirect:/allcountries";
    }

    /**
     * Видалення країни.
     */
    @GetMapping("/delete/{id}")
    public String deleteAllCountries(@AuthenticationPrincipal UserDetails userDetails,
                                     @PathVariable("id") Integer id) {
        if (!isAdmin(userDetails)) {
            return "access-denied";
        }
        allCountryService.deleteCountry(id);
        return "redirect:/allcountries";
    }

    /**
     * Обробка виключень.
     */
    @ExceptionHandler(ResponseStatusException.class)
    public String handleNotFound(ResponseStatusException ex, Model model) {
        model.addAttribute("error", ex.getReason());
        return "error";
    }
}
