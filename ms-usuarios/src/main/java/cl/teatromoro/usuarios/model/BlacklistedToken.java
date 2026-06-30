package cl.teatromoro.usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistedToken {

    @Id
    @Column(length = 500)
    private String token;

    private Date fechaExpiracion;
}
