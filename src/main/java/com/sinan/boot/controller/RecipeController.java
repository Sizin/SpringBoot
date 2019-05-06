package com.sinan.boot.controller;

import com.sinan.boot.model.Recipe;
import com.sinan.boot.model.RecipeIngredient;
import com.sinan.boot.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @ResponseBody
    public long create(@RequestBody Recipe recipe) {

        if (recipe.getRecipeIngredient() == null) {
            recipe.setRecipeIngredient(new HashSet<RecipeIngredient>());
        }

        return recipeService.createRecipe(recipe);
    }

    @GetMapping
    @ResponseBody
    public List getAll() {
        List recipes = recipeService.getRecipes();
        return recipes;
    }


}
