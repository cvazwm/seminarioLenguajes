package club;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
	private List<Persona> lstPersonas = new ArrayList<Persona>();
	private List<Actividad> lstActividades = new ArrayList<Actividad>();
	private int legajoNuevo, carnetNuevo;
	private boolean flagCargandoDb=false;
	
	//Constructor
	public Sistema() {}
	public Sistema(List<Persona> lstPersonas, List<Actividad> lstActividades) {
		super();
		this.lstPersonas = lstPersonas;
		this.lstActividades = lstActividades;
	}

	
	//Getters & Setters
	public List<Persona> getLstPersonas(){
		return lstPersonas;
	}
	public void setLstPersonas(List<Persona> lstPersonas) {
		this.lstPersonas = lstPersonas;
	}

	public List<Actividad> getLstActividades() {
		return lstActividades;
	}
	public void setLstActividades(List<Actividad> lstActividades) {
		this.lstActividades = lstActividades;
	}

	//toString
	@Override
	public String toString() {
		return "\n LISTA PERSONAS: " + lstPersonas + "\n LISTA ACTIVIDADES: " + lstActividades + "\n";
	}
	
	
	//Metodos
	public Persona traerPersona(long dni) {
		Persona persona = null;
		
		int i = 0;
		while((i < lstPersonas.size()) && (persona == null)) {
			if(lstPersonas.get(i).getDni() == dni) {
				persona = lstPersonas.get(i);
			}
			i++;
		}
		
		return persona;
	}
	
	public Profesor traerProfesor(int nroLegajo) {
		Profesor profesor = null;
		
		for(Persona pp : lstPersonas) {
			if((pp instanceof Profesor) && (((Profesor) pp).getNroLegajo() == nroLegajo)) {
				profesor = ((Profesor) pp);
			}
		}
		
		return profesor;
	}
	public Profesor traerProfesorDni(long dni) {
		Profesor profesor = null;
		
		for(Persona pp : lstPersonas) {
			if((pp instanceof Profesor) && (((Profesor) pp).getDni() == dni)) {
				profesor = ((Profesor) pp);
			}
		}
		
		return profesor;
	}
	
	public Socio traerSocio(int carnet) {
		Socio socio = null;
		
		for(Persona ps : lstPersonas) {
			if((ps instanceof Socio) && (((Socio) ps).getCarnet() == carnet)) {
				socio = ((Socio) ps);
			}
		}
		
		return socio;
	}
	
	public Socio traerSocioDni(long dni) {
		Socio socio = null;
		
		for(Persona ps : lstPersonas) {
			if((ps instanceof Socio) && (((Socio) ps).getDni() == dni)) {
				socio = ((Socio) ps);
			}
		}
		
		return socio;
	}
	
	public List<Profesor> traerProfesores(){
		List<Profesor> lstProfesores = new ArrayList<Profesor>();
		
		for(Persona pp : lstPersonas) {
			if(pp instanceof Profesor) {
				lstProfesores.add((Profesor) pp);
			}
		}
		
		return lstProfesores;
	}
	
	public List<Socio> traerSocios(){
		List<Socio> lstSocios = new ArrayList<Socio>();
		
		for(Persona ps : lstPersonas) {
			if(ps instanceof Socio) {
				lstSocios.add((Socio) ps);
			}
		}
		
		return lstSocios;
	}
	
	public boolean agregarSocioDeporte(Socio socio, Deporte deporte)throws Exception {
		if(deporte.getCupo() == deporte.getLstSocios().size() + 1 )throw new Exception("ERROR: No hay cupo disponible para la actividad.");

		//Lo mando al archivo si es que no estoy cargando una DB anterior
		if(this.flagCargandoDb==false){
			if(!updateDBPersonas(socio, deporte)){
				System.out.println("Hubo un problema al intentar escribir la base de datos");
			}
		}

		return deporte.agregarSocio(socio);
	}

	public boolean agregarProfesor(long dni, String nombre, String apellido, float sueldo)throws Exception {
		if(traerProfesorDni(dni) != null)throw new Exception("ERROR: profesor ya se encuentra en la lista.");

		for(Persona pp : lstPersonas) {
			if(pp instanceof Profesor) {
				if ( legajoNuevo < traerProfesorDni(pp.getDni()).getNroLegajo()){
					legajoNuevo = traerProfesorDni(pp.getDni()).getNroLegajo();
				};
			}
		}
		legajoNuevo++;

		Profesor profesor = new Profesor(dni, nombre, apellido, legajoNuevo, sueldo);

		//Lo mando al archivo si es que no estoy cargando una DB anterior
		if(this.flagCargandoDb==false){
			if(!updateDBPersonas(profesor)){
				System.out.println("Hubo un problema al intentar escribir la base de datos");
			}
		}
		return lstPersonas.add(profesor);
	}

	public void agregarProfesor(long dni, String nombre, String apellido, float sueldo, int legajo) {
		//Exclusivo para la carga de la DB (donde el nro de legajo se debe respetar)
		Profesor profesor = new Profesor(dni, nombre, apellido, legajo, sueldo);
		lstPersonas.add(profesor);
	}
	
	public int agregarSocio(long dni, String nombre, String apellido, float cuota)throws Exception {
		if(traerSocioDni(dni) != null)throw new Exception("ERROR: socio ya se encuentra en la lista.");
		
		for(Persona pp : lstPersonas) {
			if(pp instanceof Socio) {
				if ( carnetNuevo < traerSocioDni(pp.getDni()).getCarnet()){
					carnetNuevo = traerSocioDni(pp.getDni()).getCarnet();
				};
			}
		}
		carnetNuevo++;
		Socio socio = new Socio(dni, nombre, apellido, carnetNuevo, cuota);

		
		//Lo mando al archivo si es que no estoy cargando una DB anterior
		if(this.flagCargandoDb==false){
			if(!updateDBPersonas(socio)){
				System.out.println("Hubo un problema al intentar escribir la base de datos");
			}
		}
		lstPersonas.add(socio);
		return socio.getCarnet();
	}
	
	public void agregarSocio(long dni, String nombre, String apellido, float cuota, int carnet) {
		//Exclusivo para la carga de la DB (donde el nro de carnet se debe respetar)
		Socio socio = new Socio(dni, nombre, apellido, carnet, cuota);
		lstPersonas.add(socio);
	}
	
	public boolean eliminarPersona(long dni)throws Exception {
		Persona persona = traerPersona(dni);
		if(persona == null)throw new Exception("ERROR: persona no esta en la lista.");
		
		return lstPersonas.remove(persona);
	}
	
	public boolean eliminarProfesor(int nroLegajo) {
		Profesor profesor = traerProfesor(nroLegajo);
		if(profesor == null);
		
		try {
			BufferedReader dbClub = new BufferedReader(new FileReader("club.db"));
			StringBuffer inputBuffer = new StringBuffer();
			String l;
			//cargo archivo en buffer para sobreescribir la linea
			while ((l = dbClub.readLine()) != null) {
				try
				{
					if( Integer.parseInt(l.split(",")[4]) > nroLegajo || Integer.parseInt(l.split(",")[4]) < nroLegajo){
						inputBuffer.append(l);
						inputBuffer.append('\n');
					}
				} catch (NumberFormatException ex)
				{
					inputBuffer.append(l);
					inputBuffer.append('\n');
				}
				
			}
			dbClub.close();
			String dbStr = inputBuffer.toString();

			FileOutputStream dbClub2 = new FileOutputStream("club.db");
			dbClub2.write(dbStr.getBytes());
			dbClub2.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return lstPersonas.remove(profesor);
	}
	
	public boolean reemplazarProfesor(Deporte deporte, Profesor profesor)throws Exception  {
		try{
			eliminarProfesor(deporte.getProfesor().getNroLegajo());

			deporte.setProfesor(profesor);

			if(this.flagCargandoDb==false){
				if(!updateDBActividades(deporte)){
					System.out.println("Hubo un problema al intentar escribir la base de datos");
				}
			}

			return true;	
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}
	
	public boolean eliminarSocio(int carnet)throws Exception {
		Socio socio = traerSocio(carnet);
		if(socio == null)throw new Exception("ERROR: socio no esta en la lista.");
		
		return lstPersonas.remove(socio);
	}
	
	public Actividad traerActividad(int id) {
		Actividad actividad = null;
		
		int i = 0;
		while((i < lstActividades.size()) && (actividad == null)) {
			if(lstActividades.get(i).getId() == id) {
				actividad = lstActividades.get(i);
			}
			i++;
		}
		
		return actividad;
	}
	
	public Actividad traerActividad(DayOfWeek dia, LocalTime horaInicio, String lugar) {
		Actividad actividad = null;
		
		int i = 0;
		while((i < lstActividades.size()) && (actividad == null)) {
			if((lstActividades.get(i).getDia().equals(dia)) && (lstActividades.get(i).getHoraInicio().equals(horaInicio)) &&
					(lstActividades.get(i).getLugar().equals(lugar))) {
				actividad = lstActividades.get(i);
			}
			i++;
		}
		
		return actividad;
	}
	
	public List<Actividad> traerActividades(DayOfWeek dia){
		List<Actividad> listActividades = new ArrayList<Actividad>();
		
		for(int i = 0; i < lstActividades.size(); i++) {
			if((lstActividades.get(i).getDia().equals(dia))) {
				listActividades.add(lstActividades.get(i));
			}
		}
		
		return listActividades;
	}
	
	public Deporte traerDeporte(Profesor profesor) {
		Deporte deporte = null;
		
		for(Actividad ad : lstActividades) {
			if((ad instanceof Deporte) && (((Deporte) ad).getProfesor().equals(profesor))) {
				deporte = ((Deporte) ad);
			}
		}
		
		return deporte;
	}	
	
	public Deporte traerDeporte(String nombre) {
		Deporte deporte = null;
		
		for(Actividad ad : lstActividades) {
			if((ad instanceof Deporte) && (((Deporte) ad).getNombre().equals(nombre))) {
				deporte = ((Deporte) ad);
			}
		}
		
		return deporte;
	}	
	
	public List<Deporte> traerDeportes() {
		List<Deporte> deportes = new ArrayList<Deporte>();
		
		for(Actividad ad : lstActividades) {
			if(ad instanceof Deporte) {
				deportes.add((Deporte) ad);
			}
		}
		
		return deportes;
	}	

	public boolean existenDeportes() {
		
		for(Actividad ad : lstActividades) {
			if(ad instanceof Deporte) {
				return true;
			}
		}
		
		return false;
	}
	
	public Evento traerEvento(Persona responsable) {
		Evento evento = null;
		
		for(Actividad ae : lstActividades) {
			if((ae instanceof Evento) && (((Evento) ae).getResponsable().equals(responsable))) {
				evento = ((Evento) ae);
			}
		}
		
		return evento;
	}
	
	public boolean eliminarActividad(int id)throws Exception {
		Actividad actividad = traerActividad(id);
		if(actividad == null)throw new Exception("ERROR: actividad no esta en la lista.");
		
		return lstActividades.remove(actividad);
	}

	public boolean agregarDeporte(String nombre, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, String lugar, 
			float arancel, Profesor profesor, int cupo) throws Exception {
		
		Actividad actividad = traerActividad(dia, horaInicio, lugar);
		if(actividad != null) return false;
		
		Deporte deporte = new Deporte(nombre, dia, horaInicio, horaFin, lugar, arancel, profesor, cupo);
		
		//Lo mando al archivo si es que no estoy cargando una DB anterior
		if(this.flagCargandoDb==false){
			if(!updateDBActividades(deporte)){
				System.out.println("Hubo un problema al intentar escribir la base de datos");
			}
		}

		return lstActividades.add(deporte);
	}
	
	public boolean agregarEvento(String nombre, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, String lugar, 
			float arancel, Persona responsable)throws Exception {
		
		Actividad actividad = traerActividad(dia, horaInicio, lugar);
		if(actividad != null)throw new Exception("ERROR: fecha, horario y lugar reservado.");
		
		Evento evento = new Evento(nombre, dia, horaInicio, horaFin, lugar, arancel, responsable);

		//Lo mando al archivo si es que no estoy cargando una DB anterior
		if(this.flagCargandoDb==false){
			if(!updateDBActividades(evento)){
				System.out.println("Hubo un problema al intentar escribir la base de datos");
			}
		}

		return lstActividades.add(evento);
	}

	//Escritura de base de datos
	
	public boolean updateDBPersonas(Profesor profesor)throws Exception {
		try {
			BufferedReader dbClub = new BufferedReader(new FileReader("club.db"));
			StringBuffer inputBuffer = new StringBuffer();
			String l;
			//cargo archivo en buffer para sobreescribir la linea
			while ((l = dbClub.readLine()) != null) {
				inputBuffer.append(l);
				inputBuffer.append('\n');
			}
			inputBuffer.append("p,"+profesor.getDni()+","+profesor.getNombre()+","+profesor.getApellido()+","+profesor.getNroLegajo()+","+profesor.getSueldo()+"\n");
			dbClub.close();
			String dbStr = inputBuffer.toString();

			FileOutputStream dbClub2 = new FileOutputStream("club.db");
			dbClub2.write(dbStr.getBytes());
			dbClub2.close();
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateDBPersonas(Socio socio)throws Exception {
		try {
			BufferedReader dbClub = new BufferedReader(new FileReader("club.db"));
			StringBuffer inputBuffer = new StringBuffer();
			String l;
			//cargo archivo en buffer para sobreescribir la linea
			while ((l = dbClub.readLine()) != null) {
				inputBuffer.append(l);
				inputBuffer.append('\n');
			}
			inputBuffer.append("s,"+socio.getDni()+",,"+socio.getNombre()+","+socio.getApellido()+","+socio.getCarnet()+","+socio.getCuota()+"\n");
			dbClub.close();
			String dbStr = inputBuffer.toString();

			FileOutputStream dbClub2 = new FileOutputStream("club.db");
			dbClub2.write(dbStr.getBytes());
			dbClub2.close();
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateDBPersonas(Socio socio, Deporte deporte)throws Exception {
		try {
			BufferedReader dbClub = new BufferedReader(new FileReader("club.db"));
			StringBuffer inputBuffer = new StringBuffer();
			String l;
			//cargo archivo en buffer para sobreescribir la linea
			while ((l = dbClub.readLine()) != null) {
				inputBuffer.append(l);
				inputBuffer.append('\n');
			}
			dbClub.close();
			String dbStr = inputBuffer.toString();
			dbStr = dbStr.replace("s,"+socio.getDni()+",,", "s,"+socio.getDni()+","+ deporte.getNombre() +",");

			FileOutputStream dbClub2 = new FileOutputStream("club.db");
			dbClub2.write(dbStr.getBytes());
			dbClub2.close();
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean updateDBActividades(Deporte deporte)throws Exception {
		try {
			BufferedReader dbClub = new BufferedReader(new FileReader("club.db"));
			StringBuffer inputBuffer = new StringBuffer();
			String l;
			//cargo archivo en buffer para sobreescribir la linea
			while ((l = dbClub.readLine()) != null) {
				if(!l.split(",")[1].equals(deporte.getNombre())){
					inputBuffer.append(l);
					inputBuffer.append('\n');
				}
			}
			inputBuffer.append("d,"+deporte.getNombre()+","+deporte.getDia()+","+deporte.getHoraInicio()+","+deporte.getHoraFin()+","+deporte.getLugar()+","+deporte.getArancel()+","+deporte.getProfesor().getNroLegajo()+","+deporte.getCupo()+"\n");
			dbClub.close();
			String dbStr = inputBuffer.toString();

			FileOutputStream dbClub2 = new FileOutputStream("club.db");
			dbClub2.write(dbStr.getBytes());
			dbClub2.close();
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean updateDBActividades(Evento evento)throws Exception {
		try {
			BufferedReader dbClub = new BufferedReader(new FileReader("club.db"));
			StringBuffer inputBuffer = new StringBuffer();
			String l;
			//cargo archivo en buffer para sobreescribir la linea
			while ((l = dbClub.readLine()) != null) {
				inputBuffer.append(l);
				inputBuffer.append('\n');
			}
			inputBuffer.append("e,"+evento.getNombre()+","+evento.getDia()+","+evento.getHoraInicio()+","+evento.getHoraFin()+","+evento.getLugar()+","+evento.getArancel()+","+evento.getResponsable()+"\n");
			dbClub.close();
			String dbStr = inputBuffer.toString();

			FileOutputStream dbClub2 = new FileOutputStream("club.db");
			dbClub2.write(dbStr.getBytes());
			dbClub2.close();
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	//Lectura de base de datos

	public boolean leerDB() throws Exception {
		this.flagCargandoDb=true;
		
		BufferedReader dbClub = new BufferedReader(new FileReader("club.db"));
		StringBuffer inputBuffer = new StringBuffer();
		String l3;
		//cargo archivo en buffer para sobreescribir la linea
		while ((l3 = dbClub.readLine()) != null) {
			inputBuffer.append(l3);
			inputBuffer.append('\n');
		}
		dbClub.close();
		String dbStr = inputBuffer.toString();
		String lines[] = dbStr.split("\\r?\\n");
		String ii[],jj[];

		for(String i : lines) {
			ii=i.split(",");
			if(ii[0].equals("d")){
				for(String j : lines) {
					jj=j.split(",");
					if(jj[0].equals("p") && Integer.parseInt(jj[4]) == Integer.parseInt(ii[7])){
						agregarProfesor(Long.parseLong(jj[1]), jj[2], jj[3], Float.parseFloat(jj[5]), Integer.parseInt(jj[4]));

						agregarDeporte(
							ii[1],
							DayOfWeek.valueOf(ii[2]),
							LocalTime.of(Integer.parseInt(ii[3].split(":")[0]), Integer.parseInt( ii[3].split(":")[1] )), 
							LocalTime.of(Integer.parseInt(ii[4].split(":")[0]), Integer.parseInt( ii[4].split(":")[1] )),
							ii[5], 
							Float.parseFloat(ii[6]),
							traerProfesor(Integer.parseInt(ii[7])), 
							Integer.parseInt(ii[8])
						);
					}
				}
			}
		}

		for(String i : lines) {
			ii=i.split(",");
			if(ii[0].equals("s")){
				//l[2] contiene el nombre de la supuesta actividad,
				agregarSocio(Long.parseLong(ii[1]), ii[3], ii[4], Float.parseFloat(ii[6]),Integer.parseInt(ii[5]));
				if( ii[2] != null && !ii[2].equals("") ){
					agregarSocioDeporte(traerSocioDni(Long.parseLong(ii[1])),traerDeporte(ii[2]));
				}
			}
		}

		this.flagCargandoDb=false;
		return true;
	}
}