package main;

import java.io.*;
import java.util.*;
import java.time.LocalTime;
import java.time.LocalDate;
import club.Sistema;
import club.Socio;

public class Main {

	public static void main(String[] args)throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter("BaseDeDatos.txt"));
		
		Sistema sistema = new Sistema();
		List<Socio> lstSocios = new ArrayList<Socio>();
		
		//carga de datos al sistema
		try {
			sistema.agregarProfesor(123123, "Pedro", "Perez", 1, 50000);
			sistema.agregarProfesor(444444, "Marcos", "Martinez", 2, 50000);
			sistema.agregarProfesor(123123, "Alicia", "Roca", 3, 50000);
			sistema.agregarProfesor(333333, "Erica", "Torres", 4, 50000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			sistema.agregarSocio(5555, "Leo", "Messi", 1, 5000);
			sistema.agregarSocio(3214, "Angel", "Di Maria", 2, 5000);
			sistema.agregarSocio(3215, "Julian", "Alvarez", 3, 5000);
			sistema.agregarSocio(3211, "Emiliano", "Martinez", 4, 5000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			sistema.agregarDeporte("futbol", LocalDate.of(2022,11,18), LocalTime.of(18, 00), LocalTime.of(19, 00), "cancha 2", 2000, sistema.traerProfesor(1), 4, lstSocios);
			sistema.agregarDeporte("Tenis", LocalDate.of(2022,11,20), LocalTime.of(15, 00), LocalTime.of(16, 00), "cancha 1", 4000, sistema.traerProfesor(3), 2, lstSocios);
			sistema.agregarDeporte("Volley", LocalDate.of(2022,11,17), LocalTime.of(10, 00), LocalTime.of(12, 00), "cancha 5", 2000, sistema.traerProfesor(2), 4, lstSocios);
			sistema.agregarDeporte("Bochas", LocalDate.of(2022,11,22), LocalTime.of(11, 00), LocalTime.of(12, 00), "cancha 3", 1000, sistema.traerProfesor(3), 3, lstSocios);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			sistema.agregarEvento("Alquiler de salón", LocalDate.of(2022, 12, 15), LocalTime.of(19, 00), LocalTime.of(02, 00), "Salon pequeño", 30000, sistema.traerSocio(4));
			sistema.agregarEvento("Alquiler de cancha", LocalDate.of(2022, 12, 02), LocalTime.of(21, 00), LocalTime.of(23, 00), "cancha 2", 3000, sistema.traerSocio(2));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			lstSocios.add(sistema.traerSocio(1));
			lstSocios.add(sistema.traerSocio(2));
			lstSocios.add(sistema.traerSocio(3));
			lstSocios.add(sistema.traerSocio(4));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	
		//carga de datos al archivo txt
		try {
			salida.println(sistema.traerProfesores());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			salida.println(sistema.traerDeporte(sistema.traerProfesor(1)));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			salida.println(sistema.traerEvento(sistema.traerSocio(2)));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		salida.close();
	}

}
