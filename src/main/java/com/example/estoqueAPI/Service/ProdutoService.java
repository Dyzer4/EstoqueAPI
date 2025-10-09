package com.example.estoqueAPI.Service;

import com.example.estoqueAPI.Model.Produto;
import com.example.estoqueAPI.Repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutosRepository prepo;

    public List<Produto> listarProdutos() {
        return prepo.findAll();
    }

    public Optional<Produto> produtoPorId(int id) {
        return prepo.findById(id);
    }

    public Produto adicionarProduto(Produto produto) {
        return prepo.save(produto);
    }

    public Produto atualizarProduto(int id, Produto atualizacao) {
        Optional<Produto> produtoOptional = prepo.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produtoExistente = produtoOptional.get();
            produtoExistente.setNome(atualizacao.getNome());
            produtoExistente.setPreco(atualizacao.getPreco());
            produtoExistente.setEstoqueAtual(atualizacao.getEstoqueAtual()); // ← atualizado
            return prepo.save(produtoExistente);
        } else {
            throw new RuntimeException("Produto não encontrado!");
        }
    }

    public Produto deletarProduto(int id) {
        Optional<Produto> produtoOptional = prepo.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            prepo.delete(produto);
            return produto;
        } else {
            throw new RuntimeException("Produto não encontrado!");
        }
    }
}
