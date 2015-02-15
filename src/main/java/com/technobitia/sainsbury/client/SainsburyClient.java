package com.technobitia.sainsbury.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.technobitia.sainsbury.exceptions.SainsburyException;
import com.technobitia.sainsbury.extractors.Extractor;
import com.technobitia.sainsbury.extractors.impl.IngredientsExtractor;
import com.technobitia.sainsbury.extractors.impl.IngredientsListExtractor;
import com.technobitia.sainsbury.extractors.impl.NutritionFactsExtractor;
import com.technobitia.sainsbury.extractors.impl.RecipeMethodExtractor;
import com.technobitia.sainsbury.extractors.impl.TitleExtractor;
import com.technobitia.sainsbury.model.Recipe;
import com.technobitia.sainsbury.request.SainsburyRequest;

public class SainsburyClient {
    private Extractor titleExtractor;
    private Extractor ingredientsExtractor;
    private Extractor ingredientListExtractor;
    private Extractor nutritionFactsExtractor;
    private Extractor stepsRecipeExtractor;
    
    public SainsburyClient() {
        titleExtractor = new TitleExtractor();
        ingredientsExtractor = new IngredientsExtractor();
        ingredientListExtractor = new IngredientsListExtractor();
        nutritionFactsExtractor = new NutritionFactsExtractor();
        stepsRecipeExtractor = new RecipeMethodExtractor();
    }
    
    public Recipe extractInformation(SainsburyRequest request) throws SainsburyException {
        checkNotNull(request);
        Recipe recipe = null;

        String url = request.getUrl();
        try {
            Document doc = Jsoup.connect(url).get();
            String ingredientsToBuy = ingredientsExtractor.extract(request, doc);
            String title = titleExtractor.extract(request, doc);
            String ingredientsList = ingredientListExtractor.extract(request, doc);
            String nutritionFacts = nutritionFactsExtractor.extract(request, doc);
            String stepsToCook = stepsRecipeExtractor.extract(request, doc);
            
            recipe = new Recipe.Builder(url)
                               .withTitle(title)
                               .withIngredientsList(ingredientsList)
                               .withIngredientsToBuy(ingredientsToBuy)
                               .withNutritionFacts(nutritionFacts)
                               .withStepsToCook(stepsToCook)
                               .build();
        } catch (IOException e) {
            throw new SainsburyException("I/O exception when requesting information to url:" + url);
        }
        
        return recipe;
    }
}
