package br.com.skyline.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodolistApplication {

	/*
	 * Usar ApiDog
	 * Usar - http://localhost:8080/h2-console como banco de dados
	 */
	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
