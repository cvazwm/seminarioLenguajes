package club;

import java.time.LocalTime;
import java.time.DayOfWeek;

public abstract class Actividad {
	protected int id;
	protected String nombre;
	protected DayOfWeek dia;
	protected LocalTime horaInicio;
	protected LocalTime horaFin;
	protected String lugar;
	protected float arancel;
	
	//Constructor
	public Actividad(String nombre, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, String lugar,
			float arancel) {
		super();
		this.nombre = nombre;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.lugar = lugar;
		this.arancel = arancel;
	}

	//Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DayOfWeek getDia() {
		return dia;
	}
	public void setDia(DayOfWeek dia) {
		this.dia = dia;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public float getArancel() {
		return arancel;
	}
	public void setArancel(float arancel) {
		this.arancel = arancel;
	}

	//toString
	@Override
	public String toString() {
		return "ID: " + id + ", NOMBRE: " + nombre + ", DIA DE LA SEMANA: " + dia + ", HORA INICIO: " + horaInicio + ", HORA FIN: " + horaFin
				+ ", LUGAR: " + lugar + ", ARANCEL: " + arancel + "\n";
	}
	
	
}
