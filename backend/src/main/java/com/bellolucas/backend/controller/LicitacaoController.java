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
    public ResponseEntity<Page<LicitacaoDTO>> listByUasgCodigo(
            @PathVariable String uasgCodigo, Pageable pageable) {
        Page<Licitacao> page = licitacaoService.listByUasgCodigo(uasgCodigo, pageable);
        return page.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(page.map(LicitacaoDTO::new));
    }

    @GetMapping("/pregao")
    public ResponseEntity<Page<LicitacaoDTO>> listByNumeroPregao(@RequestParam String numeroPregao, Pageable pageable) {
        Page<Licitacao> page = licitacaoService.listByNumeroPregao(numeroPregao, pageable);
        return page.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(page.map(LicitacaoDTO::new));
    }
}
