package karadahitotsu.fashionshop.repository;

import karadahitotsu.fashionshop.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    List<Users> findByEmailAndPassword(String email, String password);
}
