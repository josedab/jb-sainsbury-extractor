package com.technobitia.sainsbury.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.common.collect.Lists;
import com.technobitia.sainsbury.exceptions.SainsburyException;
import com.technobitia.sainsbury.extractors.Extractor;
import com.technobitia.sainsbury.extractors.ListExtractor;
import com.technobitia.sainsbury.extractors.impl.IngredientsExtractor;
import com.technobitia.sainsbury.extractors.impl.IngredientsListExtractor;
import com.technobitia.sainsbury.extractors.impl.NutritionFactsExtractor;
import com.technobitia.sainsbury.extractors.impl.RecipeMethodExtractor;
import com.technobitia.sainsbury.extractors.impl.TitleExtractor;
import com.technobitia.sainsbury.extractors.impl.UrlTextSearchExtractor;
import com.technobitia.sainsbury.extractors.impl.VideoIdExtractor;
import com.technobitia.sainsbury.model.Recipe;
import com.technobitia.sainsbury.request.SainsburyRequest;

public class SainsburyClient {
    private Extractor titleExtractor;
    private Extractor ingredientsExtractor;
    private Extractor ingredientListExtractor;
    private Extractor nutritionFactsExtractor;
    private Extractor stepsRecipeExtractor;
    private Extractor videoIdExtractor;
    
    public SainsburyClient() {
        titleExtractor = new TitleExtractor();
        ingredientsExtractor = new IngredientsExtractor();
        ingredientListExtractor = new IngredientsListExtractor();
        nutritionFactsExtractor = new NutritionFactsExtractor();
        stepsRecipeExtractor = new RecipeMethodExtractor();
        videoIdExtractor = new VideoIdExtractor();
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
            String videoIdentifier = videoIdExtractor.extract(request, doc);
            
            recipe = new Recipe.Builder(url)
                               .withVideoId(videoIdentifier)
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
    
    public List<String> getRecipeUrlsFromUrl(SainsburyRequest request) throws SainsburyException {
        checkNotNull(request);

        List<String> urls = Lists.newLinkedList();
        String url = request.getUrl();
        try {
            Document doc = Jsoup.connect(url).get();
            ListExtractor urlExtractor = new UrlTextSearchExtractor();
            urls = urlExtractor.extract(request, doc);
            
        } catch (IOException e) {
            throw new SainsburyException("I/O exception when requesting information to url:" + url);
        }
        return urls;
    }
}
