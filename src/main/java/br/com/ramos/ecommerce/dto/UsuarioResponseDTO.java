package br.com.ramos.ecommerce.dto;

import br.com.ramos.ecommerce.database.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UsuarioResponseDTO {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private Boolean enabled;
}
