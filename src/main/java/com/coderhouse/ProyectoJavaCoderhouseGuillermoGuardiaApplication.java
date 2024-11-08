package com.coderhouse;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.coderhouse.models.Client;

@SpringBootApplication
public class ProyectoJavaCoderhouseGuillermoGuardiaApplication implements CommandLineRunner{
	

	//Sobreescribo el metodo run...

	@Override
	public void run(String... arg) throws Exception {
		try {
			
			/*//Aca adentro hacemos las operaciones...
			Producto product1 = new Producto("Termo","Termo Marca Bremen",225);
			System.out.println(product1.toString());
			
			Producto product2 = new Producto("Vaso","Vaso Marca Bremen",225);
			System.out.println(product2.toString());
			
			dao.createProduct(product1);
			dao.createProduct(product2);
			*/
			
		Client cliente1 = new Client("31823844","Guillermo","Guardia","Bolivia553","1864","11591491654");
		System.out.print(cliente1.toString());
            
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(ProyectoJavaCoderhouseGuillermoGuardiaApplication.class, args);
	}

}
