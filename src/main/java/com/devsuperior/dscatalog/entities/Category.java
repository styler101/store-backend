package com.devsuperior.dscatalog.entities;



import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
// Camada de entidade que será as representações com o banco de dados.
@Entity
@Table(name="tb_category")
public class Category implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") // Vai salvar as informação no formato utc
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;
    public Category(){}

    public Category(Long id, String name){
        this.id = id;
        this.name = name;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }


    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    public void prePersist(){
        createdAt = Instant.now();
    }

    @PreUpdate
    public void  preUpdate(){
        updatedAt = Instant.now();
    }


}
