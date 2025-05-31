package com.bellolucas.backend.scraper;

import com.bellolucas.backend.dto.LicitacaoDTO;
import com.bellolucas.backend.model.Licitacao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LicitacaoScraperService {
    public List<LicitacaoDTO> extractLicitacoes() throws Exception {
        List<LicitacaoDTO> licitacoes = new ArrayList<>();

        InputStream inputStream = new ClassPathResource("mock/mock_licitacoes_comprasnet.html").getInputStream();
        Document doc = Jsoup.parse(inputStream, StandardCharsets.UTF_8.name(), "");

        Elements formularios = doc.select("form");

        for (Element form : formularios) {
            Element td = form.selectFirst("td");
            if (td == null) continue;

            String text = td.text();

            String uasg = extrair(text, "Código da UASG:\\s*(\\d+)");
            String numero = extrair(text, "(?:Pregão|Tomada)\\s+.*?Nº\\s*(\\d+/\\d+)");
            String objeto = extrair(text, "Objeto:\\s+[^-]+-\\s*(.+?)\\s*(Edital a partir de:|Endereço:)");
            String entrega = extrair(text, "Entrega da Proposta:\\s*(\\d{2}/\\d{2}/\\d{4} às [^\\s<]+)");

            licitacoes.add(new LicitacaoDTO(uasg, numero, objeto, entrega));
        }

        return licitacoes;
    }

    public String extrair(String texto, String padrão) {
        Pattern p = Pattern.compile(padrão, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(texto);
        return m.find() ? m.group(1).trim() : null;
    }
}
