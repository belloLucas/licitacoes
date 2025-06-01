package com.bellolucas.backend.controller;

import com.bellolucas.backend.model.Licitacao;
import com.bellolucas.backend.service.LicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/licitacoes")
public class LicitacaoController {
    @Autowired
    private LicitacaoService licitacaoService;

    @GetMapping
    public ResponseEntity<Page<Licitacao>> list(Pageable pageable) {
        Page<Licitacao> page = licitacaoService.list(pageable);
        return page.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(page);
    }

    @GetMapping("/uasg/{uasgCodigo}")
    public ResponseEntity<List<Licitacao>> listByUasgCodigo(@PathVariable  String uasgCodigo) {
        List<Licitacao> licitacoes = licitacaoService.listByUasgCodigo(uasgCodigo);
        return licitacoes.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(licitacoes);
    }

    @GetMapping("/pregao")
    public ResponseEntity<List<Licitacao>> listByNumeroPregao(@RequestParam String numeroPregao) {
        List<Licitacao> licitacoes = licitacaoService.listByNumeroPregao(numeroPregao);
        return licitacoes.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(licitacoes);
    }
}
