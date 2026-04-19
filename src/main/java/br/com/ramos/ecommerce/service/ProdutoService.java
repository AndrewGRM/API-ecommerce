package br.com.ramos.ecommerce.service;

import br.com.ramos.ecommerce.database.model.ProdutoEntity;
import br.com.ramos.ecommerce.database.repository.ProdutoRepository;
import br.com.ramos.ecommerce.dto.ProdutoDTO;
import br.com.ramos.ecommerce.exception.ProdutoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public void save(ProdutoDTO produtoDTO) {
        produtoRepository.save(ProdutoEntity.builder()
                        .nome(produtoDTO.getNome())
                        .descricao(produtoDTO.getDescricao())
                        .preco(produtoDTO.getPreco())
                        .quantidade(produtoDTO.getQuantidade())
                .build());
    }

    public List<ProdutoEntity> ListarProdutos() {
        List<ProdutoEntity> todosProdutos = produtoRepository.findAll();
        return todosProdutos;
    }

    public ProdutoEntity atualizarProduto(Long id, ProdutoDTO produtoDTO) {
            ProdutoEntity produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException(id));
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
