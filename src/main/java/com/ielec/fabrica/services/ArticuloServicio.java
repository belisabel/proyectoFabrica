package com.ielec.fabrica.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ielec.fabrica.entities.Articulo;
import com.ielec.fabrica.exceptions.MyException;

import com.ielec.fabrica.repositories.ArticuloRepositorio;
import com.ielec.fabrica.repositories.FabricaRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticuloServicio {

    
    private ArticuloRepositorio articuloRepositorio;

    private AtomicInteger atomicInteger;

    private FabricaRepositorio fabricaRepositorio;

    

    public ArticuloServicio(ArticuloRepositorio articuloRepositorio, FabricaRepositorio fabricaRepositorio) {
       
       this.articuloRepositorio = articuloRepositorio;
       this.fabricaRepositorio = fabricaRepositorio;
       Integer maximo= articuloRepositorio.buscarMaximo();

       this.atomicInteger = new AtomicInteger(maximo+1);
    }

    @Transactional
    public void crearArticulo(String nombreArticulo , String descripcionArticulo) throws MyException {

        validar(nombreArticulo,descripcionArticulo);
        Articulo articulo = new Articulo();// Instancio un objeto del tipo Articulo
        articulo.setNombreArticulo(nombreArticulo);// Seteo el atributo, con el valor recibido como parámetro
        articulo.setDescripcionArticulo(descripcionArticulo);
        articulo.setNroArticulo(atomicInteger.getAndIncrement());

        articuloRepositorio.save(articulo); // Persisto el dato en mi BBDD
    }

    public List<Articulo> listarArticulos() {

        List<Articulo> articulos = new ArrayList<>();

        articulos = articuloRepositorio.findAll();
        return articulos;
    }

    

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
    @Transactional
    public void modificarArticulo(String nombreArticulo , String descripcionArticulo, UUID id) throws MyException {

        validar(nombreArticulo,descripcionArticulo);
        Optional<Articulo> respuesta = articuloRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Articulo articulo = respuesta.get();

            articulo.setNombreArticulo(nombreArticulo);
            articulo.setDescripcionArticulo(descripcionArticulo);
            articuloRepositorio.save(articulo);
        }
    }


}

