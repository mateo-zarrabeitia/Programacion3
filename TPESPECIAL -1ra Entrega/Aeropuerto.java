import java.util.ArrayList;
import java.util.List;
 
public class Aeropuerto {
    private String nombre;
    private String pais;
    private String ciudad;
    private String estado;
    private List<Ruta> rutas;
 
    public Aeropuerto(String nombre,String ciudad, String pais) {
    	this.ciudad = ciudad;
    	this.nombre = nombre;
        this.pais = pais;
        this.estado = "noVisitado";
        
    }
 
    public String getCiudad() {
        return ciudad;
    }
 
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
 
    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<Ruta> getRutas() {
        return rutas;
    }
 
    public void addRuta(Ruta ruta) {
        if (rutas == null) {
        	rutas = new ArrayList<>();
        }
        rutas.add(ruta);
    }
    
    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
    @Override
    public String toString() {
        return this.nombre;
    }
}