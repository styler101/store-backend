package com.devsuperior.dscatalog.dto;
import com.devsuperior.dscatalog.entities.Category;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.Instant;

public class CategoryDTO implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private String name;

    private Instant createdAt;
    private Instant updatedAt;
    public CategoryDTO(){

    }
    public CategoryDTO(Category category){
        this.name = category.getName();
        this.id = category.getId();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
