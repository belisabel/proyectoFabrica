package com.ielec.fabrica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ielec.fabrica.entities.Fabrica;

@Repository
public interface FabricaRepositorio extends JpaRepository<Fabrica,UUID> {
    
}
