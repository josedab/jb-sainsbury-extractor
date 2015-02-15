package com.technobitia.sainsbury.extractors.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.technobitia.sainsbury.extractors.Extractor;

public class IngredientsListExtractor extends AbstractExtractor implements Extractor {

    private static final String INGREDIENTS_LIST_SELECTOR = "#ingredientsList";
    private static final String LINKS_SELECTOR = ".links";

    @Override
    protected String getSectionSelector() {
        return INGREDIENTS_LIST_SELECTOR;
    }

    @Override
    protected Element postProcessElement(Element element) {
        removeLinks(element);
        return element;
    }

    /**
     * Removes links from the ingredients list
     * 
     * @param element
     */
    private void removeLinks(Element element) {
        Elements badges = element.select(LINKS_SELECTOR);
        for (Element badgeElement : badges) {
            badgeElement.remove();
        }
    }

}
