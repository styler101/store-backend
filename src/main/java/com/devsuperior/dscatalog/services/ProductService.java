package com.devsuperior.dscatalog.services;


import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;



    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(PageRequest page){
        Page<Product> products = repository.findAll(page);
        return products.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
       Optional<Product> object = repository.findById(id);
       Product entity = object.orElseThrow(() -> new ResourceNotFoundException("Id not found" + id));
       return new ProductDTO(entity, entity.getCategories());

    }

    @Transactional
    public ProductDTO create(ProductDTO dto) {
        Product product = new Product();
        copyDtoToEntity(dto, product);
        product = repository.save(product);
        return new ProductDTO(product, product.getCategories());
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try{
            Product entity  = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity, entity.getCategories());
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }

    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e ){
            throw new DataBaseException("Data Base Violation" + e);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found!");
        }
    }


    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDate(dto.getDate());
        entity.setPrice(dto.getPrice());

        entity.getCategories().clear();
        for (CategoryDTO catDto: dto.getCategories()) {
            Category category = categoryRepository.getOne(catDto.getId());
            entity.getCategories().add(category);
        }

    }



}
