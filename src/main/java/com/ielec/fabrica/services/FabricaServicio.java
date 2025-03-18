package com.ielec.fabrica.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ielec.fabrica.entities.Fabrica;
import com.ielec.fabrica.exceptions.MyException;
import com.ielec.fabrica.repositories.FabricaRepositorio;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FabricaServicio {

    @Autowired
    private FabricaRepositorio fabricaRepositorio;;

    @Transactional
    public void crearFabrica(String nombreFabrica) throws MyException {

        validar(nombreFabrica);
        Fabrica Fabrica = new Fabrica();// Instancio un objeto del tipo Fabrica
        Fabrica.setNombreFabrica(nombreFabrica);// Seteo el atributo, con el valor recibido como parámetro

        fabricaRepositorio.save(Fabrica); // Persisto el dato en mi BBDD
    }

    public List<Fabrica> listarFabricas() {

        List<Fabrica> fabricas = new ArrayList<>();

        fabricas = fabricaRepositorio.findAll();
        return fabricas;
    }

    @Transactional
    public void modificarFabrica(String nombreFabrica, UUID idFabrica) throws MyException {

        validar(nombreFabrica);
        Optional<Fabrica> respuesta = fabricaRepositorio.findById(idFabrica);
        if (respuesta.isPresent()) {
            Fabrica fabrica = respuesta.get();

            fabrica.setNombreFabrica(nombreFabrica);
            fabricaRepositorio.save(fabrica);
        }
    }

    private void validar(String nombreFabrica) throws MyException {
        if (nombreFabrica.isEmpty() || nombreFabrica == null) {
            throw new MyException("el nombre de fabrica no puede ser nulo o estar vacío");
        }
    }

    @Transactional(readOnly = true)
    public Fabrica getOne(UUID idFabrica) {
        return fabricaRepositorio.getReferenceById(idFabrica);
    }

}
