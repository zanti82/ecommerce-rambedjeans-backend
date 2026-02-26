package com.rambedjeans.ecommerce_jeans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceJeansApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceJeansApplication.class, args);
	}

	/*
	Controller habla con el mundo exterior.
	Service piensa y decide.
	Repository habla con la base de datos.
	Ninguno hace el trabajo del otro.

	El paciente que llega El navegador / app
	La recepcionista Controller 
	El médico Service
	El archivo de historiales Repository
	La ficha del paciente Model
	*/

}
