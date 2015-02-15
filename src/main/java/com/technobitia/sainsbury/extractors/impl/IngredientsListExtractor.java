package com.technobitia.sainsbury.extractors.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.technobitia.sainsbury.extractors.Extractor;
import com.technobitia.sainsbury.request.SainsburyRequest;

public class IngredientsListExtractor implements Extractor {

    private static final String INGREDIENTS_SELECTOR = "#ingredientsList";
    private static final String LINKS_SELECTOR = ".links";

    public String extract(SainsburyRequest request, Document doc) {
        checkNotNull(request);
        checkNotNull(doc);
        
        String result = null;
        Elements results = doc.select(INGREDIENTS_SELECTOR);
        Element infoBox = results.first();
        if (infoBox != null) {
            infoBox = postProcessElement(infoBox);
            if (request.getContentType().equalsIgnoreCase("html")) {
                result = infoBox.toString();
            } else {
                result = infoBox.text();
            }
        }

        return result;
    }
    
    private Element postProcessElement(Element element) {
        removeLinks(element);
        return element;
    }

    /**
     * Removes links from the ingredients list
     * @param element
     */
    private void removeLinks(Element element) {
        Elements badges = element.select(LINKS_SELECTOR);
        for (Element badgeElement : badges) {
            badgeElement.remove();
        }
    }

}
