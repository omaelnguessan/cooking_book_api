package com.omael.cookingbook.ingredient;

import com.omael.cookingbook.exception.DuplicateResourceException;
import com.omael.cookingbook.exception.RequestValidationException;
import com.omael.cookingbook.exception.ResourceNotFoundException;
import com.omael.cookingbook.ingredient.dao.IngredientDao;
import com.omael.cookingbook.ingredient.dto.IngredientDTO;
import com.omael.cookingbook.ingredient.dto.IngredientDTOMapper;
import com.omael.cookingbook.ingredient.request.CreateIngredientRequest;
import com.omael.cookingbook.ingredient.request.UpdateIngredientRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientDao ingredientDao;
    private final IngredientDTOMapper ingredientDTOMapper;

    public IngredientService(IngredientRepository ingredientRepository, IngredientDao ingredientDao, IngredientDTOMapper ingredientDTOMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientDao = ingredientDao;
        this.ingredientDTOMapper = ingredientDTOMapper;
    }

    public List<IngredientDTO> getAllIngredients() {
        return ingredientRepository.findAll()
                .stream()
                .map(ingredientDTOMapper).collect(Collectors.toList());
    }

    public IngredientDTO findById(Integer ingredientId) throws ResourceNotFoundException {
        return ingredientRepository.findById(ingredientId)
                .map(ingredientDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Ingredient with id [%s] not found".formatted(ingredientId))
        );
    }

    public IngredientDTO addIngredient(CreateIngredientRequest createIngredient) {
        boolean existIngredient = ingredientDao.existsByName(createIngredient.name());
        if (existIngredient) {
            throw new DuplicateResourceException(
                    "name already taken"
            );
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setName(createIngredient.name());
        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        return new IngredientDTO(
                savedIngredient.getId(),
                savedIngredient.getName(),
                savedIngredient.getCreatedAt(),
                savedIngredient.getUpdatedAt()
        );
    }

    public IngredientDTO updateIngredient(Integer ingredientId, UpdateIngredientRequest updateIngredient) throws ResourceNotFoundException {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Ingredient with id [%s] not found".formatted(ingredientId)));

        boolean changes = false;
        if (updateIngredient.name() != null & !ingredient.getName().equals(updateIngredient.name())) {
            boolean existIngredient = ingredientDao.existsByName(updateIngredient.name());
            if (existIngredient) {
                throw new DuplicateResourceException(
                        "name already taken"
                );
            }
            ingredient.setName(updateIngredient.name());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }

        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return new IngredientDTO(
                savedIngredient.getId(),
                savedIngredient.getName(),
                savedIngredient.getCreatedAt(),
                savedIngredient.getUpdatedAt()
        );
    }

    public void deleteIngredient(Integer ingredientId) throws ResourceNotFoundException {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ingredient with id [%s] not found".formatted(ingredientId)));
        ingredientRepository.delete(ingredient);
    }
}
