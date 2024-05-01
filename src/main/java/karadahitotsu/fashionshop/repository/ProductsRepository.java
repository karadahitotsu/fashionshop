package karadahitotsu.fashionshop.repository;

import karadahitotsu.fashionshop.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Long> {
    List<Products> findByCollectionAndCategory(String collection, String category);
}
