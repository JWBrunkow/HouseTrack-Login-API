package spring.login.orm.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import spring.login.orm.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(Long userId);
}
