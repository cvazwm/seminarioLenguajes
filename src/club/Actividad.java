package club;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Actividad {
	protected int id;
	protected String nombre;
	protected LocalDate fecha;
	protected LocalTime horaInicio;
	protected LocalTime horaFin;
	protected String lugar;
	protected float arancel;
	
	//Constructor
	public Actividad() {}
	public Actividad(String nombre, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String lugar,
			float arancel) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
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

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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
		return "NOMBRE: " + nombre + ", ID: " + id +  ", FECHA: " + fecha + ", HORA INICIO: " + horaInicio + ", HORA FIN: " + horaFin
				+ ", LUGAR: " + lugar + ", ARANCEL: " + arancel + "\n";
	}
	
	
}
