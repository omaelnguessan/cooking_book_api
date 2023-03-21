package com.omael.cookingbook.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omael.cookingbook.category.Category;
import com.omael.cookingbook.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private RecipeLevel recipeLevel;
    private Integer prepareTime;
    private Integer cookingTime;
    private Integer totalTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonIgnore
    private LocalDateTime updatedAt;

    public Recipe() {}

    public Recipe(Integer id, String name, String description, RecipeLevel recipeLevel, Integer prepareTime, User user, Category category, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.recipeLevel = recipeLevel;
        this.prepareTime = prepareTime;
        this.user = user;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Recipe(String name, String description, RecipeLevel recipeLevel, Integer prepareTime, Integer cookingTime, Integer totalTime, User user, Category category) {
        this.name = name;
        this.description = description;
        this.recipeLevel = recipeLevel;
        this.prepareTime = prepareTime;
        this.cookingTime = cookingTime;
        this.totalTime = totalTime;
        this.user = user;
        this.category = category;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecipeLevel getRecipeLevel() {
        return recipeLevel;
    }

    public void setRecipeLevel(RecipeLevel recipeLevel) {
        this.recipeLevel = recipeLevel;
    }

    public Integer getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(Integer prepareTime) {
        this.prepareTime = prepareTime;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe recipe)) return false;
        return Objects.equals(id, recipe.id) && Objects.equals(name, recipe.name) && Objects.equals(description, recipe.description) && recipeLevel == recipe.recipeLevel && Objects.equals(prepareTime, recipe.prepareTime) && Objects.equals(cookingTime, recipe.cookingTime) && Objects.equals(totalTime, recipe.totalTime) && Objects.equals(user, recipe.user) && Objects.equals(category, recipe.category) && Objects.equals(createdAt, recipe.createdAt) && Objects.equals(updatedAt, recipe.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, recipeLevel, prepareTime, cookingTime, totalTime, user, category, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", recipeLevel=" + recipeLevel +
                ", prepareTime=" + prepareTime +
                ", cookingTime=" + cookingTime +
                ", totalTime=" + totalTime +
                ", user=" + user +
                ", category=" + category +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
