package com.bellolucas.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
