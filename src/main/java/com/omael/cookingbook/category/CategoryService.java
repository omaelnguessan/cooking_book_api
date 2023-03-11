package com.omael.cookingbook.category;

import com.omael.cookingbook.category.dao.CategoryDao;
import com.omael.cookingbook.category.dto.CategoryDTO;
import com.omael.cookingbook.category.dto.CategoryDTOMapper;
import com.omael.cookingbook.category.request.CategoryCreateRequest;
import com.omael.cookingbook.category.request.CategoryUpdateRequest;
import com.omael.cookingbook.exception.DuplicateResourceException;
import com.omael.cookingbook.exception.RequestValidationException;
import com.omael.cookingbook.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;
    private final CategoryDTOMapper categoryDTOMapper;
    private final CategoryRepository categoryRepository;

    public CategoryService(
            CategoryDao categoryDao,
            CategoryDTOMapper categoryDTOMapper,
            CategoryRepository categoryRepository) {
        this.categoryDao = categoryDao;
        this.categoryDTOMapper = categoryDTOMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryDTOMapper)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategory(Integer categoryId) throws ResourceNotFoundException {
        return categoryRepository.findById(categoryId)
                .map(categoryDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Category with id [%s] not found".formatted(categoryId))
                );
    }

    public CategoryDTO addCategory(CategoryCreateRequest categoryCreateRequest) {
        var name = categoryCreateRequest.name();
        if (categoryDao.existsCategoryByName(name)) {
            throw new DuplicateResourceException(
                    "name already taken"
            );
        }

        Category category = new Category(
                categoryCreateRequest.name(),
                categoryCreateRequest.description(),
                categoryCreateRequest.status()
        );

        Category categorySaved = categoryRepository.save(category);
        return new CategoryDTO(
                categorySaved.getId(),
                categorySaved.getName(),
                categorySaved.getDescription(),
                categorySaved.isStatus()
        );

    }

    public void deleteCategory(Integer categoryId) throws ResourceNotFoundException {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException(
                    "Category with id [%s] not found".formatted(categoryId)
            );
        }

        categoryRepository.deleteById(categoryId);
    }

    public CategoryDTO updateCategory(Integer categoryId, CategoryUpdateRequest categoryUpdateRequest) throws ResourceNotFoundException {
     Category category = categoryRepository.findById(categoryId)
             .orElseThrow(() ->new ResourceNotFoundException(
                     "Category with id [%s] not found".formatted(categoryId)
             ));

        boolean changes = false;

        if (categoryUpdateRequest.status() != category.isStatus()) {
            category.setStatus(categoryUpdateRequest.status());
            changes = true;
        }

        if (categoryUpdateRequest.name() != null && !categoryUpdateRequest.name().equals(category.getName())) {
            if (categoryDao.existsCategoryByName(categoryUpdateRequest.name())) {
                throw new DuplicateResourceException(
                        "name already taken"
                );
            }
            category.setName(categoryUpdateRequest.name());
            changes = true;
        }

        if (categoryUpdateRequest.description() != null && !categoryUpdateRequest.description().equals(category.getDescription())) {
            category.setDescription(categoryUpdateRequest.description());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }

        Category categorySaved = categoryRepository.save(category);
        return new CategoryDTO(
                categorySaved.getId(),
                categorySaved.getName(),
                categorySaved.getDescription(),
                categorySaved.isStatus()
        );
    }

}
