/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.regras_de_negocio_e_controller.intranetMarketingDigital.controller;

import com.steadystate.css.parser.CSSOMParser;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleSheet;

/**
 *
 * @author salvio
 */
public class ProcessadorEmailHtml {

    CSSOMParser parser = new CSSOMParser();
    final CSSStyleSheet stylesheet;
    final Document document;
    final CSSRuleList rules;

    final Map<Element, Map<String, String>> elementStyles = new HashMap<>();

    public ProcessadorEmailHtml(String pHTML, String pCSS) {
        document = Jsoup.parse(pHTML);
        try {
            stylesheet = parser.parseStyleSheet(new InputSource(new StringReader(pCSS)), null, null);
            stylesheet.getCssRules();
            rules = stylesheet.getCssRules();

        } catch (IOException ex) {
            throw new UnsupportedOperationException("falhou " + ex.getMessage());
        }

    }

    public String getHtmlProcessado() {

        /*
 * For each rule in the style sheet, find all HTML elements that match
 * based on its selector and store the style attributes in the map with
 * the selected element as the key.
         */
        for (int i = 0; i < rules.getLength(); i++) {
            final CSSRule rule = rules.item(i);
            if (rule instanceof CSSStyleRule) {
                final CSSStyleRule styleRule = (CSSStyleRule) rule;
                final String selector = styleRule.getSelectorText();

                // Ignore pseudo classes, as JSoup's selector cannot handle
                // them.
                if (!selector.contains(":")) {
                    final Elements selectedElements = document.select(selector);
                    for (final Element selected : selectedElements) {
                        if (!elementStyles.containsKey(selected)) {
                            elementStyles.put(selected, new LinkedHashMap<String, String>());
                        }

                        final CSSStyleDeclaration styleDeclaration = styleRule.getStyle();

                        for (int j = 0; j < styleDeclaration.getLength(); j++) {
                            final String propertyName = styleDeclaration.item(j);
                            final String propertyValue = styleDeclaration.getPropertyValue(propertyName);
                            final Map<String, String> elementStyle = elementStyles.get(selected);
                            elementStyle.put(propertyName, propertyValue);
                        }

                    }
                }
            }
        }

        /*
 * Apply the style attributes to each element and remove the "class"
 * attribute.
         */
        for (final Map.Entry<Element, Map<String, String>> elementEntry : elementStyles.entrySet()) {
            final Element element = elementEntry.getKey();
            final StringBuilder builder = new StringBuilder();
            for (final Map.Entry<String, String> styleEntry : elementEntry.getValue().entrySet()) {
                builder.append(styleEntry.getKey()).append(":").append(styleEntry.getValue()).append(";");
            }
            builder.append(element.attr("style"));
            element.attr("style", builder.toString());
            // element.removeAttr("class");
        }
        return document.html();

    }
}
