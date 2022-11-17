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
			sistema.agregarProfesor(123123, "asd", "qweqew", 1, 50000);
			sistema.agregarProfesor(123124, "asdr", "qeqew", 2, 50000);
			sistema.agregarProfesor(12123, "ad", "qweqqw", 3, 50000);
			sistema.agregarProfesor(123, "aswd", "qwew", 4, 50000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			sistema.agregarSocio(321, "qwe", "wer", 1, 5000);
			sistema.agregarSocio(3214, "qwer", "wert", 2, 5000);
			sistema.agregarSocio(3215, "qwert", "werty", 3, 5000);
			sistema.agregarSocio(32111, "qwe", "wer", 4, 5000);
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
		
		try {
			sistema.agregarDeporte("a", LocalDate.now(), LocalTime.of(12, 0), LocalTime.of(14, 0), "a", 2000, sistema.traerProfesor(1), 4, lstSocios);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			sistema.agregarEvento("b", LocalDate.now(), LocalTime.of(13, 0), LocalTime.of(15, 0), "b", 1000, sistema.traerSocio(2));
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
