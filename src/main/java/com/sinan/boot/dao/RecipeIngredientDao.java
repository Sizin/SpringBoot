package com.sinan.boot.dao;


import com.sinan.boot.model.Ingredient;
import com.sinan.boot.model.Recipe;
import com.sinan.boot.model.RecipeIngredient;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RecipeIngredientDao {
    private static final String GET_ALL_RECIPES_INGREDIENT = "from RecipeIngredient";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public long createRecipeIngredient(RecipeIngredient recipeIngredient) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(recipeIngredient);
        return recipeIngredient.getId();
    }

    public RecipeIngredient loadRecipeIngredientById(long recipeIngredientId){
        Session session = entityManager.unwrap(Session.class);
        return session.load(RecipeIngredient.class, recipeIngredientId);
    }


    public List getAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(GET_ALL_RECIPES_INGREDIENT).list();
    }




}
