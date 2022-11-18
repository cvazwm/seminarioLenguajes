package club;

public class Socio extends Persona {
	private int carnet;
	private float cuota;
	
	//Constructor
	public Socio(long dni, String nombre, String apellido, int carnet, float cuota) {
		super(dni, nombre, apellido);
		this.carnet = carnet;
		this.cuota = cuota;
	}

	//Getters & Setters
	public int getCarnet() {
		return carnet;
	}
	public void setCarnet(int carnet) {
		this.carnet = carnet;
	}

	public float getCuota() {
		return cuota;
	}
	public void setCuota(float cuota) {
		this.cuota = cuota;
	}

	//toString
	@Override
	public String toString() {
		return "\n CARNET: " + carnet + ", CUOTA: " + cuota + ", DNI: " + dni + ", NOMBRE: " + nombre
				+ ", APELLIDO: " + apellido;
	}
	
}
