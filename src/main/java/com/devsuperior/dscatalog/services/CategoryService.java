package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;


import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true) // o próprio framework vai garantir as operações de transações
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
        Page<Category> categories = repository.findAll(pageRequest); // O pageable já é uma stream do java 8
        return categories.map(x -> new CategoryDTO(x));
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) throws ResourceNotFoundException {
        Optional<Category> object = repository.findById(id); // Optional é uma abordagem para garantir que o valor não venha nulo
        Category category = object.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity = repository.save(entity); // representação da entidade salva
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category entity = repository.getOne(id); // Pega o objeto sem precisar ir no banco de dados. O objeto fica representado na memória
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    public void delete(Long id){
       try{
          repository.deleteById(id);
       }catch(EmptyResultDataAccessException e){
           e.printStackTrace();
           throw new ResourceNotFoundException("Id not found!" + id);
       }catch( DataIntegrityViolationException e){
            throw new DataBaseException("Integrate violioation");
       }
    }

}
