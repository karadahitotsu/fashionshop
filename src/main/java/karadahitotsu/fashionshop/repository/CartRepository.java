package karadahitotsu.fashionshop.repository;

import karadahitotsu.fashionshop.Entity.Cart;
import karadahitotsu.fashionshop.Entity.Products;
import karadahitotsu.fashionshop.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByUser(Users users);
    List<Cart> findByProduct(Products product);

    List<Cart> findByUserAndProductAndSize(Users users, Products products,String size);
}
