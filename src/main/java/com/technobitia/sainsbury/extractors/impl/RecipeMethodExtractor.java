package com.technobitia.sainsbury.extractors.impl;

import org.jsoup.nodes.Element;

import com.technobitia.sainsbury.extractors.Extractor;

public class RecipeMethodExtractor extends AbstractExtractor implements Extractor {

    private static final String RECIPE_METHOD_SELECTOR = "#information.section";

    @Override
    protected String getSectionSelector() {
        return RECIPE_METHOD_SELECTOR;
    }

    @Override
    protected Element postProcessElement(Element element) {
        return element;
    }
    
}
