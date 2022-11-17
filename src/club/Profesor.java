package club;

public class Profesor extends Persona {
	private int nroLegajo;
	private float sueldo;
	
	//Constructor
	public Profesor() {}
	public Profesor(long dni, String nombre, String apellido, int nroLegajo, float sueldo) {
		super(dni, nombre, apellido);
		this.nroLegajo = nroLegajo;
		this.sueldo = sueldo;
	}

	//Getters & Setters
	public int getNroLegajo() {
		return nroLegajo;
	}
	public void setNroLegajo(int nroLegajo) {
		this.nroLegajo = nroLegajo;
	}

	public float getSueldo() {
		return sueldo;
	}
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	@Override
	public String toString() {
		return "\n ----- Profesor ----- \n LEGAJO: " + nroLegajo + ", SUELDO: " + sueldo + ", DNI: " + dni + ", NOMBRE: " + nombre
				+ ", APELLIDO: " + apellido + "\n";
	}
	
	
	
}
