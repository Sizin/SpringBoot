package com.sinan.boot.dao;


import com.sinan.boot.model.Recipe;
import com.sinan.boot.model.RecipeIngredient;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RecipeDao {
    private static final String GET_ALL_RECIPES = "from Recipe";

    @PersistenceContext
    private EntityManager entityManager;

    private RecipeIngredientDao recipeIngredientDao;

    public RecipeDao (RecipeIngredientDao recipeIngredientDao) {
        this.recipeIngredientDao = recipeIngredientDao;
    }


    @Transactional
    public long createRecipe(Recipe recipe) {
        Session session = entityManager.unwrap(Session.class);

        Set<RecipeIngredient> recipeIngredients = new HashSet<>();

        // Si pas de Set RecipeIngredient alors on set le Set null
        if (recipe.getRecipeIngredient() == null){
            recipe.setRecipeIngredient(recipeIngredients);
        }else{
            // Sinon on parcours tous les RecipeIngredient
            recipe.getRecipeIngredient().stream().forEach( recipeIngredient -> {
                // If Id is present (!= 0)
                if(recipeIngredient.getId() != 0) {
                    recipeIngredients.add(recipeIngredient);
                    // If the RecipeIngredient exist
                    if(session.get(RecipeIngredient.class, recipeIngredient.getId()).getId() != 0) {
                        session.load(RecipeIngredient.class, recipeIngredient.getId());
                    }else {
                        recipeIngredientDao.createRecipeIngredient(recipeIngredient);
                    }
                }
            });
        }
        recipe.setRecipeIngredient(recipeIngredients);
        session.persist(recipe);
        return recipe.getId();
    }

    public List getAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(GET_ALL_RECIPES).list();
    }




}
