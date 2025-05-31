package com.bellolucas.backend.model;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String unidade;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "licitacao_id")
    private Licitacao licitacao;
}
