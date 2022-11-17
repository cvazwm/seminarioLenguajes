package club;

public abstract class Persona {
	protected long dni;
	protected String nombre;
	protected String apellido;
	
	//Constructor
	public Persona() {}
	public Persona(long dni, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	//Getters & Setters
	public long getDni() {
		return dni;
	}
	public void setDni(long dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	//toString
	@Override
	public String toString() {
		return "DNI: " + dni + ", NOMBRE: " + nombre + ", APELLIDO: " + apellido + "\n";
	}
	
	
}
