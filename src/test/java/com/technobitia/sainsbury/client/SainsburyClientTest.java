package com.technobitia.sainsbury.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.technobitia.sainsbury.exceptions.SainsburyException;

@RunWith(MockitoJUnitRunner.class)
public class SainsburyClientTest {

    @InjectMocks
    private SainsburyClient sainsburyClient;

    @Test(expected = NullPointerException.class)
    public void whenExtractingInformation_givenNullRequest_thenThrowNPE() throws SainsburyException {
        sainsburyClient.extractInformation(null);
    }
}
