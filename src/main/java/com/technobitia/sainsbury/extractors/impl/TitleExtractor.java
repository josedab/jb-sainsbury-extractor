package com.technobitia.sainsbury.extractors.impl;

import org.jsoup.nodes.Element;

import com.technobitia.sainsbury.extractors.Extractor;

public class TitleExtractor extends AbstractExtractor implements Extractor {

    private static final String TITLE_SELECTOR = "h1.fn";

    @Override
    protected String getSectionSelector() {
        return TITLE_SELECTOR;
    }

    @Override
    protected Element postProcessElement(Element element) {
        return element;
    }

}
