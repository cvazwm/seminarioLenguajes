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
			sistema.agregarProfesor(123123, "asd", "qweqew", 50000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
			
		try {
			sistema.agregarDeporte("a", LocalDate.now(), LocalTime.of(12, 0), LocalTime.of(14, 0), "a", 2000, sistema.traerProfesor(1), 4);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//Opcional
		try {
			lstSocios.add(sistema.traerSocio(sistema.agregarSocio(321, "qwe", "wer", 5000)));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//Opcional
		try {
			sistema.agregarEvento("b", LocalDate.now(), LocalTime.of(13, 0), LocalTime.of(15, 0), "b", 1000, sistema.traerSocio(2));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//carga de datos al archivo txt
		// try {
		// 	salida.println(sistema.traerProfesores());
		// }catch(Exception e) {
		// 	System.out.println(e.getMessage());
		// }
		
		// try {
		// 	salida.println(sistema.traerDeporte(sistema.traerProfesor(1)));
		// }catch(Exception e) {
		// 	System.out.println(e.getMessage());
		// }
		
		// try {
		// 	salida.println(sistema.traerEvento(sistema.traerSocio(2)));
		// }catch(Exception e) {
		// 	System.out.println(e.getMessage());
		// }
		salida.close();
	}

}
