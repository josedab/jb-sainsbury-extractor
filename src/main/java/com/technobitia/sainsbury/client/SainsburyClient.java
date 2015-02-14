package com.technobitia.sainsbury.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.technobitia.sainsbury.exceptions.SainsburyException;
import com.technobitia.sainsbury.extractors.Extractor;
import com.technobitia.sainsbury.extractors.impl.IngredientsExtractor;
import com.technobitia.sainsbury.request.SainsburyRequest;

public class SainsburyClient {
    Extractor ingredientsExtractor = new IngredientsExtractor();
    public SainsburyClient() {
        
    }
    
    public String extractInformation(SainsburyRequest request) throws SainsburyException {
        checkNotNull(request);
        String result = null;

        String url = request.getUrl();
        try {
            Document doc = Jsoup.connect(url).get();
            result = ingredientsExtractor.extract(request, doc);
        } catch (IOException e) {
            throw new SainsburyException("I/O exception when requesting information to url:" + url);
        }
        
        return result;
    }
}
