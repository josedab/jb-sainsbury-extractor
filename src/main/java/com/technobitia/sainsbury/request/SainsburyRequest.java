package com.technobitia.sainsbury.request;

public class SainsburyRequest {
    
    public static final String CONTENT_TYPE = "html";
    
    private final String url;
    private final String contentType;

    private SainsburyRequest(Builder requestBuilder) {
        this.url = requestBuilder.url;
        this.contentType = requestBuilder.contentType;
    }

    public String getUrl() {
        return url;
    }

    public String getContentType() {
        return contentType;
    }

    public static class Builder {
        private final String url;
        private String contentType = CONTENT_TYPE;

        public Builder(String url) {
            this.url = url;
        }
        
        public Builder withContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public SainsburyRequest build() {
            return new SainsburyRequest(this);
        }
    }
}
