package com.omael.cookingbook.category;

import com.omael.cookingbook.category.dto.CategoryDTO;
import com.omael.cookingbook.category.request.CategoryCreateRequest;
import com.omael.cookingbook.category.request.CategoryUpdateRequest;
import com.omael.cookingbook.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(categoryService.getAllCategory());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(
            @PathVariable("categoryId") Integer categoryId) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(categoryService.getCategory(categoryId));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.addCategory(categoryCreateRequest));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @PathVariable("categoryId") Integer categoryId,
            @RequestBody CategoryUpdateRequest categoryUpdateRequest) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.updateCategory(categoryId, categoryUpdateRequest));
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Integer categoryId) throws ResourceNotFoundException {
        categoryService.deleteCategory(categoryId);
    }
}
