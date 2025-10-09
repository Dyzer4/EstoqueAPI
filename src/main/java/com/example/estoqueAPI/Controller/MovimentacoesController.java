package com.example.estoqueAPI.Controller;

import com.example.estoqueAPI.Model.Movimentacoes;
import com.example.estoqueAPI.Service.MovimentacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacoesController {
    @Autowired
    private MovimentacoesService movimentacoesService;

    @GetMapping("/{idProduto}")
    List<Movimentacoes> ListarMovimentacoes(@PathVariable int idProduto){
        return movimentacoesService.findByProdutoId(idProduto);
    }

    @PostMapping
    public Movimentacoes adicionarMovimentacao(@RequestBody Movimentacoes movimentacao) {
        return movimentacoesService.registrarMovimentacao(movimentacao);
    }
}
