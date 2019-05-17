import java.util.ArrayList;

public class Ruta {
    private Aeropuerto origen;
    private Aeropuerto destino;
    private double distancia;
    private String cabotaje;
    private ArrayList<Vuelo> vuelos;
 
    public Ruta(Aeropuerto origen, Aeropuerto destino, double distancia, String cabotaje) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.cabotaje = cabotaje;
    }
    
 
    public Aeropuerto getOrigen() {
        return origen;
    }
 
    public void setOrigen(Aeropuerto origin) {
        this.origen = origin;
    }
 
    public Aeropuerto getDestino() {
        return destino;
    }
 
    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }
 
    public double getDistancia() {
        return distancia;
    }
 
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    public String getCabotaje() {
    	return this.cabotaje;
    }
 
    public ArrayList<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(ArrayList<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}
	

	@Override
    public String toString() {
        return "\n Edge [origin=" + origen + ", destination=" + destino + ", distance="
                + distancia + "]";
    }
 
}