package com.bellolucas.backend.controller;

import com.bellolucas.backend.dto.LicitacaoDTO;
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
    public ResponseEntity<Page<LicitacaoDTO>> list(Pageable pageable) {
        Page<Licitacao> page = licitacaoService.list(pageable);
        return page.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(page.map(LicitacaoDTO::new));
    }

    @GetMapping("/uasg/{uasgCodigo}")
    public ResponseEntity<List<LicitacaoDTO>> listByUasgCodigo(@PathVariable String uasgCodigo) {
        List<Licitacao> licitacoes = licitacaoService.listByUasgCodigo(uasgCodigo);
        return licitacoes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(licitacoes.stream().map(LicitacaoDTO::new).toList());
    }

    @GetMapping("/pregao")
    public ResponseEntity<List<LicitacaoDTO>> listByNumeroPregao(@RequestParam String numeroPregao) {
        List<Licitacao> licitacoes = licitacaoService.listByNumeroPregao(numeroPregao);
        return licitacoes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(licitacoes.stream().map(LicitacaoDTO::new).toList());
    }
}
