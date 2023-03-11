package com.omael.cookingbook.category;


import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean status;

    public Category(Integer id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Category(String name, String description, boolean status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Category() {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return status == category.status && id.equals(category.id) && name.equals(category.name) && description.equals(category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
