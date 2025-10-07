package com.example.estoqueAPI.Repository;

import com.example.estoqueAPI.Model.Movimentacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacoesRepository extends JpaRepository<Movimentacoes, Integer> {
}
