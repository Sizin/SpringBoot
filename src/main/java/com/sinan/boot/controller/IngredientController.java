package com.sinan.boot.controller;

import com.sinan.boot.dao.IngredientDao;
import com.sinan.boot.model.Ingredient;
import com.sinan.boot.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @ResponseBody
    public List getAll() {
        //return this.ingredientDao.getAll();
        List ingredients = ingredientService.getIngredients();
        return ingredients;
    }


    @GetMapping("/{id}")
    @ResponseBody
    public Ingredient getById(@PathVariable("id") long id)
    {
        return ingredientService.getIngredientById(id);
    }


    @PostMapping
    @ResponseBody
    public long create(@RequestParam("name") String name) {
        if (!name.isEmpty()) {
            long newIngredientId = ingredientService.postIngredient(name);
            return newIngredientId;
        }else {
            return 0;
        }
    }

    // RequestBody param que tu tape a la main ds le Swagger (ex : JSON)
    // Path Variable quand c'est dans l'url  (ex: ingredient/{id})
    // RequestPAram ?param=value&
    @PutMapping
    @ResponseBody
    public Ingredient update(@RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredientById(ingredient);
    }

    @DeleteMapping
    @ResponseBody
    public long delete(@RequestBody Ingredient ingredient) {
        return  ingredientService.deleteIngredient(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteById(@PathVariable("id") long id) {
        ingredientService.deleteIngredientById(id);
    }

}
