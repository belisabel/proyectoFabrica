package com.ielec.fabrica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ielec.fabrica.exceptions.MyException;
import com.ielec.fabrica.services.ArticuloServicio;
import com.ielec.fabrica.services.UsuarioServicio;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FabricaApplication {

	public static void main(String[] args) {
		//SpringApplication.run(FabricaApplication.class, args);

		//ArticuloServicio articulo = new ArticuloServicio();
		//Hibernate: insert into articulo (descripcion_articulo,fabrica_id,nombre_articulo,nro_articulo,id_articulo) values (?,?,?,?,?)

		try {
			ApplicationContext context = SpringApplication.run(FabricaApplication.class, args);
			ArticuloServicio articuloServicio = context.getBean(ArticuloServicio.class);
			articuloServicio.crearArticulo("laptop", "aparato electrónico");
			
			//System.out.println("Mi artículo es: " + articuloServicio.getOne(null));
		} catch (MyException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
