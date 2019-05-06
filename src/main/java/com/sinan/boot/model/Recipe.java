package com.sinan.boot.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Recipe {
    private long id;
    private String name;
    private String picture;
    private String description;
    private Set<RecipeIngredient> recipeIngredient;
    private Set<String> instructions;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    @Column
    @OneToMany
    public Set<RecipeIngredient> getRecipeIngredient() {
        return recipeIngredient;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRecipeIngredient(Set<RecipeIngredient> recipeIngredient) {
        this.recipeIngredient = recipeIngredient;
    }

    @ElementCollection(targetClass=String.class)
    public Set<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(Set<String> instructions) {
        this.instructions = instructions;
    }
}
