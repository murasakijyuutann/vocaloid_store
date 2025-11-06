package mjyuu.vocaloidshop.controller.web;

import lombok.RequiredArgsConstructor;
import mjyuu.vocaloidshop.entity.User;
import mjyuu.vocaloidshop.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderWebController {
    
    private final OrderService orderService;
    
    @GetMapping
    public String viewOrders(@AuthenticationPrincipal User user, Model model) {
        var orders = orderService.listUserOrders(user.getId());
        model.addAttribute("orders", orders);
        return "orders";
    }
    
    @GetMapping("/{id}")
    public String viewOrderDetail(@PathVariable Long id,
                                  @RequestParam(required = false) Boolean success,
                                  @AuthenticationPrincipal User user,
                                  Model model) {
        var order = orderService.getById(id);
        model.addAttribute("order", order);
        if (success != null && success) {
            model.addAttribute("successMessage", "Order placed successfully!");
        }
        return "order-detail";
    }
}
