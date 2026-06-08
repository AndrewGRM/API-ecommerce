package br.com.ramos.ecommerce.service;

import br.com.ramos.ecommerce.database.model.Usuario;
import br.com.ramos.ecommerce.database.repository.UsuarioRepository;
import br.com.ramos.ecommerce.dto.UsuarioRequestDTO;
import br.com.ramos.ecommerce.exception.UsuarioNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void salvar_Usuario(UsuarioRequestDTO requestUsuarioDTO) {
        usuarioRepository.save(Usuario.builder()
                        .username(requestUsuarioDTO.getUsername())
                        .email(requestUsuarioDTO.getEmail())
                        .password(passwordEncoder.encode(requestUsuarioDTO.getPassword()))
                        .build());
    }

    public List<Usuario> ListarUsuarios() {
        List<Usuario> todosUsuarios = usuarioRepository.findAll();
        return todosUsuarios;
    }

    public Usuario atualizar_usuario(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        usuarioExistente.setUsername(usuarioRequestDTO.getUsername());
        usuarioExistente.setEmail(usuarioRequestDTO.getEmail());
        if(usuarioRequestDTO.getPassword() != null && !usuarioRequestDTO.getPassword().isBlank()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuarioRequestDTO.getPassword()));
        }
        usuarioExistente.setRole(usuarioRequestDTO.getRole());
        if (usuarioExistente.getEnabled() != null) {
            usuarioExistente.setEnabled(usuarioRequestDTO.getEnabled());
        }
        usuarioRepository.save(usuarioExistente);
        return usuarioExistente;
    }

    public void deletar_usuario(Long id){
        if(!usuarioRepository.existsById(id)) {
            throw new UsuarioNotFoundException(id);
        }
        usuarioRepository.deleteById(id);
    }

}
