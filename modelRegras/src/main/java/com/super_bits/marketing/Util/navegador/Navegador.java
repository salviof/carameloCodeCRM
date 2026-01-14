/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.marketing.Util.navegador;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author SalvioF
 */
public class Navegador implements Serializable {

    protected Document documento;
    private List<String> links;
    private List<String> imagens;
    private String paginaprincipal;

    public Navegador(String pUrl) {
        try {

            paginaprincipal = pUrl;
            navegar(pUrl);
            System.out.println("titulo:" + documento.title()); //doc.get
            carregarLinksEImagens();
        } catch (Throwable t) {

        }

    }

    private void carregarLinksEImagens() {
        Elements elementoLinks = documento.select("a[href]");
        if (documento != null) {
            links = new ArrayList<>();
            imagens = new ArrayList<>();
            for (Element elemento : elementoLinks) {
                String endereco = paginaprincipal + elemento.attr("href");
                links.add(endereco);

            }
            Elements img = documento.getElementsByTag("img");
            for (Element el : img) {
                String src = el.absUrl("src");
                if (src == null || src.isEmpty()) {

                    src = el.absUrl("data-src");
                }
                if (src != null && !src.isEmpty()) {
                    imagens.add(src);
                }
            }
        }

    }

    public final void navegar(String pLink) {
        try {
            documento = Jsoup.parse(new URL(pLink), 2000);
            carregarLinksEImagens();
        } catch (Throwable t) {
            System.out.println("Erro navegando em url:" + pLink);
        }
    }

    public void navegar(int idLink) {
        navegar(links.get(idLink));
    }

    public List<String> getLinks() {
        return links;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public boolean isFoiCarregadoDocumento() {
        return documento != null;
    }

}
