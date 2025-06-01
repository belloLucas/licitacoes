package com.bellolucas.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "licitacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Licitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uasg_codigo", nullable = false)
    private String uasgCodigo;

    @Column(name = "numero_pregao", nullable = false)
    private String numeroPregao;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "edital", nullable = false)
    private String edital;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "data_abertura", nullable = false)
    private LocalDate dataAbertura;

    //TODO: Implementar relacionamento com a entidade Item mais tarde.
    //@OneToMany(mappedBy = "licitacao", cascade = CascadeType.ALL)
    //private List<Item> itens;
}
