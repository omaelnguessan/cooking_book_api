package com.omael.cookingbook.recipe;

import com.omael.cookingbook.exception.ResourceNotFoundException;
import com.omael.cookingbook.recipe.dto.RecipeDTO;
import com.omael.cookingbook.recipe.request.CreateRecipeRequest;
import com.omael.cookingbook.recipe.request.UpdateRecipeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipe() {
        return ResponseEntity.accepted()
                .body(recipeService.getAllRecipe());
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> getRecipe(
            @PathVariable("recipeId") Integer id
    ) throws ResourceNotFoundException {
        return ResponseEntity.accepted()
                .body(recipeService.getRecipeById(id));
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CreateRecipeRequest createRecipeRequest
    ) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recipeService.addRecipe(userDetails.getUsername(), createRecipeRequest));
    }

    @PutMapping("/{recipeId}")
    public  ResponseEntity<RecipeDTO> updateRecipe(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("recipeId") Integer id,
            @RequestBody UpdateRecipeRequest updateRecipeRequest
            ) throws ResourceNotFoundException {
        return  ResponseEntity
                .ok(recipeService.editRecipe(userDetails.getUsername(), id, updateRecipeRequest));
    }

    @DeleteMapping("/{recipeId}")
    public void deleteRecipe(
            @AuthenticationPrincipal UserDetails userDetails,
                               @PathVariable("recipeId") Integer id
    ) throws ResourceNotFoundException  {
        recipeService.deleteRecipe(userDetails.getUsername(), id);
    }
}
