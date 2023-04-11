package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Camada de acesso ao banco de dados
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
