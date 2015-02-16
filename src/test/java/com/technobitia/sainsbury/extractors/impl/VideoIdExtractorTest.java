package com.technobitia.sainsbury.extractors.impl;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.technobitia.sainsbury.exceptions.SainsburyException;
import com.technobitia.sainsbury.request.SainsburyRequest;

@RunWith(MockitoJUnitRunner.class)
public class VideoIdExtractorTest {

    @Mock
    private Document documentMock;
    
    @Mock
    private SainsburyRequest sainsburyRequestMock;
    
    @InjectMocks
    private VideoIdExtractor videoIdExtractor;

    @Test(expected = NullPointerException.class)
    public void whenExtractingInformation_givenNullRequest_thenThrowNPE() throws SainsburyException {
        videoIdExtractor.extract(null, documentMock);
    }

    @Test(expected = NullPointerException.class)
    public void whenExtractingInformation_givenNullDocument_thenThrowNPE() throws SainsburyException {
        videoIdExtractor.extract(sainsburyRequestMock, null);
    }
}
