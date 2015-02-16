package com.technobitia.sainsbury.extractors.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.technobitia.sainsbury.extractors.Extractor;

public class IngredientsExtractor extends AbstractExtractor implements Extractor {

    private static final String INGREDIENTS_SELECTOR = "#ingredients.section";
    private static final String BADGES_SELECTOR = "div.badges";

    @Override
    protected String getSectionSelector() {
        return INGREDIENTS_SELECTOR;
    }

    @Override
    protected Element postProcessElement(Element element) {
        removeBadges(element);
        convertLinksToAbsoluteUrls(element);
        convertActionsToAbsoluteUrls(element);
        return element;
    }

    /**
     * Removes badges from the ingredients list
     * @param element
     */
    private void removeBadges(Element element) {
        Elements badges = element.select(BADGES_SELECTOR);
        for (Element badgeElement : badges) {
            badgeElement.remove();
        }
    }

    /**
     * Replaces relative urls with absolute urls.
     * This will let the user click on any link even if they are not on wikipedia
     * @param element
     */
    private void convertLinksToAbsoluteUrls(Element element) {
        Elements urls = element.select("a[href]");
        for (Element urlElement : urls) {
            urlElement.attr("href", urlElement.absUrl("href"));
        }
        
        Elements images = element.select("img[src]");
        for (Element imageElement : images) {
            imageElement.attr("src", imageElement.absUrl("src"));
        }
    }
    
    private void convertActionsToAbsoluteUrls(Element element) {
        Elements formActions = element.select("form[action=OrderItemAdd]");
        for (Element formAction : formActions) {
            formAction.attr("action", formAction.absUrl("action"));
        }
    }

}
