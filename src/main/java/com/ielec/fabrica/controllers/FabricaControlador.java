package com.ielec.fabrica.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ielec.fabrica.entities.Fabrica;
import com.ielec.fabrica.exceptions.MyException;
import com.ielec.fabrica.services.FabricaServicio;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;


@Controller
@RequestMapping("/fabrica") // localhost:8080/Fabrica
public class FabricaControlador {

    @Autowired
    private FabricaServicio fabricaServicio;


    @GetMapping("/registrar") // localhost:8080/Fabrica/registrar
    public String registrar() {
        return "fabrica_form.html";
    }


    @PostMapping("/registro") // localhost:8080/Fabrica/registro
    public String registro(@RequestParam String nombre,ModelMap modelo) {
        try {
            fabricaServicio.crearFabrica(nombre);    // llamo a mi servicio para persistir   
            modelo.put("exito", "La Fabrica fue cargado exitosamente");     
        } catch (MyException ex) {
            
            modelo.put("error",ex.getMessage());
            Logger.getLogger(FabricaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "fabrica_form.html";
        }        
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {


        List<Fabrica> fabricas = fabricaServicio.listarFabricas();
        modelo.addAttribute("fabricas", fabricas);
        return "fabrica_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable UUID id, ModelMap modelo) {
        modelo.put("fabrica", fabricaServicio.getOne(id));


        return "fabrica_modificar.html";
    }


    @PostMapping("/modificar/{id}") 
    public String modificar(@PathVariable UUID id, String nombreFabrica, ModelMap modelo) {
        try {
            fabricaServicio.modificarFabrica(nombreFabrica, id);


            return "redirect:../lista";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "fabrica_modificar.html";
        }
    }

    // @GetMapping("/lista") // localhost:8080/Fabrica/lista
    // public String lista(ModelMap modelo) {

    //     List<Fabrica> Fabricaes1 = FabricaServicio.listarFabricaes();
    
    //     if (Fabricaes1 == null || Fabricaes1.isEmpty()) {
    //         System.out.println("La lista de Fabricaes está vacía.");
    //     } else {
    //         for (Fabrica Fabrica : Fabricaes1) {
    //             System.out.println(Fabrica.getNombre());
    //         }
    //     }
    
    //     return "Fabrica_form.html";
     
       
    // }
}