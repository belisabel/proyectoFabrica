package com.ielec.fabrica.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ielec.fabrica.entities.Articulo;
import com.ielec.fabrica.exceptions.MyException;

import com.ielec.fabrica.repositories.ArticuloRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticuloServicio {

    @Autowired
    private ArticuloRepositorio articuloRepositorio;

    @Transactional
    public void crearArticulo(String nombreArticulo , String descripcionArticulo) throws MyException {

        validar(nombreArticulo,descripcionArticulo);
        Articulo articulo = new Articulo();// Instancio un objeto del tipo Articulo
        articulo.setNombreArticulo(nombreArticulo);// Seteo el atributo, con el valor recibido como parámetro
        articulo.setDescripcionArticulo(descripcionArticulo);

        articuloRepositorio.save(articulo); // Persisto el dato en mi BBDD
    }

    public List<Articulo> listarArticulos() {

        List<Articulo> articulos = new ArrayList<>();

        articulos = articuloRepositorio.findAll();
        return articulos;
    }

    // @Transactional
    // public void modificarArticulo(String nombre, String id) throws MyException {
    //     validar(nombre);
    //     Optional<Articulo> respuesta = ArticuloRepositorio.findById(id);
    //     if (respuesta.isPresent()) {
    //         Articulo Articulo = respuesta.get();

    //         Articulo.setNombreArticulo(nombre);
    //         ArticuloRepositorio.save(Articulo);
    //     }
    // }

    private void validar(String nombreArticulo , String descripcionArticulo) throws MyException {
        if (nombreArticulo.isEmpty() || nombreArticulo == null) {
            throw new MyException("el nombre Articulo no puede ser nulo o estar vacío");
        }
        if (descripcionArticulo.isEmpty() || descripcionArticulo == null) {
            throw new MyException("la descripcion Articulo no puede ser nulo o estar vacío");
        }
    }

        @Transactional(readOnly = true)
    public Articulo getOne(UUID id) {
        return articuloRepositorio.getReferenceById(id);
    }


}

