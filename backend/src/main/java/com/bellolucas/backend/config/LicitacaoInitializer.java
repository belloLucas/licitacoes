package com.bellolucas.backend.config;

import com.bellolucas.backend.scraper.LicitacaoScraperService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LicitacaoInitializer implements CommandLineRunner {
    private final LicitacaoScraperService licitacaoScraperService;

    public LicitacaoInitializer(LicitacaoScraperService licitacaoScraperService) {
        this.licitacaoScraperService = licitacaoScraperService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando carregamento de licitações...");
        licitacaoScraperService.extractLicitacoes();
        System.out.println("Carregamento concluído.");
    }
}
