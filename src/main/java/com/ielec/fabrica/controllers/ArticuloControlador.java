package com.ielec.fabrica.controllers;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ielec.fabrica.entities.Fabrica;
import com.ielec.fabrica.entities.Usuario;
import com.ielec.fabrica.entities.Articulo;
import com.ielec.fabrica.exceptions.MyException;
import com.ielec.fabrica.services.FabricaServicio;
import com.ielec.fabrica.services.UsuarioServicio;
import com.ielec.fabrica.services.ArticuloServicio;

@Controller
@RequestMapping("/articulo")
public class ArticuloControlador {

    @Autowired
    private ArticuloServicio articuloServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private FabricaServicio fabricaServicio;

    @GetMapping("/registrar") // localhost:9090/Articulo/registrar
    public String registrar(ModelMap model) {
        
        List<Fabrica> fabricas = fabricaServicio.listarFabricas();
        model.addAttribute("Fabricas", fabricas);
        return "articulo_form.html";
    }

    @PostMapping("/registro")
    public String registro( @RequestParam String nombreArticulo,@RequestParam String descripcionArticulo, @RequestParam UUID idFabrica, ModelMap modelo) {
        try {
            articuloServicio.crearArticulo(nombreArticulo, descripcionArticulo,idFabrica);

            modelo.put("exito", "El Articulo fue cargado exitosamente");

        } catch (MyException ex) {

            modelo.put("error", ex.getMessage());

            return "articulo_form.html"; // volvemos a cargar el formulario.
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Articulo> articulos = articuloServicio.listarArticulos();
        modelo.addAttribute("articulos", articulos);
        return "articulo_list.html";
    }

    @GetMapping("/modificar/{idArticulo}")
    public String modificar(@PathVariable UUID idArticulo, ModelMap modelo) {

        modelo.put("articulo", articuloServicio.getOne(idArticulo));

        
        List<Fabrica> fabricas = fabricaServicio.listarFabricas();

       
        modelo.addAttribute("fabricas", fabricas);

        return "articulo_modificar.html";
    }

    @PostMapping("/modificar/{idArticulo}")
    public String modificar(@PathVariable UUID idArticulo, String nombreArticulo, String descripcionArticulo, UUID idFabrica,
             ModelMap modelo) {
        try {
            
            List<Fabrica> fabricas = fabricaServicio.listarFabricas();

            
            modelo.addAttribute("fabricas", fabricas);
            articuloServicio.modificarArticulo(nombreArticulo, descripcionArticulo, idFabrica, idArticulo);
            return "redirect:../lista";

        } catch (MyException ex) {
        
            List<Fabrica> fabricas = fabricaServicio.listarFabricas();

            modelo.put("error", ex.getMessage());

   
            modelo.addAttribute("fabricas", fabricas);

            return "articulo_modificar.html";
        }

    }

}
