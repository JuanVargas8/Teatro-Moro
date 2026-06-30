package cl.teatromoro.common.security;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ms-usuarios")
public interface AuthClient {

    @GetMapping("/api/v1/auth/validate")
    ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String bearerToken);
}
