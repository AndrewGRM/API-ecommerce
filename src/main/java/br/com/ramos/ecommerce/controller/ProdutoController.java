package br.com.ramos.ecommerce.controller;

import java.util.List;

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

import br.com.ramos.ecommerce.database.model.ProdutoEntity;
import br.com.ramos.ecommerce.database.repository.ProdutoRepository;
import br.com.ramos.ecommerce.dto.ProdutoDTO;
import br.com.ramos.ecommerce.exception.ProdutoNotFoundException;
import br.com.ramos.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/produtos")
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "Gestão de produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Listar todos os produtos",
            description = "Retorna uma lista com todos os produtos cadastrados no sistema. "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ")
    })
    public ResponseEntity<List<ProdutoEntity>> ListarTodosProdutos() {
        List<ProdutoEntity> TodosProdutos = produtoService.ListarProdutos();
        return ResponseEntity.ok().body(TodosProdutos);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Buscar produto por ID",
            description = "Retorna os detalhes completos de um específico através do seu ID. " +
                    "Caso o produto não seja encontrado, retorna status 404 Not Found."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado com o Id informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ProdutoEntity ListarProdutoPorId(
            @Parameter(description = "ID do produto a ser buscado", example = "1", required = true)
            @PathVariable Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Cria um novo produto",
            description = "Cadastra um novo produto no sistema. " +
                    "Recebe os dados do produto e retorna o produto criado com status 201 Created. "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou incompletos"),
            @ApiResponse(responseCode = "409", description = "Já existe produto com esse Id ou SKU"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void CriarProduto(
            @Parameter(description = "Produto a ser criado", required = true)
            @RequestBody ProdutoDTO produtoDTO) {
        produtoService.save(produtoDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Atualizar um produto existente",
            description = "Atualiza todos os dados de um produto já cadastrado. " +
                    "É necessário informar o ID do produto e todos os campos no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou requisição mal formada"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado com o Id informado"),
            @ApiResponse(responseCode = "409", description = "Conflito ao atualizar o produto (ex: SKU ou Id duplicado)"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ProdutoEntity atualizarProduto(
            @Parameter(description = "Id do produto a ser atualizado", example = "2", required = true)
            @PathVariable Long id,
            @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.atualizarProduto(id, produtoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Deletar um produto",
            description = "Remove um produto permanentemente do sistema através do seu ID. " +
                    "Caso o produto seja deletado com sucesso, retorna o status 204 No Content " +
                    "(Sem corpo na resposta).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do Servidor")
    })
    public void deletarProduto(
            @Parameter(description = "Id do produto a ser deletado", example = "3", required = true)
            @PathVariable Long id) {
        produtoService.delete(id);
    }
}
