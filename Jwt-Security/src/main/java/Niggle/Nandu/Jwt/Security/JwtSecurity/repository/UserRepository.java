package Niggle.Nandu.Jwt.Security.JwtSecurity.repository;

import Niggle.Nandu.Jwt.Security.JwtSecurity.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
