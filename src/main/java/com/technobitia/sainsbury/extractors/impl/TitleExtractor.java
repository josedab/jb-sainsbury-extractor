package com.technobitia.sainsbury.extractors.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.technobitia.sainsbury.extractors.Extractor;
import com.technobitia.sainsbury.request.SainsburyRequest;

public class TitleExtractor extends AbstractExtractor implements Extractor {

    private static final String TITLE_SELECTOR = "h1.fn";

    @Override
    public String extract(SainsburyRequest request, Document doc) {
        checkNotNull(request);
        checkNotNull(doc);
        
        String result = null;
        Elements results = doc.select(getSectionSelector());
        Element infoBox = results.first();
        if (infoBox != null) {
            infoBox = postProcessElement(infoBox);
            result = infoBox.text();
        }

        return result;
    }
    
    @Override
    protected String getSectionSelector() {
        return TITLE_SELECTOR;
    }

    @Override
    protected Element postProcessElement(Element element) {
        return element;
    }

}
