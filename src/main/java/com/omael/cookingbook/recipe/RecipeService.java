package com.omael.cookingbook.recipe;

import com.omael.cookingbook.category.Category;
import com.omael.cookingbook.category.CategoryRepository;
import com.omael.cookingbook.exception.RequestValidationException;
import com.omael.cookingbook.exception.ResourceNotFoundException;
import com.omael.cookingbook.recipe.dao.RecipeDao;
import com.omael.cookingbook.recipe.dto.RecipeDTO;
import com.omael.cookingbook.recipe.dto.RecipeDTOMapper;
import com.omael.cookingbook.recipe.request.CreateRecipeRequest;
import com.omael.cookingbook.recipe.request.UpdateRecipeRequest;
import com.omael.cookingbook.user.User;
import com.omael.cookingbook.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeDao recipeDao;
    private final UserRepository userRepository;
    private final RecipeDTOMapper recipeDTOMapper;

    public RecipeService(CategoryRepository categoryRepository, RecipeRepository recipeRepository, RecipeDao recipeDao, UserRepository userRepository, RecipeDTOMapper recipeDTOMapper) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.recipeDao = recipeDao;
        this.userRepository = userRepository;
        this.recipeDTOMapper = recipeDTOMapper;
    }

    public List<RecipeDTO> getAllRecipe() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeDTOMapper)
                .collect(Collectors.toList());
    }

    public RecipeDTO getRecipeById(Integer recipeId) throws ResourceNotFoundException {
        return recipeRepository.findById(recipeId)
                .map(recipeDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Recipe with id [%s] not found".formatted(recipeId))
                );
    }

    public RecipeDTO addRecipe(String email, CreateRecipeRequest createRecipeRequest) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                "User with email [%s] not found".formatted(email))
        );

        Category category = categoryRepository.findById(createRecipeRequest.category())
                .orElseThrow(() -> new ResourceNotFoundException(
                "User with email [%s] not found".formatted(createRecipeRequest.category()))
        );

        Recipe recipe = new Recipe(
                createRecipeRequest.name(),
                createRecipeRequest.description(),
                createRecipeRequest.recipeLevel(),
                createRecipeRequest.prepareTime(),
                createRecipeRequest.cookingTime(),
                createRecipeRequest.totalTime(),
               user,
                category
        ) ;

     Recipe recipeSaved =   recipeRepository.save(recipe);
       return new RecipeDTO(
               recipeSaved.getId(),
               recipeSaved.getName(),
               recipeSaved.getDescription(),
               recipeSaved.getRecipeLevel(),
               recipeSaved.getPrepareTime(),
               recipeSaved.getCookingTime(),
               recipeSaved.getTotalTime(),
               recipeSaved.getUser().getId(),
               recipeSaved.getCategory().getId()
       );
    }

    public RecipeDTO editRecipe(String email, Integer RecipeId, UpdateRecipeRequest updateRecipeRequest) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                "User with email [%s] not found".formatted(email))
        );

        Category category = categoryRepository.findById(updateRecipeRequest.category())
                .orElseThrow(() -> new ResourceNotFoundException(
                "Category with id [%d] not found".formatted(updateRecipeRequest.category()))
        );

        Recipe recipe = recipeRepository.findById(RecipeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Recipe with id [%d] not found".formatted(RecipeId)));

        if (!Objects.equals(user.getId(), recipe.getUser().getId())) {
            throw new ResourceNotFoundException("Recipe with id [%d] not found".formatted(RecipeId));
        }

        boolean changes = false;
        if (updateRecipeRequest.name() != null && !updateRecipeRequest.name().equals(recipe.getName())) {
            recipe.setName(updateRecipeRequest.name());
            changes = true;
        }

        if (updateRecipeRequest.description() != null && !updateRecipeRequest.description().equals(recipe.getDescription())) {
            recipe.setDescription(updateRecipeRequest.description());
            changes = true;
        }

        if (updateRecipeRequest.recipeLevel() != null && !updateRecipeRequest.recipeLevel().equals(recipe.getRecipeLevel())) {
            recipe.setRecipeLevel(updateRecipeRequest.recipeLevel());
            changes = true;
        }

        if (updateRecipeRequest.prepareTime() != null && !updateRecipeRequest.prepareTime().equals(recipe.getPrepareTime())) {
            recipe.setPrepareTime(updateRecipeRequest.prepareTime());
            changes = true;
        }

        if (updateRecipeRequest.totalTime() != null && !updateRecipeRequest.totalTime().equals(recipe.getTotalTime())) {
            recipe.setTotalTime(updateRecipeRequest.totalTime());
            changes = true;
        }

        if (updateRecipeRequest.cookingTime() != null && !updateRecipeRequest.cookingTime().equals(recipe.getCookingTime())) {
            recipe.setCookingTime(updateRecipeRequest.cookingTime());
            changes = true;
        }

        if (!updateRecipeRequest.category().equals(recipe.getCategory().getId())) {
            recipe.setCategory(category);
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }

        Recipe recipeSaved =   recipeRepository.save(recipe);

        return new RecipeDTO(
                recipeSaved.getId(),
                recipeSaved.getName(),
                recipeSaved.getDescription(),
                recipeSaved.getRecipeLevel(),
                recipeSaved.getPrepareTime(),
                recipeSaved.getCookingTime(),
                recipeSaved.getTotalTime(),
                recipeSaved.getUser().getId(),
                recipeSaved.getCategory().getId()
        );
    }

    public void deleteRecipe(String email, Integer recipeId) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with email [%s] not found".formatted(email))
                );

        Recipe recipe = recipeDao.findRecipeByIdAndUserId(recipeId, user.getId());
        if (recipe == null) {
            throw new ResourceNotFoundException(
                    "Recipe with id [%d] not found".formatted(recipeId));
        }

        recipeRepository.deleteById(recipe.getId());
    }

}
