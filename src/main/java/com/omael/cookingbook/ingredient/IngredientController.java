package com.omael.cookingbook.ingredient;

import com.omael.cookingbook.exception.ResourceNotFoundException;
import com.omael.cookingbook.ingredient.dto.IngredientDTO;
import com.omael.cookingbook.ingredient.request.CreateIngredientRequest;
import com.omael.cookingbook.ingredient.request.UpdateIngredientRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<IngredientDTO> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{ingredientId}")
    public IngredientDTO getIngredient(
            @PathVariable("ingredientId") Integer ingredientId)
            throws ResourceNotFoundException {
        return ingredientService.findById(ingredientId);
    }

    @PostMapping
    public IngredientDTO addIngredient(
            @RequestBody CreateIngredientRequest createIngredient) {
        return ingredientService.addIngredient(createIngredient);
    }

    @PutMapping("/{ingredientId}")
    public IngredientDTO updateIngredient(
            @PathVariable("ingredientId") Integer ingredientId,
            @RequestBody UpdateIngredientRequest updateIngredient
            ) throws ResourceNotFoundException {
        return ingredientService.updateIngredient(ingredientId, updateIngredient);
    }

    @DeleteMapping("/{ingredientId}")
    public void deleteIngredient(
            @PathVariable("ingredientId") Integer ingredientId
    ) throws ResourceNotFoundException {
        ingredientService.deleteIngredient(ingredientId);
    }
}
