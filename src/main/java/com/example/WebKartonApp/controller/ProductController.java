package com.example.WebKartonApp.controller;

import com.example.WebKartonApp.model.Product;
import com.example.WebKartonApp.service.ProductService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Data
@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("product", productService.findAll());
        return "panel";
    }

//    @GetMapping("/{category}")
//    public String getCategory(@PathVariable String category, Model model) {
//        model.addAttribute("category", category);
//
//        List<String> subcategories = productService.getSubcategoriesByCategoryName(category);
//        List<Product> products = productService.getProductsByCategoryName(category);
//
//        model.addAttribute("subcategories", subcategories);
//        model.addAttribute("products", products);
//
//        return "catalog";
//    }
//
//    @GetMapping("/{category}/{subcategory}")
//    public String getSubcategory(@PathVariable String category, @PathVariable String subcategory, Model model) {
//        model.addAttribute("category", category);
//        model.addAttribute("subcategory", subcategory);
//
//        List<Product> products = productService.getProductsBySubcategoryName(subcategory);
//        List<Product> subcategories = Collections.emptyList();
//
//        model.addAttribute("subcategories", subcategories);
//        model.addAttribute("products", products);
//
//        return "catalog";
//    }
//
//    @GetMapping("/{category}/{subcategory}/{product}")
//    public String getProduct(@PathVariable String category, @PathVariable String subcategory, @PathVariable String product, Model model) {
//        model.addAttribute("product", productService.getProductBySlug(product));
//        return "product";
//    }
//        public static String reversTransliterate(String message){
//        char[] abcCyr =   {'-','a','b','v','g','d','e','e','z'+'h','z','i','y','k','l','m','n','o','p','r','s','t','u','f','h','t'+'s','c'+'h','s'+'h','s'+'c'+'h','i','e', 'j'+'u','j'+'a','А','B','V','G','D','E','E', 'Z'+'h','Z','I','Y','K','L','M','N','O','P','R','S','T','U','F','H', 'T'+'s', 'C'+'h','S'+'h','S'+'c'+'h','I','E','J'+'u','J'+'a','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
//        String[] abcLat = {" ","а","б","в","г","д","е","ё", "ж","з","и","й","к","л","м","н","о","п","р","с","т","у","ф","х", "ц","ч", "ш","щ","ъ","ы","ь","э", "ю","я","А","Б","В","Г","Д","Е","Ё", "Ж","З","И","Й","К","Л","М","Н","О","П","Р","С","Т","У","Ф","Х", "Ц", "Ч","Ш", "Щ","Ъ","Ы","Ь","Э","Ю","Я","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < message.length(); i++) {
//            for (int x = 0; x < abcCyr.length; x++ ) {
//                if (message.charAt(i) == abcCyr[x]) {
//                    builder.append(abcLat[x]);
//                }
//            }
//        }
//        return builder.toString();
//    }
}