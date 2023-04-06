package com.omael.cookingbook.picture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omael.cookingbook.recipe.Recipe;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name ="picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String path;
    private String name;
    @Column(nullable = true)
    private String description;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean cover;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonIgnore
    private LocalDateTime updatedAt;

    private Picture(Integer id, String path, String name, String description, boolean cover, Recipe recipe, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.description = description;
        this.cover = cover;
        this.recipe = recipe;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Picture() {}

    public static Picture createPicture(Integer id, String path, String name, String description, boolean cover, Recipe recipe, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Picture(id, path, name, description, cover, recipe, createdAt, updatedAt);
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public boolean isCover() {
        return cover;
    }

    public void setCover(boolean cover) {
        this.cover = cover;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
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
        if (!(o instanceof Picture picture)) return false;
        return cover == picture.cover && Objects.equals(id, picture.id) && Objects.equals(path, picture.path) && Objects.equals(name, picture.name) && Objects.equals(description, picture.description) && Objects.equals(recipe, picture.recipe) && Objects.equals(createdAt, picture.createdAt) && Objects.equals(updatedAt, picture.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, name, description, cover, recipe, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cover=" + cover +
                ", recipe=" + recipe +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
