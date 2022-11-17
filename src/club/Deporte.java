package club;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Deporte extends Actividad {
	private Profesor profesor;
	private int cupo;
	private List<Socio> lstSocios = new ArrayList<Socio>();
	
	//Constructor
	public Deporte(String nombre, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String lugar, float arancel, 
			 Profesor profesor, int cupo, List<Socio> lstSocios) {
		super(nombre, fecha, horaInicio, horaFin, lugar, arancel);
		this.profesor = profesor;
		this.cupo = cupo;
		this.lstSocios = lstSocios;
	}

	//Getters & Setters
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public List<Socio> getLstSocios() {
		return lstSocios;
	}
	public void setLstSocios(List<Socio> lstSocios) {
		this.lstSocios = lstSocios;
	}

	//toString
	@Override
	public String toString() {
		return "\n ----- DEPORTE ----- \n PROFESOR:  " + profesor + ", CUPOS: " + cupo + ", LISTA SOCIOS: " + lstSocios + ", NOMBRE: " + nombre + ", FECHA: " + fecha
				+ ", HORA INICIO: " + horaInicio + ", HORA FIN:" + horaFin + ", LUGAR: " + lugar + ", ARANCEL: " + arancel
				+ "\n";
	}
	
	//Metodos
	
	
}
