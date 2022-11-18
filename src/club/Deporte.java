package club;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;

public class Deporte extends Actividad {
	private Profesor profesor;
	private int cupo;
	private List<Socio> lstSocios = new ArrayList<Socio>();
	
	//Constructor
	public Deporte(String nombre, DayOfWeek fecha, LocalTime horaInicio, LocalTime horaFin, String lugar, float arancel, 
			 Profesor profesor, int cupo) {
		super(nombre, fecha, horaInicio, horaFin, lugar, arancel);
		this.profesor = profesor;
		this.cupo = cupo;
	}

	//Getters & Setters
	public Profesor getProfesor() {
		return this.profesor;
	}
	public boolean setProfesor(Profesor profesor) {
		this.profesor = profesor;

		if (this.profesor.equals(profesor))
		return true;
		else
		return false;
	}
	
	public int getCupo() {
		return this.cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public List<Socio> getLstSocios() {
		return this.lstSocios;
	}
	public void setLstSocios(List<Socio> lstSocios) {
		this.lstSocios = lstSocios;
	}
	
	public boolean agregarSocio(Socio socio) {
		this.lstSocios.add(socio);
		return true;
	}
	

	//toString
	@Override
	public String toString() {
		return " \n  NOMBRE: " + nombre + ", DIA DE LA SEMANA: " + dia
				+ ", HORA INICIO: " + horaInicio + ", HORA FIN:" + horaFin 
				+ ", LUGAR: " + lugar + ", ARANCEL: " + arancel + ", CUPOS: " + cupo + " \n"
				+ "        PROFESOR:  " + profesor.getNombre() 
				+ " \n" 
				+ "        LISTA SOCIOS:\n " 
				+ "                     "+lstSocios + " \n";
	}
	
	//Metodos
	
	
}
