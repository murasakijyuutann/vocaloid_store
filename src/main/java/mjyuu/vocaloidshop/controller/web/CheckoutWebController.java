package mjyuu.vocaloidshop.controller.web;

import lombok.RequiredArgsConstructor;
import mjyuu.vocaloidshop.entity.User;
import mjyuu.vocaloidshop.service.CartService;
import mjyuu.vocaloidshop.service.OrderService;
import mjyuu.vocaloidshop.service.AddressService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutWebController {
    
    private final CartService cartService;
    private final OrderService orderService;
    private final AddressService addressService;
    
    @GetMapping
    public String checkout(@AuthenticationPrincipal User user, Model model) {
        var cartItems = cartService.getUserCart(user.getId());
        var total = cartService.getCartTotal(user.getId());
        var addresses = addressService.getUserAddresses(user.getId());
        
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", total);
        model.addAttribute("addresses", addresses);
        
        return "checkout";
    }
    
    @PostMapping("/place-order")
    public String placeOrder(@RequestParam(required = false) Long addressId,
                            @AuthenticationPrincipal User user,
                            Model model) {
        try {
            var order = orderService.placeOrder(user.getId(), addressId);
            return "redirect:/orders/" + order.getId() + "?success=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            var cartItems = cartService.getUserCart(user.getId());
            var total = cartService.getCartTotal(user.getId());
            var addresses = addressService.getUserAddresses(user.getId());
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("cartTotal", total);
            model.addAttribute("addresses", addresses);
            return "checkout";
        }
    }
}
