package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAllPaged(
        @RequestParam(value="page", defaultValue = "0") Integer page,
        @RequestParam(value="linerPerPage", defaultValue = "12") Integer linesPerPage,
        @RequestParam(value="direction", defaultValue="ASC") String  direction,
        @RequestParam(value="orderBy", defaultValue="name") String orderBy
        ) {
        PageRequest pageable = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ProductDTO> products = service.findAll(pageable);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        ProductDTO  product = service.findById(id);
        return ResponseEntity.ok().body(product);
    }


}
