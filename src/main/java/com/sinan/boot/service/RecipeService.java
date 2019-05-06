package com.sinan.boot.service;

import com.sinan.boot.dao.RecipeDao;
import com.sinan.boot.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private RecipeDao recipeDao;

    public RecipeService(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public long createRecipe(Recipe recipe) {
        return recipeDao.createRecipe(recipe);
    }

    public List getRecipes(){
        return recipeDao.getAll();
    }
}
