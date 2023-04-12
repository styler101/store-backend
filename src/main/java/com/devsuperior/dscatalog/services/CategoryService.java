package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }
    @Transactional(readOnly = true) // o próprio framework vai garantir as operações de transações
    public List<CategoryDTO> findAll(){
        List<Category> categoryList = repository.findAll();
        return categoryList.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }
}
