
public class Reserva {
	private Aeropuerto origen;
	private Aeropuerto destino;
	private String nombreAerolinea;
	private int asientosReservados;

	public Reserva(Aeropuerto origen, Aeropuerto destino, String nombreAerolinea, int asientosReservados ) {
		this.origen = origen;
		this.destino =destino;
		this.nombreAerolinea =nombreAerolinea;
		this.asientosReservados=asientosReservados;
		
	}
	public Aeropuerto getOrigen() {
		return this.origen;
	}

	public Aeropuerto getDestino() {
		return this.destino;
	}

	public String getNombreAerolinea() {
		return this.nombreAerolinea;
	}

	public int getAsientosReservados() {
		return this.asientosReservados;
	}

	public void setOrigen(Aeropuerto nuevoOrigen) {
		this.origen= nuevoOrigen;
	}

	public void setDestino(Aeropuerto nuevoDestino) {
		this.destino = nuevoDestino;
	}

	public void setNombreAerolinea(String nuevoNombre) {
		this.nombreAerolinea = nuevoNombre;
	}

	public void setAsientosReservados(int nuevosAsientosReservados) {
		this.asientosReservados = nuevosAsientosReservados;
	}
	
	 @Override
	    public String toString() {
	        return "\n Reserva: " + origen + " a " + destino + " - Aerolinea: "
	                + nombreAerolinea + " asientos reservados= " + asientosReservados;
	    }

}
