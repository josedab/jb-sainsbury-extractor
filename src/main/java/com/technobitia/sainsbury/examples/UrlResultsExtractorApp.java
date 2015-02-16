package com.technobitia.sainsbury.examples;

import java.util.List;

import com.technobitia.sainsbury.client.SainsburyClient;
import com.technobitia.sainsbury.exceptions.SainsburyException;
import com.technobitia.sainsbury.request.SainsburyRequest;

public class UrlResultsExtractorApp {

    public static void main(String[] args) throws SainsburyException {
        String url = "http://www.sainsburys.co.uk/shop/gb/groceries/RecipesFreeTextSearch?langId=44&storeId=10151&catalogId=10122&top=Y&recipesCatalogId=10053&recipesSearch=true&recipesPage=true&searchTerm=video";
        SainsburyRequest request = new SainsburyRequest.Builder(url)
                                                        .withContentType("html")
                                                        .build();
        SainsburyClient client = new SainsburyClient();
        List<String> urls = client.getRecipeUrlsFromUrl(request);
        System.out.println(urls);

    }

}
