package az.ada.library.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.ada.library.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

	Boolean existsByEmail(String username);
}
