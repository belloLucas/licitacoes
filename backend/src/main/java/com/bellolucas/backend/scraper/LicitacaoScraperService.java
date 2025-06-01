package com.bellolucas.backend.scraper;

import com.bellolucas.backend.dto.LicitacaoDTO;
import com.bellolucas.backend.model.Licitacao;
import com.bellolucas.backend.repository.LicitacaoRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LicitacaoScraperService {
    @Autowired
    private LicitacaoRepository licitacaoRepository;

    public void extractLicitacoes() throws Exception {
        InputStream inputStream = new ClassPathResource("mock/mock_licitacoes_comprasnet.html").getInputStream();
        Document doc = Jsoup.parse(inputStream, StandardCharsets.UTF_8.name(), "");

        Elements formularios = doc.select("form");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Element form : formularios) {
            Element td = form.select("table td").get(1);

            if (td == null) continue;

            String text = td.text();

            String uasg = extrair(text, "Código da UASG:\\s*(\\d+)");
            String numero = extrair(text, "(?:Pregão|Tomada)\\s+.*?Nº\\s*(\\d+/\\d+)");
            String descricao = extrair(text, "Objeto:\\s*(?:Objeto:\\s*)?(.+?)\\s*(Edital a partir de:|Endereço:)");
            String edital = extrair(text, "Edital a partir de:\\s*([\\d/]+ das [^\\s<]+)");
            String endereco = extrair(text, "Endereço:\\s*(.+?)\\s*Telefone:");
            String telefone = extrair(text, "Telefone:\\s*(.+?)(?=\\s*Fax:|\\s*Entrega da Proposta:|$)");
            String fax = extrair(text, "Fax:\\s*(.+?)(?=\\s*Entrega da Proposta:|$)");
            String entrega = extrair(text, "Entrega da Proposta:\\s*(\\d{2}/\\d{2}/\\d{4} às [^\\s<]+)");

            LocalDate data = null;
            if (entrega != null && entrega.length() >= 10) {
                String dataStr = entrega.substring(0, 10);
                data = LocalDate.parse(dataStr, formatter);
            }

            Licitacao lic = new Licitacao(null, uasg, numero, descricao, edital, endereco, telefone, fax, data);
            licitacaoRepository.save(lic);
        }
    }

    public String extrair(String texto, String padrão) {
        Pattern p = Pattern.compile(padrão, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(texto);
        return m.find() ? m.group(1).trim() : null;
    }
}
