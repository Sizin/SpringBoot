package com.sinan.boot.dao;

import com.sinan.boot.model.Ingredient;
import io.swagger.models.auth.In;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.sinan.boot.model.Ingredient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.SessionScope;


//10.1.149:8080/swagger-ui.html
@Repository
public class IngredientDao {
    private static final String GET_ALL_INGREDIENTS = "from Ingredient";
    private static final String DELETE_INGREDIENT_BY_ID = "delete Ingredient where id=:id";


    @PersistenceContext
    private EntityManager entityManager;

    public List getAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(GET_ALL_INGREDIENTS).list();
    }



    /*
        @Transactional Sert à oubrir ou fermer la session  équivaut au lignes en dessous

        session.beginTransaction();
        try {
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    */
    @Transactional
    public long createIngredient(Ingredient ingredient) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(ingredient);
        // Ou alors :
        // entityManager.persist(ingredient);
        // Dans ce cas là on délègue la gestion de la session à EntityManager qui se débrouille bien aussi
        // Mais c'est bizzare de demander a EntityManager de persist, par soucis de clarté on récupère nous mm
        // la session.
        return ingredient.getId();
    }

    public Ingredient getIngredientById(long id) {
        Session session = entityManager.unwrap(Session.class);
        // return Optional.ofNullable(session.get(Ingredient.class, id));
        return session.get(Ingredient.class, id);
    }

    @Transactional
    public Ingredient updateIngredient(Ingredient ingredientToUpdate) {
        Session session = entityManager.unwrap(Session.class);
        session.update(ingredientToUpdate);
        return ingredientToUpdate;
    }
    // A part pour les get que des voids
    @Transactional
    public long deleteIngredient(Ingredient ingredient) {
        Session session = entityManager.unwrap(Session.class);
        // We have to load/attach the Ingredient/Object to the Hibernate Session factory first!!!!
        Ingredient ingredientToDelete = session.load(Ingredient.class, ingredient.getId());
        session.delete(ingredientToDelete);
        return ingredientToDelete.getId();
    }

    @Transactional
    public void deleteIngredientById(long ingredient) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(getIngredientById(ingredient));
    }

}

