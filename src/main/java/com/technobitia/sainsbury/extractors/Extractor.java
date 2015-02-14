package com.technobitia.sainsbury.extractors;

import org.jsoup.nodes.Document;

import com.technobitia.sainsbury.request.SainsburyRequest;

public interface Extractor {
    public String extract(SainsburyRequest request, Document doc);
}
