package com.devsuperior.dscatalog.services;


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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;



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
       parsedDTOtoEntity(dto, product);
       repository.save(product);
       return new ProductDTO(product);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e ){
            throw new DataBaseException("Id not found" + e);
        }
    }


    private void parsedDTOtoEntity(ProductDTO dto, Product product){
         product.setId(dto.getId());
         product.setName(dto.getName());
         product.setDescription(dto.getDescription());
         product.setDate(dto.getDate());
         product.setImgUrl(dto.getImgUrl());
    }
}
