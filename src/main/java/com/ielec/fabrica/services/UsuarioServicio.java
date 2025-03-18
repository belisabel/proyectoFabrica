package com.ielec.fabrica.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ielec.fabrica.entities.Usuario;
import com.ielec.fabrica.enumerations.Rol;
import com.ielec.fabrica.exceptions.MyException;
import com.ielec.fabrica.repositories.UsuarioRepositorio;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrar(String nombre,String apellido, String email,String password) throws MyException {

        validar(nombre, email, password);
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setPassword("default");
        //usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        // usuario.setPassword(password);
        usuario.setRol(Rol.USER);
        usuarioRepositorio.save(usuario);

    }

 


    private void validar(String nombre, String email, String password) throws MyException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MyException("el nombre no puede ser nulo o estar vacío");
        }
        if (email.isEmpty() || email == null) {
            throw new MyException("el email no puede ser nulo o estar vacío");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MyException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }
       
    }
    @Transactional(readOnly = true)
    public Usuario getOne(UUID id) {
        return usuarioRepositorio.getReferenceById(id);
    }
}
