package com.ielec.fabrica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ielec.fabrica.exceptions.MyException;
import com.ielec.fabrica.services.ArticuloServicio;
import com.ielec.fabrica.services.UsuarioServicio;

@SpringBootApplication
public class FabricaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabricaApplication.class, args);

		// ArticuloServicio articulo = new ArticuloServicio();
		// try {
		// 	articulo.crearArticulo("computadora", "aparato electrónico");
		// 	System.out.println("Mi artículo es: " + articulo.toString());
		// } catch (MyException e) {
		// 	System.out.println("Error: " + e.getMessage());
		// }

	}

}
