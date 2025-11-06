package mjyuu.vocaloidshop.controller.web;

import lombok.RequiredArgsConstructor;
import mjyuu.vocaloidshop.dto.AddressRequestDTO;
import mjyuu.vocaloidshop.entity.User;
import mjyuu.vocaloidshop.service.AddressService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileWebController {
    
    private final AddressService addressService;
    
    @GetMapping
    public String viewProfile(@AuthenticationPrincipal User user, Model model) {
        var addresses = addressService.getUserAddresses(user.getId());
        
        model.addAttribute("user", user);
        model.addAttribute("addresses", addresses);
        model.addAttribute("newAddress", new AddressRequestDTO());
        
        return "profile";
    }
    
    @PostMapping("/address/add")
    public String addAddress(@Valid @ModelAttribute("newAddress") AddressRequestDTO addressDto,
                            BindingResult result,
                            @AuthenticationPrincipal User user,
                            Model model) {
        if (result.hasErrors()) {
            var addresses = addressService.getUserAddresses(user.getId());
            model.addAttribute("user", user);
            model.addAttribute("addresses", addresses);
            return "profile";
        }
        
        addressService.addAddress(user.getId(), addressDto);
        return "redirect:/profile?addressAdded=true";
    }
    
    @PostMapping("/address/delete/{id}")
    public String deleteAddress(@PathVariable Long id,
                               @AuthenticationPrincipal User user) {
        addressService.deleteAddress(id);
        return "redirect:/profile?addressDeleted=true";
    }
}
