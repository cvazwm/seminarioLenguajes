package club;

import java.time.LocalTime;
import java.time.DayOfWeek;

public class Evento extends Actividad {
	private Persona responsable;

	//Constructor
	public Evento(String nombre, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, String lugar, float arancel, 
			 Persona responsable) {
		super(nombre, dia, horaInicio, horaFin, lugar, arancel);
		this.responsable = responsable;
	}

	//Getter & Setter
	public Persona getResponsable() {
		return responsable;
	}
	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}

	//toString
	@Override
	public String toString() {
		return "\n ----- EVENTO ----- \n RESPONSABLE: " + responsable + ", NOMBRE: " + nombre + ", DIA DE LA SEMANA: " + dia + ", HORA INICIO: "
				+ horaInicio + ", HORA FIN: " + horaFin + ", LUGAR: " + lugar + ", ARANCEL: " + arancel + "\n";
	}
	
	
}
