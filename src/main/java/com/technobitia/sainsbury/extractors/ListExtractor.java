package com.technobitia.sainsbury.extractors;

import java.util.List;

import org.jsoup.nodes.Document;

import com.technobitia.sainsbury.request.SainsburyRequest;

public interface ListExtractor {
    public List<String> extract(SainsburyRequest request, Document doc);
}
