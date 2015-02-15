package com.technobitia.sainsbury.extractors.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.technobitia.sainsbury.extractors.Extractor;
import com.technobitia.sainsbury.request.SainsburyRequest;

public class RecipeMethodExtractor implements Extractor {

    private static final String RECIPE_METHOD_SELECTOR = "#information.section";

    public String extract(SainsburyRequest request, Document doc) {
        checkNotNull(request);
        checkNotNull(doc);
        
        String result = null;
        Elements results = doc.select(RECIPE_METHOD_SELECTOR);
        Element infoBox = results.first();
        if (infoBox != null) {
            if (request.getContentType().equalsIgnoreCase("html")) {
                result = infoBox.toString();
            } else {
                result = infoBox.text();
            }
        }

        return result;
    }
    

}
