package com.example.estoqueAPI.Service;

import com.example.estoqueAPI.Model.Movimentacoes;
import com.example.estoqueAPI.Repository.MovimentacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacoesService {
    @Autowired
    private MovimentacoesRepository mrepo;

    public Movimentacoes Entrada(int id, int quantidade){

    }

    public Movimentacoes Saida(int id, int quantidade){

    }
}
