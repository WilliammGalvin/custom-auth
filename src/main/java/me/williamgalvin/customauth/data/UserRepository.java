package me.williamgalvin.customauth.data;

import me.williamgalvin.customauth.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findById(long id);

}
