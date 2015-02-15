package com.technobitia.sainsbury.examples;

import com.technobitia.sainsbury.client.SainsburyClient;
import com.technobitia.sainsbury.exceptions.SainsburyException;
import com.technobitia.sainsbury.model.Recipe;
import com.technobitia.sainsbury.request.SainsburyRequest;

public class IngredientsExtractorApp {

    public static void main(String[] args) throws SainsburyException {
        String url = "http://www.sainsburys.co.uk/shop/gb/groceries/find-recipes/recipes/chicken-poultry-and-game/roast-chicken#ingredients";
        SainsburyRequest request = new SainsburyRequest.Builder(url)
                                                        .withContentType("html")
                                                        .build();
        SainsburyClient client = new SainsburyClient();
        Recipe recipe = client.extractInformation(request);
        String text = recipe.getIngredientsToBuy();
        System.out.println(text);

    }

}
