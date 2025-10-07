package com.example.estoqueAPI.Repository;

import com.example.estoqueAPI.Model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {
}
