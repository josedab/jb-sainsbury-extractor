package com.technobitia.sainsbury.extractors.impl;

import org.jsoup.nodes.Element;

import com.technobitia.sainsbury.extractors.Extractor;

public class NutritionFactsExtractor extends AbstractExtractor implements Extractor {

    private static final String NUTRITION_FACTS_SELECTOR = "#nutrition.section";

    @Override
    protected String getSectionSelector() {
        return NUTRITION_FACTS_SELECTOR;
    }

    @Override
    protected Element postProcessElement(Element element) {
        return element;
    }

}
