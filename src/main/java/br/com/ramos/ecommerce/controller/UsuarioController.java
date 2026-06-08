package br.com.ramos.ecommerce.controller;

import br.com.ramos.ecommerce.database.model.Usuario;
import br.com.ramos.ecommerce.database.repository.UsuarioRepository;
import br.com.ramos.ecommerce.dto.UsuarioRequestDTO;
import br.com.ramos.ecommerce.exception.UsuarioNotFoundException;
import br.com.ramos.ecommerce.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Gestão de Usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Lista todos os usuários.",
            description = "Retorna uma lista com todos os usuários cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<List<Usuario>> ListarTodosUsuarios() {
        List<Usuario> TodosUsuarios = usuarioService.ListarUsuarios();
        return ResponseEntity.ok().body(TodosUsuarios);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Busca usuário por ID.",
            description = "Retorna os detalhes completos de um usuário específico através do seu ID. " +
                    "Caso o usuário não seja encontrado, retorna status 404 Not Found. "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado com o ID informado."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public Usuario ListarUsuarioPorId(
            @Parameter(description = "Id do usuário a ser buscado", example = "1", required = true)
            @PathVariable Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Cria um novo usuário",
            description = "Cadastra um novo usuário no sistema. " +
                    "Recebe os dados do Usuário e retorna o Usuário criado com status 201 Created. "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos on incompletos."),
            @ApiResponse(responseCode = "409", description = "Já existe um usuário com este ID."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public void CriarUsuario(
            @Parameter(description = "Usuário a ser criado.", required = true)
            @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        UsuarioRequestDTO responseUsuario = usuarioRequestDTO;
        usuarioService.salvar_Usuario(responseUsuario);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Atualizar um usuário existente",
            description = "Atualiza todos os dados de um usuário já cadastrado. " +
                    "É necessário informar o ID do usuário e todos os campos no corpo da requisição. "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou requisição mal formada."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado com id informado."),
            @ApiResponse(responseCode = "409", description = "Conflito ao atualizar o usuário (ex: Id duplicado)."),
            @ApiResponse(responseCode = "500", description = "Erro interno do Servidor.")
    })
    public Usuario atualizarUsuario(
            @Parameter(description = "Id do usuário a ser atualizado", example = "1", required = true)
            @PathVariable Long id,
            @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.atualizar_usuario(id, usuarioRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Deletar Usuário",
            description = "Deleta um Usuário permanentemente do sistema através do seu ID. " +
                    "Caso o Usuário seja deletado com sucesso, retorna o status 204 No Content " +
                    "(Sem corpo na resposta).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do Servidor")
    })
    public void deletarUsuario(
            @Parameter(description = "Id do usuário a ser deletado", example = "1", required = true)
            @PathVariable Long id) {
        usuarioService.deletar_usuario(id);
    }
}
