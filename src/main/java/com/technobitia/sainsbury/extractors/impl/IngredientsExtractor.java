package com.technobitia.sainsbury.extractors.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.technobitia.sainsbury.extractors.Extractor;
import com.technobitia.sainsbury.request.SainsburyRequest;

public class IngredientsExtractor implements Extractor {

    private static final String INGREDIENTS_SELECTOR = "#ingredients.section";
    private static final String BADGES_SELECTOR = "div.badges";

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
        removeBadges(element);
        convertLinksToAbsoluteUrls(element);
        // addExternalTargetToLinks(element);
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
    }

}
