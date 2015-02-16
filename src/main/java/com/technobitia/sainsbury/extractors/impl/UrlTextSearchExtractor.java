package com.technobitia.sainsbury.extractors.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;
import com.technobitia.sainsbury.extractors.ListExtractor;
import com.technobitia.sainsbury.request.SainsburyRequest;

public class UrlTextSearchExtractor implements ListExtractor {

    private static final String URL_SELECTOR = "#content > div.section > ul > li > div > div > h3 > a[href]";

    public List<String> extract(SainsburyRequest request, Document doc) {
        checkNotNull(request);
        checkNotNull(doc);
        
        List<String> result = Lists.newLinkedList();
        Elements resultElements = doc.select(URL_SELECTOR);

        for (Element resultElement : resultElements) {
            String absUrl = resultElement.absUrl("href");
            result.add(absUrl);
        }

        return result;
    }

}
