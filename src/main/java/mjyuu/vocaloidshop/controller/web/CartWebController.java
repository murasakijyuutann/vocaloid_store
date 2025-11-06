package mjyuu.vocaloidshop.controller.web;

import lombok.RequiredArgsConstructor;
import mjyuu.vocaloidshop.dto.AddToCartRequestDTO;
import mjyuu.vocaloidshop.entity.User;
import mjyuu.vocaloidshop.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartWebController {
    
    private final CartService cartService;
    
    @GetMapping
    public String viewCart(@AuthenticationPrincipal User user, Model model) {
        var cartItems = cartService.getUserCart(user.getId());
        var total = cartService.getCartTotal(user.getId());
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", total);
        return "cart";
    }
    
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                           @RequestParam(defaultValue = "1") Integer quantity,
                           @AuthenticationPrincipal User user) {
        AddToCartRequestDTO request = new AddToCartRequestDTO();
        request.setProductId(productId);
        request.setQuantity(quantity);
        cartService.addToCart(user.getId(), request);
        return "redirect:/cart";
    }
    
    @PostMapping("/update")
    public String updateCartItem(@RequestParam Long itemId,
                                @RequestParam Integer quantity,
                                @AuthenticationPrincipal User user) {
        cartService.updateCartItemQuantity(itemId, quantity);
        return "redirect:/cart";
    }
    
    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long itemId,
                                @AuthenticationPrincipal User user) {
        cartService.removeFromCart(itemId);
        return "redirect:/cart";
    }
}
