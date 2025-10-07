package com.example.estoqueAPI.Service;

import com.example.estoqueAPI.Model.Produtos;
import com.example.estoqueAPI.Repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutosRepository prepo;

    public List<Produtos> listarProdutos(){
        return prepo.findAll();
    }

    public Optional<Produtos> produtoPorId(int id){
        return prepo.findById(id);
    }

    public Produtos adicionarProduto(Produtos produto){
        return prepo.save(produto);
    }

    public Produtos atualizarProduto(int id, Produtos atualizacao) {
        Optional<Produtos> produto = prepo.findById(id);
        if (produto.isPresent()) {
            Produtos produtoExistente = produto.get();
            produtoExistente.setPreco(atualizacao.getPreco());
            produtoExistente.setQuantidade(atualizacao.getQuantidade());
            return prepo.save(produtoExistente);
        }
        throw new RuntimeException("Produto não encontrado!");
    }

    public Produtos deletarProduto(int id) {
        Optional<Produtos> produtoOptional = prepo.findById(id);
        if (produtoOptional.isPresent()) {
            Produtos produto = produtoOptional.get();
            prepo.deleteById(id);
            return produto;
        } else {
            throw new RuntimeException("Produto não encontrado!");
        }
    }
}
