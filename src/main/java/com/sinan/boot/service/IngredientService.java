package com.sinan.boot.service;

import com.sinan.boot.dao.IngredientDao;
import com.sinan.boot.model.Ingredient;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    // Pour remove le autowired, on met le DAO en construteur, Spring boot s'occupe du reste
    // @Autowired
    private IngredientDao ingredientDao;

    public IngredientService(IngredientDao ingredientDao){
        this.ingredientDao = ingredientDao;
    }


    public List getIngredients() {
        return ingredientDao.getAll();
    }

    public long postIngredient(String newIngredientName) {
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName(newIngredientName);
        return ingredientDao.createIngredient(newIngredient);
    }

    public Ingredient getIngredientById(long ingredientId) {
        return ingredientDao.getIngredientById(ingredientId);
    }

    public Ingredient updateIngredientById(Ingredient ingredient) {
        return ingredientDao.updateIngredient(ingredient);
    }

    public long deleteIngredient(Ingredient ingredient) {
        return ingredientDao.deleteIngredient(ingredient);
    }

    public void deleteIngredientById(long ingredientId) {
        ingredientDao.deleteIngredientById(ingredientId);
    }


}
