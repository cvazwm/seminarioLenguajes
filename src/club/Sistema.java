package club;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Sistema {
	private List<Persona> lstPersonas = new ArrayList<Persona>();
	private List<Actividad> lstActividades = new ArrayList<Actividad>();
	private int legajoNuevo, carnetNuevo;
	
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
	
	public boolean agregarSocioDeporte(Socio socio, Deporte actividad)throws Exception {
		if((actividad.getCupo() + 1) > actividad.getLstSocios().size())throw new Exception("ERROR: No hay cupo disponible para la actividad.");

		return actividad.agregarSocio(socio);
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

		return lstPersonas.add(profesor);
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
		lstPersonas.add(socio);
		return socio.getCarnet();
	}
	
	public boolean eliminarPersona(long dni)throws Exception {
		Persona persona = traerPersona(dni);
		if(persona == null)throw new Exception("ERROR: persona no esta en la lista.");
		
		return lstPersonas.remove(persona);
	}
	
	public boolean eliminarProfesor(int nroLegajo)throws Exception {
		Profesor profesor = traerProfesor(nroLegajo);
		if(profesor == null)throw new Exception("ERROR: profesor no esta en la lista.");
		
		return lstPersonas.remove(profesor);
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
	
	public Actividad traerActividad(LocalDate fecha, LocalTime horaInicio, String lugar) {
		Actividad actividad = null;
		
		int i = 0;
		while((i < lstActividades.size()) && (actividad == null)) {
			if((lstActividades.get(i).getFecha().equals(fecha)) && (lstActividades.get(i).getHoraInicio().equals(horaInicio)) &&
					(lstActividades.get(i).getLugar().equals(lugar))) {
				actividad = lstActividades.get(i);
			}
			i++;
		}
		
		return actividad;
	}
	
	public List<Actividad> traerActividades(LocalDate fecha){
		List<Actividad> listActividades = new ArrayList<Actividad>();
		
		for(int i = 0; i < lstActividades.size(); i++) {
			if((lstActividades.get(i).getFecha().equals(fecha))) {
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

	public boolean agregarDeporte(String nombre, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String lugar, 
			float arancel, Profesor profesor, int cupo)throws Exception {
		
		Actividad actividad = traerActividad(fecha, horaInicio, lugar);
		if(actividad != null)throw new Exception("ERROR: fecha, horario y lugar reservado.");
		
		Deporte deporte = new Deporte(nombre, fecha, horaInicio, horaFin, lugar, arancel, profesor, cupo);
		
		return lstActividades.add(deporte);
	}
	
	public boolean agregarEvento(String nombre, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String lugar, 
			float arancel, Persona responsable)throws Exception {
		
		Actividad actividad = traerActividad(fecha, horaInicio, lugar);
		if(actividad != null)throw new Exception("ERROR: fecha, horario y lugar reservado.");
		
		Evento evento = new Evento(nombre, fecha, horaInicio, horaFin, lugar, arancel, responsable);
		
		return lstActividades.add(evento);
	}
	
}
