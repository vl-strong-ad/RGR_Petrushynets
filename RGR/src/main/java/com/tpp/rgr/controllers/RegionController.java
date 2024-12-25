package com.tpp.rgr.controllers;

import com.tpp.rgr.models.Region;
import com.tpp.rgr.service.RegionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    /**
     * Перевіряє, чи є поточний користувач адміністратором.
     */
    private boolean isAdmin(UserDetails userDetails) {
        return userDetails != null && userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    /**
     * Відображення списку всіх регіонів.
     */
    @GetMapping
    public String listRegion(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("regions", regionService.getAllRegions());
        model.addAttribute("isAdmin", isAdmin(userDetails));
        return "region"; // Переконайтесь, що шаблон region.html існує
    }

    /**
     * Форма для додавання нового регіону.
     */
    @GetMapping("/add")
    public String addRegionForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (!isAdmin(userDetails)) {
            return "access-denied"; // Переконайтесь, що шаблон access-denied.html існує
        }
        model.addAttribute("region", new Region());
        return "add-region"; // Переконайтесь, що шаблон add-region.html існує
    }

    /**
     * Додавання нового регіону.
     */
    @PostMapping("/add")
    public String addRegion(@AuthenticationPrincipal UserDetails userDetails,
                            @Valid @ModelAttribute("region") Region region,
                            BindingResult result) {
        if (!isAdmin(userDetails)) {
            return "access-denied";
        }
        if (result.hasErrors()) {
            return "add-region";
        }
        regionService.saveRegion(region);
        return "redirect:/region";
    }

    /**
     * Форма для редагування регіону за ID.
     */
    @GetMapping("/edit/{id}")
    public String editRegionForm(@AuthenticationPrincipal UserDetails userDetails,
                                 @PathVariable("id") Integer id,
                                 Model model) {
        if (!isAdmin(userDetails)) {
            return "access-denied";
        }
        Region region = regionService.findRegionById(id).orElse(null);
        if (region == null) {
            model.addAttribute("errorMessage", "Регіон не знайдено."); // Додано повідомлення про помилку
            return "redirect:/region";
        }
        model.addAttribute("region", region);
        return "edit-region"; // Переконайтесь, що шаблон edit-region.html існує
    }

    /**
     * Оновлення даних регіону.
     */
    @PostMapping("/update/{id}")
    public String updateRegion(@AuthenticationPrincipal UserDetails userDetails,
                               @PathVariable("id") Integer id,
                               @Valid @ModelAttribute("region") Region region,
                               BindingResult result) {
        if (!isAdmin(userDetails)) {
            return "access-denied";
        }
        if (result.hasErrors()) {
            return "edit-region";
        }
        region.setRegionId(id);
        regionService.updateRegion(region);
        return "redirect:/region";
    }

    /**
     * Видалення регіону за ID.
     */
    @GetMapping("/delete/{id}")
    public String deleteRegion(@AuthenticationPrincipal UserDetails userDetails,
                               @PathVariable("id") Integer id) {
        if (!isAdmin(userDetails)) {
            return "access-denied";
        }
        regionService.deleteRegion(id);
        return "redirect:/region";
    }
}
