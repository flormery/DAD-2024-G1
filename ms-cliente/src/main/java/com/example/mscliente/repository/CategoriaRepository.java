package com.example.mscliente.repository;


import com.example.mscliente.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}

