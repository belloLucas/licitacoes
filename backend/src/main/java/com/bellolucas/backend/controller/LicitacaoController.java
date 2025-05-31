package com.bellolucas.backend.controller;

import com.bellolucas.backend.model.Licitacao;
import com.bellolucas.backend.service.LicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/licitacoes")
public class LicitacaoController {
    @Autowired
    private LicitacaoService licitacaoService;

    @GetMapping
    public List<Licitacao> list() {
        return licitacaoService.list();
    }

    @GetMapping("/uasg/{uasgCodigo}")
    public Licitacao listByUasgCodigo(String uasgCodigo) {
        return licitacaoService.listByUasgCodigo(uasgCodigo);
    }

    @GetMapping("/pregao/{numeroPregao}")
    public Licitacao listByNumeroPregao(String numeroPregao) {
        return licitacaoService.listByNumeroPregao(numeroPregao);
    }
}
