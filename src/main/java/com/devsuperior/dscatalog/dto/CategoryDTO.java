package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private static final Long serialVersionUID = 1L;
    private Long id;
    private String name;
    public CategoryDTO(){

    }
    public CategoryDTO(Category category){
        this.name = category.getName();
        this.id = category.getId();
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
}