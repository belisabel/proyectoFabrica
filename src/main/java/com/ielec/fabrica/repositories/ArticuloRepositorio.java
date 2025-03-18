package com.ielec.fabrica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ielec.fabrica.entities.Articulo;

@Repository
public interface ArticuloRepositorio extends JpaRepository<Articulo,UUID>{

    
}