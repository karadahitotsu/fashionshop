package karadahitotsu.fashionshop.controllers;

import karadahitotsu.fashionshop.Entity.Cart;
import karadahitotsu.fashionshop.Entity.Products;
import karadahitotsu.fashionshop.Entity.Users;
import karadahitotsu.fashionshop.repository.CartRepository;
import karadahitotsu.fashionshop.repository.ProductsRepository;
import karadahitotsu.fashionshop.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    private Environment environment;
    @GetMapping("/")
    public String mainPage(){
        return "main";
    }
    @GetMapping("/y2k")
    public String y2k(Model model){
        List<Products> jeans = productsRepository.findByCollectionAndCategory("y2k","jeans");
        List<Products> tops = productsRepository.findByCollectionAndCategory("y2k","tops");
        model.addAttribute("jeans",jeans);
        model.addAttribute("tops",tops);
        return "y2k";
    }
    @GetMapping("/opiumcore")
    public String opiumcore(Model model){
        List<Products> jeans = productsRepository.findByCollectionAndCategory("opiumcore","jeans");
        List<Products> tops = productsRepository.findByCollectionAndCategory("opiumcore","tops");
        model.addAttribute("jeans",jeans);
        model.addAttribute("tops",tops);
        return "opiumcore";
    }
    @GetMapping("/product")
    public String product(@RequestParam(name="productid",required = true)Long productid, Model model){
        Products product = productsRepository.getReferenceById(productid);
        model.addAttribute("product",product);
        return "product";
    }
    @GetMapping("/aboutus")
    public String aboutus(){
        return "aboutus";
    }
    @GetMapping("/admin")
    public String admin(Model model){
        List<Products>products = productsRepository.findAll();
        model.addAttribute("products",products);
        return "admin";
    }
    @GetMapping("/payment")
    public String payment(){
        return "payment";
    }
    @GetMapping("/finish")
    public String finish(){
        return "finish";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @GetMapping("/contacts")
    public String contacts(){
        return "contacts";
    }
    @GetMapping("/cart")
    public String cart(@RequestParam(name="userid",required = true)Long userid,Model model){
        //model.addAttribute("user",usersRepository.getReferenceById(userid));
        Users user = usersRepository.getReferenceById(userid);
        List <Cart> cart = cartRepository.findByUser(user);
        Integer price = 0;
        for (int i = 0;i<cart.size();i++){
            price+=cart.get(i).getProduct().getPrice()*cart.get(i).getCount();
        }
        model.addAttribute("products",cart);
        model.addAttribute("price",price);

        return "cart";
    }
    @GetMapping("/images/{folder}/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String folder, @PathVariable String filename) throws IOException {
        String filePath = environment.getProperty("image.storage.path") + folder + "/" + filename;

        File file = new File(filePath);

        if (file.exists()) {
            byte[] imageBytes = new byte[(int) file.length()];
            try (InputStream inputStream = new FileInputStream(file)) {
                inputStream.read(imageBytes);
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
