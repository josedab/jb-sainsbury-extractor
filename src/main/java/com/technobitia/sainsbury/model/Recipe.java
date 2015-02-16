package com.technobitia.sainsbury.model;


public class Recipe {
    private String url;
    private String title;
    private String ingredientsToBuy;
    private String ingredientsList;
    private String nutritionFacts;
    private String stepsToCook;
    private String videoId;
    
    private Recipe(Builder builder) {
        url = builder.url;
        title = builder.title;
        ingredientsToBuy = builder.ingredientsToBuy;
        ingredientsList = builder.ingredientsList;
        nutritionFacts = builder.nutritionFacts;
        stepsToCook = builder.stepsToCook;
        videoId = builder.videoId;
    }
    
    public String getUrl() {
        return url;
    }
    public String getTitle() {
        return title;
    }
    public String getIngredientsToBuy() {
        return ingredientsToBuy;
    }
    public String getIngredientsList() {
        return ingredientsList;
    }
    public String getNutritionFacts() {
        return nutritionFacts;
    }
    public String getStepsToCook() {
        return stepsToCook;
    }
    
    public String getVideoId() {
        return videoId;
    }

    public static class Builder {
        private final String url;
        private String title;
        private String ingredientsToBuy;
        private String ingredientsList;
        private String nutritionFacts;
        private String stepsToCook;
        private String videoId;

        public Builder(String url) {
            this.url = url;
        }
        
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }
        
        public Builder withIngredientsToBuy(String ingredientsToBuy) {
            this.ingredientsToBuy = ingredientsToBuy;
            return this;
        }
        
        public Builder withIngredientsList(String ingredientsList) {
            this.ingredientsList = ingredientsList;
            return this;
        }
        
        public Builder withNutritionFacts(String nutritionFacts) {
            this.nutritionFacts = nutritionFacts;
            return this;
        }
        
        public Builder withStepsToCook(String stepsToCook) {
            this.stepsToCook = stepsToCook;
            return this;
        }

        public Builder withVideoId(String videoId) {
            this.videoId = videoId;
            return this;
        }
        public Recipe build() {
            return new Recipe(this);
        }
    }
    
}
