package com.example.estoqueAPI.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "MovimentacoesEstoque")
public class Movimentacoes {

    public enum Tipo {
        Entrada,
        Saida
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private int idProduto;

    @Column(nullable = true)
    private String observacao;
}
