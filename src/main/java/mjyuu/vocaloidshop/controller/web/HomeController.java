package mjyuu.vocaloidshop.controller.web;

import lombok.RequiredArgsConstructor;
import mjyuu.vocaloidshop.service.ProductService;
import mjyuu.vocaloidshop.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {
    
    private final ProductService productService;
    private final CategoryService categoryService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        var allProducts = productService.getAllProducts();
        model.addAttribute("featuredProducts", allProducts.size() > 8 ? allProducts.subList(0, 8) : allProducts);
        return "index";
    }
    
    @GetMapping("/products")
    public String products(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search,
            Model model) {
        
        var products = productService.getAllProducts();
        
        if (categoryId != null) {
            products = productService.getProductsByCategory(categoryId);
        } else if (search != null && !search.isEmpty()) {
            products = productService.searchProducts(search);
        }
        
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("selectedCategory", categoryId);
        model.addAttribute("searchQuery", search);
        
        return "products";
    }
    
    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        var relatedProducts = productService.getAllProducts();
        model.addAttribute("relatedProducts", relatedProducts.size() > 4 ? relatedProducts.subList(0, 4) : relatedProducts);
        return "product-detail";
    }
}
