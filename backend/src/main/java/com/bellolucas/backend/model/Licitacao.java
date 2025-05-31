package com.bellolucas.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Licitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uasgCodigo;
    private String numeroPregao;
    private String descricao;
    private LocalDate dataAbertura;

    @OneToMany(mappedBy = "licitacao", cascade = CascadeType.ALL)
    private List<Item> itens;
}
