package main;

import java.io.*;
import java.util.*;
import java.time.LocalTime;
import java.time.DayOfWeek;

import club.Actividad;
import club.Persona;
import club.Sistema;
import club.Socio;

public class Main {

	public static void main(String[] args)throws IOException {
		//PrintWriter salida = new PrintWriter(new FileWriter("BaseDeDatos.txt"));
		
		Sistema sistema = new Sistema();
		List<Socio> lstSocios = new ArrayList<Socio>();
		int valorCuotaSocio = 5000;
		int sueldoProfesor = 10000;
		float arancel = 2000;

		
		try{
			System.out.println(" - Buscando DB...");
			if(sistema.leerDB()){
				System.out.println(" - Se cargaron datos previos");
			}else{
				System.out.println(" - No hay datos previos");
			};
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		//Declaro variables utilizadas por el menú
		long dni;
		int identificador,act,i,diaDSemana,cupo;
		String nombre,apellido,lugar,inicio,fin,nombreDeporte;
		
		//Menú principal
		Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {
            System.out.println("");
            System.out.println("-----------------MENU PRINCIPAL-----------------");
            System.out.println("1. Inscribir un Socio");
            System.out.println("2. Inscribir un Socio a una Actividad Deportiva");
            System.out.println("3. Agregar profesor y Actividad Deportiva");
            System.out.println("4. Sustituir profesor en una Actividad Deportiva");
            System.out.println("5. Mostrar todos los socios");
            System.out.println("6. Mostrar todos los Profesores");
            System.out.println("7. Mostrar Actividades Deportivas");
            System.out.println("8. Salir");
 
            try {
 
				System.out.println("");
                System.out.println(" > Escribe una de las opciones");
                opcion = sn.nextInt();
 
                switch (opcion) {
                    case 1:
						dni=0;
						nombre="";
						apellido="";
						do{
							System.out.println("Intrduzca DNI del nuevo Socio:");
							dni = sn.nextInt();
							System.out.println("Intrduzca el Nombre:");
							nombre = sn.next();
							System.out.println("Intrduzca el Apellido:");
							apellido = sn.next();
						}while(String.valueOf(dni).length()!=8||nombre==null||nombre.isEmpty()||apellido==null||apellido.isEmpty());
						try {
							lstSocios.add(sistema.traerSocio(sistema.agregarSocio(dni,nombre,apellido,valorCuotaSocio)));
						}catch(Exception e) {
							System.out.println(e.getMessage());
						}
                        break;
                    case 2:
					//comprobar si existe una actividad deportiva
						nombreDeporte = "";
						if( sistema.existenDeportes() ){
							do{
									System.out.println("");
									System.out.println(" > Intrduzca DNI (8 digitos) o Legajo del Socio:");
								identificador = sn.nextInt();
							}
							while (sistema.traerSocioDni(identificador) == null && sistema.traerSocio(identificador) == null);
							do{
								do{
										System.out.println("");
											System.out.println(" > Elija una Actividad Deportiva:");
									i = 0;
									for (Actividad deporte : sistema.traerDeportes()) {
										i++;
										System.out.println(String.valueOf(i) + " - " + deporte.getNombre());
									}
									act = sn.nextInt();
								}while( act > i );
								i = 0;
								for (Actividad deporte : sistema.traerDeportes()) {
									i++;
									if (i == act)nombreDeporte=deporte.getNombre();
								}
							}
							while (sistema.traerDeporte(nombreDeporte) == null);

							if (String.valueOf(identificador).length() == 8 &&  nombreDeporte != null && !nombreDeporte.isEmpty() ){
								//Es DNI
								try{
									if(sistema.agregarSocioDeporte(sistema.traerSocioDni(identificador),sistema.traerDeporte(nombreDeporte))){
										System.out.println(" > Socio inscripto con exito a "+ nombreDeporte);
									}else{
										System.out.println(" > Hubo un problema al inscribir al socio a la actividad");
									}
								}catch(Exception e) {
									System.out.println(e.getMessage());
								}
							}else{
								//Es legajo
								try{
									if(sistema.agregarSocioDeporte(sistema.traerSocio(identificador),sistema.traerDeporte(nombreDeporte))){
										System.out.println(" > Socio inscripto con exito a "+ nombreDeporte);
									}else{
										System.out.println(" > Hubo un problema al inscribir al socio a la actividad");
									}
								}catch(Exception e) {
									System.out.println(e.getMessage());
								}
							}
						}else{
							System.out.println(" > No existe ninguna actividad deportiva, agregar una primero.");
						};
                        break;
					case 3:
						dni=0;
						nombre="";
						apellido="";
						do{
							System.out.println("");
							System.out.println(" > Intrduzca DNI del nuevo profesor:");
							dni = sn.nextInt();
							System.out.println("Intrduzca el Nombre:");
							nombre = sn.next();
							System.out.println("Intrduzca el Apellido:");
							apellido = sn.next();
						}while(String.valueOf(dni).length()!=8||nombre==null||nombre.isEmpty()||apellido==null||apellido.isEmpty());
						try {
							sistema.agregarProfesor(dni, nombre, apellido, sueldoProfesor);
						}catch(Exception e) {
							System.out.println(e.getMessage());
						}

						do{
							System.out.println("");
							System.out.println("1 - Lunes");
							System.out.println("2 - Martes");
							System.out.println("3 - Miercoles");
							System.out.println("4 - Jueves");
							System.out.println("5 - Viernes");
							System.out.println("6 - Sabado");
							System.out.println("7 - Domingo");
							System.out.println(" > Seleccione que día se practicará este deporte:");
							diaDSemana = sn.nextInt();
							System.out.println("Intrduzca el nombre del deporte:");
							nombre = sn.next();
							System.out.println("Indique el lugar empleado:");
							lugar = sn.next();
							System.out.println("Indique el cupo maximo de socios:");
							cupo = sn.nextInt();
							System.out.println("Hora de comienzo de actividad (hh:mm):");
							inicio = sn.next();
							System.out.println("Hora finalización de actividad (hh:mm):");
							fin = sn.next();
						}while(
							diaDSemana>7 || diaDSemana<1 ||
							nombre==null || nombre.isEmpty() ||
							apellido==null || apellido.isEmpty() ||
							lugar==null || lugar.isEmpty() ||
							cupo<1 ||
							inicio.split(":").length!=2 ||
							fin.split(":").length!=2
							);
						try {
							if(sistema.agregarDeporte(
								nombre, 
								DayOfWeek.of(diaDSemana),
								LocalTime.of( Integer.parseInt( inicio.split(":")[0] ), Integer.parseInt( inicio.split(":")[1] )), 
								LocalTime.of( Integer.parseInt( fin.split(":")[0] ), Integer.parseInt( fin.split(":")[1] )), 
								lugar, 
								arancel, 
								sistema.traerProfesorDni(dni), 
								cupo
							)){
							System.out.println(" > Se agregó la actividad: " + nombre);
							}else{
							//si la actividad no se crea, el profesor recien creado se borra, sino queda huerfano, acumula basura.
							sistema.eliminarProfesor(sistema.traerProfesorDni(dni).getNroLegajo());
							System.out.println(" > Hubo un problema para agregar la actividad");
							}
						}catch(Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 4:
						nombreDeporte = "";
						if( sistema.existenDeportes() ){
							dni=0;
							nombre="";
							apellido="";
							do{
								System.out.println("");
									System.out.println(" > Intrduzca DNI del nuevo profesor:");
								dni = sn.nextInt();
								System.out.println("Intrduzca el Nombre:");
								nombre = sn.next();
								System.out.println("Intrduzca el Apellido:");
								apellido = sn.next();
							}while(String.valueOf(dni).length()!=8||nombre==null||nombre.isEmpty()||apellido==null||apellido.isEmpty());
							try {
								sistema.agregarProfesor(dni, nombre, apellido, sueldoProfesor);
							}catch(Exception e) {
								System.out.println(e.getMessage());
							}

							do{
								do{
										System.out.println("");
										System.out.println(" > Elija una Actividad Deportiva:");
									i = 0;
									for (Actividad deporte : sistema.traerDeportes()) {
										i++;
										System.out.println(String.valueOf(i) + " - " + deporte.getNombre());
									}
									act = sn.nextInt();
								}while( act > i );
								i = 0;
								for (Actividad deporte : sistema.traerDeportes()) {
									i++;
									if (i == act)nombreDeporte=deporte.getNombre();
								}
							}
							while (sistema.traerDeporte(nombreDeporte) == null);

							//Sustituyo el profesor
							if(sistema.reemplazarProfesor(sistema.traerDeporte(nombreDeporte),sistema.traerProfesorDni(dni))){
								System.out.println(" > Se actualizó el profesor para " + nombreDeporte);
							}else{
								System.out.println(" > Hubo un problema al intentar actualizar el profesor de la actividad " + nombreDeporte);
							}

						}else{
							System.out.println(" > No existe ninguna actividad deportiva, agregar una primero.");
						};
						break;
					case 5:
						System.out.println("");
						System.out.println("-----------------LISTADO DE SOCIOS-----------------");
						for (Persona socio : sistema.traerSocios()) {
							System.out.println(socio);
						}
						break;
					case 6:
						System.out.println("");
						System.out.println("---------------LISTADO DE PROFESORES---------------");
						for (Persona profesor : sistema.traerProfesores()) {
							System.out.println(profesor);
						}
						break;
					case 7:
						System.out.println("");
						System.out.println("------------LISTADO DE ACTIVIDADES DEP.------------");
						for (Actividad deporte : sistema.traerDeportes()) {
							System.out.println(deporte);
						}
						break;
                    case 8:
                        salir = true;
                        break;
                    default:
                        System.out.println(" > Solo números entre 1 y 9 !!");
                }
            } catch (InputMismatchException e) {
                System.out.println(" > Debes insertar un número");
                sn.next();
            }
        }

		//Opcional
		try {
			//sistema.agregarEvento("b", LocalDate.now(), LocalTime.of(13, 0), LocalTime.of(15, 0), "b", 1000, sistema.traerSocio(2));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
