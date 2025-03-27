package com.ielec.fabrica.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ielec.fabrica.entities.Articulo;
import com.ielec.fabrica.entities.Fabrica;
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
    public void crearArticulo(String nombreArticulo , String descripcionArticulo,UUID idFabrica) throws MyException {

        validar(nombreArticulo,descripcionArticulo,idFabrica);
        Articulo articulo = new Articulo();// Instancio un objeto del tipo Articulo
        articulo.setNombreArticulo(nombreArticulo);// Seteo el atributo, con el valor recibido como parámetro
        articulo.setDescripcionArticulo(descripcionArticulo);
        articulo.setNroArticulo(atomicInteger.getAndIncrement());
        Fabrica fabrica = fabricaRepositorio.findById(idFabrica).get();
        articulo.setFabrica(fabrica);

        articuloRepositorio.save(articulo); // Persisto el dato en mi BBDD
    }

    public List<Articulo> listarArticulos() {

        List<Articulo> articulos = new ArrayList<>();

        articulos = articuloRepositorio.findAll();
        return articulos;
    }

    

    private void validar(String nombreArticulo , String descripcionArticulo,UUID idFabrica) throws MyException {
        if (nombreArticulo.isEmpty() || nombreArticulo == null) {
            throw new MyException("el nombre Articulo no puede ser nulo o estar vacío");
        }
        if (descripcionArticulo.isEmpty() || descripcionArticulo == null) {
            throw new MyException("la descripcion Articulo no puede ser nulo o estar vacío");
        }
        if ((idFabrica.toString()).isEmpty() || idFabrica == null) {
            throw new MyException("La Fabrica no puede ser nula o estar vacia");
        }
        
    }

        @Transactional(readOnly = true)
    public Articulo getOne(UUID id) {
        return articuloRepositorio.getReferenceById(id);
    }
    @Transactional
    public void modificarArticulo(String nombreArticulo , String descripcionArticulo, UUID idFabrica,UUID idArticulo) throws MyException {

        validar(nombreArticulo,descripcionArticulo,idFabrica);
        Optional<Articulo> respuestaArticulo = articuloRepositorio.findById(idArticulo);
        Optional<Fabrica> respuestaFabrica = fabricaRepositorio.findById(idFabrica);

        Fabrica fabrica = new Fabrica();

        if (respuestaFabrica.isPresent()) {
            fabrica = respuestaFabrica.get();
        }
        if (respuestaArticulo.isPresent()) {
            Articulo articulo = respuestaArticulo.get();

            articulo.setNombreArticulo(nombreArticulo);
            articulo.setDescripcionArticulo(descripcionArticulo);
            articulo.setFabrica(fabrica);
            articuloRepositorio.save(articulo);
        }
    }


}

