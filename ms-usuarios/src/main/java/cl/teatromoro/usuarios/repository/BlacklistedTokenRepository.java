package cl.teatromoro.usuarios.repository;

import cl.teatromoro.usuarios.model.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, String> {
    Optional<BlacklistedToken> findByToken(String token);
}
