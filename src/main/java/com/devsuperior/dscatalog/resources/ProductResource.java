package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletRequest;
import java.net.URI;

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
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO  product = service.findById(id);
        return ResponseEntity.ok().body(product);
    }


    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
        ProductDTO product =  service.create(dto);
        URI uri = ServletUriComponentsBuilder.
            fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(product.getId())
            .toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto){
        ProductDTO product = service.update(id, dto);
        return ResponseEntity.ok().body(product);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
