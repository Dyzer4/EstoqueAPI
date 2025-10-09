package com.example.estoqueAPI.Service;

import com.example.estoqueAPI.Model.Movimentacoes;
import com.example.estoqueAPI.Model.Produto;
import com.example.estoqueAPI.Repository.MovimentacoesRepository;
import com.example.estoqueAPI.Repository.ProdutosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovimentacoesService {

    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private MovimentacoesRepository mrepo;

    public Movimentacoes registrarMovimentacao(Movimentacoes moviRequest) {
        Produto produto = produtosRepository.findById(moviRequest.getProduto().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produto com ID " + moviRequest.getProduto().getId() + " não encontrado."));

        Movimentacoes movi = new Movimentacoes();

        int quantidade = moviRequest.getQuantidade();
        if (quantidade > 0) {
            movi.setTipo("Entrada");

            // Atualiza o estoque
            produto.setEstoqueAtual(produto.getEstoqueAtual() + quantidade);
        } else if (quantidade < 0) {
            movi.setTipo("Saída");
            int quantidadeSaida = Math.abs(quantidade);

            if (produto.getEstoqueAtual() < quantidadeSaida) {
                throw new IllegalArgumentException("Estoque insuficiente. Estoque atual: " + produto.getEstoqueAtual());
            }

            produto.setEstoqueAtual(produto.getEstoqueAtual() - quantidadeSaida);
            quantidade = quantidadeSaida;
        } else {
            throw new IllegalArgumentException("Quantidade não pode ser zero.");
        }

        movi.setQuantidade(quantidade);
        movi.setData(LocalDate.now());
        movi.setProduto(produto);
        movi.setObservacao(
                (moviRequest.getObservacao() == null || moviRequest.getObservacao().isBlank())
                        ? "Sem observações."
                        : moviRequest.getObservacao()
        );

        produtosRepository.save(produto);
        return mrepo.save(movi);
    }

    public List<Movimentacoes> findByProdutoId(int produtoId){
        return mrepo.findByProdutoId(produtoId);
    }

}
