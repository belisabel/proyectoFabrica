package com.ielec.fabrica.entities;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name = "articulo")
public class Articulo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID idArticulo; // Autogenerado.

   

    
    @Column(name="nro_articulo",unique = true)
    private Integer nroArticulo;
    // Integer. Código de identificación interno de un producto.
    // Código correlativo único y autogenerado. Debe iniciar en 1, y si se agrega
    // otro artículo
    // a la lista sería 2… y así sucesivamente.
    @Column(name = "nombre_articulo",nullable = false)
    private String nombreArticulo;
    @Column(name = "descripcion_articulo",nullable = false)
    private String descripcionArticulo; 

    @ManyToOne
    @JoinColumn(name = "fabrica_id") 
    private Fabrica fabrica;// Representará un dato de tipo Fábrica, por lo que deberá establecerse la relación correspondiente.

    public Articulo() {
       // this.nroArticulo = atomicInteger.incrementAndGet();
    }
}
