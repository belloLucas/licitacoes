package com.bellolucas.backend.controller;

import com.bellolucas.backend.model.Licitacao;
import com.bellolucas.backend.service.LicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/licitacoes")
public class LicitacaoController {
    @Autowired
    private LicitacaoService licitacaoService;

    @GetMapping
    public Page<Licitacao> list(Pageable pageable) {
        return licitacaoService.list(pageable);
    }

    @GetMapping("/uasg/{uasgCodigo}")
    public List<Licitacao> listByUasgCodigo(@PathVariable  String uasgCodigo) {
        return licitacaoService.listByUasgCodigo(uasgCodigo);
    }

    @GetMapping("/pregao")
    public List<Licitacao> listByNumeroPregao(@RequestParam String numeroPregao) {
        return licitacaoService.listByNumeroPregao(numeroPregao);
    }
}
