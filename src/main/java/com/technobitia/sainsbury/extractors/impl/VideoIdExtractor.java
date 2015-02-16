package com.technobitia.sainsbury.extractors.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.technobitia.sainsbury.extractors.Extractor;
import com.technobitia.sainsbury.request.SainsburyRequest;

public class VideoIdExtractor extends AbstractExtractor implements Extractor {

    private static final String VIDEO_ID_SELECTOR = ".youtube-player";

    @Override
    public String extract(SainsburyRequest request, Document doc) {
        checkNotNull(request);
        checkNotNull(doc);
        
        String result = null;
        Elements results = doc.select(getSectionSelector());
        Element infoBox = results.first();
        if (infoBox != null) {
            result = extractVideoId(infoBox);
        }

        return result;
    }
    
    private String extractVideoId(Element infoBox) {
        String videoId = null;
        String iframeSrc = infoBox.attr("src");
        String pattern = "http://www.youtube.com/embed/(.*?)\\?.*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(iframeSrc);
        if (m.find( )) {
           videoId = m.group(1);
        }
        return videoId;
    }

    @Override
    protected String getSectionSelector() {
        return VIDEO_ID_SELECTOR;
    }

    @Override
    protected Element postProcessElement(Element element) {
        return element;
    }

}
