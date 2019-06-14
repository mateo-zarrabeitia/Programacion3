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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aeropuerto other = (Aeropuerto) obj;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}
    
}