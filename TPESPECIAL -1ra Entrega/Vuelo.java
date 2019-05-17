
public class Vuelo {
	private String nombre_aerolinea;
	private int capacidad;
	private int cantidadReservada;
	
	public Vuelo(String nombre, int capacidad,int cantidadReservada) {
		this.nombre_aerolinea = nombre;
		this.capacidad = capacidad;
		this.cantidadReservada = cantidadReservada;
	}

	public String getNombreAerolinea() {
		return this.nombre_aerolinea;
	}
	
	public int getCapacidad() {
		return this.capacidad;
	}
	
	public void setNombreAerolinea(String nuevoNombre) {
		 this.nombre_aerolinea = nuevoNombre;
	}
	
	public void setCapacidad(int nuevaCapacidad) {
		 this.capacidad = nuevaCapacidad;
	
	}

	public int getCantidadReservada() {
		return cantidadReservada;
	}

	public void setCantidadReservada(int cantidadReservada) {
		this.cantidadReservada = cantidadReservada;
	}
	
	public boolean hayCapacidad(){
		int disponibilidad = getCapacidad()-getCantidadReservada();
		if(disponibilidad>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String toString() {
		return "Vuelo [nombre_aerolinea=" + nombre_aerolinea + ", capacidad=" + capacidad + ", cantidadReservada="
				+ cantidadReservada + "]";
	}
	
	
	
}