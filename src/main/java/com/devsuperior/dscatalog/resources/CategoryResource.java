package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/categories")
public class CategoryResource {
    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> categories = new ArrayList<Category>();
        categories.add( new Category(1L, "Electronics"));
        categories.add( new Category(2L, "Garden"));
        categories.add( new Category(3L, "House Care"));
        return ResponseEntity.ok().body(categories);
    }
}
