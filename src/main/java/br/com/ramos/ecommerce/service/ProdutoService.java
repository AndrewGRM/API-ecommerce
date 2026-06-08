package br.com.ramos.ecommerce.service;

import br.com.ramos.ecommerce.database.model.Produto;
import br.com.ramos.ecommerce.database.repository.ProdutoRepository;
import br.com.ramos.ecommerce.dto.ProdutoRequestDTO;
import br.com.ramos.ecommerce.exception.ProdutoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public void criar_produto(ProdutoRequestDTO produtoDTO) {
        produtoRepository.save(Produto.builder()
                        .nome(produtoDTO.getNome())
                        .descricao(produtoDTO.getDescricao())
                        .preco(produtoDTO.getPreco())
                        .quantidade(produtoDTO.getQuantidade())
                .build());
    }

    public List<Produto> ListarProdutos() {
        List<Produto> todosProdutos = produtoRepository.findAll();
        return todosProdutos;
    }

    public Produto atualizarProduto(Long id, ProdutoRequestDTO produtoDTO) {
            Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException(id));
            produtoExistente.setNome(produtoDTO.getNome());
            produtoExistente.setDescricao(produtoDTO.getDescricao());
            produtoExistente.setPreco(produtoDTO.getPreco());
            produtoExistente.setQuantidade(produtoDTO.getQuantidade());
            produtoRepository.save(produtoExistente);
            return produtoExistente;
    }

    public void delete(Long id) {
        if(!produtoRepository.existsById(id)) {
            throw new ProdutoNotFoundException(id);
        }
        produtoRepository.deleteById(id);
    }
}
