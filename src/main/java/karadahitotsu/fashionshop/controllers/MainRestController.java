package karadahitotsu.fashionshop.controllers;

import karadahitotsu.fashionshop.Entity.Cart;
import karadahitotsu.fashionshop.Entity.Products;
import karadahitotsu.fashionshop.Entity.Users;
import karadahitotsu.fashionshop.repository.CartRepository;
import karadahitotsu.fashionshop.repository.ProductsRepository;
import karadahitotsu.fashionshop.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class MainRestController {
    @Autowired
    private Environment environment;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CartRepository cartRepository;
    @PostMapping("/image")
    public void image(@RequestBody Products products){
        productsRepository.save(products);

    }
    @PostMapping("/api/createuser")
    public String createUser(@RequestParam(name = "email") String email,@RequestParam(name = "password") String password) {
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(password);

        usersRepository.save(user);
        return "user";

    }
    @PostMapping("/api/loginuser")
    public HashMap loginUser(@RequestParam(name = "email") String email,@RequestParam(name = "password") String password) {
        HashMap<String,String> mappa = new HashMap<>();
        List<Users> userr = usersRepository.findByEmailAndPassword(email,password);
        System.out.println(email+password);
        if(userr.isEmpty()){

            mappa.put("status","wrong data");
            return mappa;

        }
        mappa.put("userid",userr.get(0).getId().toString());
        return mappa;

    }
    @PostMapping("/api/image/catalog")
    public void uploadImageCatalog(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, @RequestParam("category") String category, @RequestParam("price") Integer price, @RequestParam("sizes")List<String> sizes,@RequestParam("brand") String brand,@RequestParam ("description")String description )throws IOException {
        String filePath = saveImageCatalog(file);
        Products product = new Products();
        product.setCategory(category);
        product.setName(name);
        product.setCollection(brand);
        product.setImagePath(filePath);
        product.setPrice(price);
        product.setDescription(description);
        product.setSizes(sizes);
        productsRepository.save(product);

    }



    private String saveImageCatalog(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
        String filePath = environment.getProperty("image.storage.path")+"products/" + fileName;


        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] bytes = new byte[1024];
            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

        return fileName;
    }
    @PostMapping("/api/cart/add")
    public void addCart(@RequestParam("userid") Long userid,@RequestParam("productid") Long productid,@RequestParam("size") String size){
        Users user = usersRepository.getReferenceById(userid);
        Products product = productsRepository.getReferenceById(productid);
        List<Cart> carts = cartRepository.findByUserAndProductAndSize(user,product,size);
        if(!carts.isEmpty()){
            Cart cart = carts.get(0);
            cart.setCount(cart.getCount()+1);
            cartRepository.save(cart);

        }
        else{
            Cart cart = new Cart();
            cart.setCount(1);
            cart.setProduct(product);
            cart.setUser(user);
            cart.setSize(size);
            cartRepository.save(cart);
        }
    }
    @PostMapping("api/product/delete")
    public void deleteProduct(@RequestParam("id") Long productid){
        Products product = productsRepository.getReferenceById(productid);
        List<Cart> cart = cartRepository.findByProduct(product);
        for (int i = 0; i<cart.size();i++){
            cartRepository.delete(cart.get(i));
        }
        productsRepository.delete(product);

    }

}
