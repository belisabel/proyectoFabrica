package com.ielec.fabrica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ielec.fabrica.entities.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,UUID> {

    
} 
