import java.util.ArrayList;

public class Grafo {

	private ArrayList<Aeropuerto> aeropuertos = new ArrayList<>();;
	private ArrayList<Reserva> reservas = new ArrayList<>();

	public void addNodo(Aeropuerto nodo) {
		aeropuertos.add(nodo);
	}

	public void addNodos(ArrayList<Aeropuerto> nodos) {
		aeropuertos.addAll(nodos);
	}

	public ArrayList<Aeropuerto> getNodos() {
		return aeropuertos;
	}

	@Override
	public String toString() {
		return "Graph [nodes=" + aeropuertos + "]";
	}

	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}

	public String listarAeropuertos() {
		String aux = "";
		for (Aeropuerto e : aeropuertos) {
			aux = aux + e.toString()+"\n";
		}
		return aux;
	}

	public String listarReservas() {
		String aux = "";
		for (Reserva e : reservas) {
			aux = aux + e.toString();
		}
		return aux;
	}

	public String servicio1(String origen, String destino, String aerolinea) {
		for (Aeropuerto aeropuerto : aeropuertos) {
			if (aeropuerto.getNombre().equals(origen)) {
				for (Ruta rutas : aeropuerto.getRutas()) {
					if (rutas.getDestino().getNombre().equals(destino)) {
						for (Vuelo vuelos : rutas.getVuelos()) {
							if (vuelos.getNombreAerolinea().equals(aerolinea)) {
								return "Existe el vuelo, los kilometros son: "+rutas.getDistancia()+" - Asientos disponibles: "+(vuelos.getCapacidad()-vuelos.getCantidadReservada());
							}
						}
					}
				}
			}
		}
		return "No existen vuelos directos entre "+origen+ " y "+destino+" - Con la aerolinea: "+aerolinea;
	}


	public String servicio2(String origen, String destino) {
		ArrayList<String> aux = new ArrayList<>();
		Integer escala = 0;
		Double distancia = 0.0;
		for (Aeropuerto origenObj : aeropuertos) {
			if (origenObj.getNombre().equals(origen)) {
				aux.addAll(DFS_Visit(origenObj, destino, escala, distancia,""));
				return aux.toString();
			}
		}
		return "No existen vuelos  entre "+origen+ " y "+destino;
	}

	private ArrayList<String> DFS_Visit(Aeropuerto origen, String destino, int t, Double distancia,String aeropuerto){
		ArrayList<String> aux = new ArrayList<>();
		origen.setEstado("visitandose"); 
		aeropuerto += " -> "+origen.getNombre();
		if (origen.getRutas() != null) {
			for (Ruta rutas : origen.getRutas()) {
				distancia += rutas.getDistancia();
				if (rutas.getDestino().getNombre().equals(destino)) {
					aux.addAll(listarVuelos(rutas.getVuelos(), t, distancia, aeropuerto +" -> "+rutas.getDestino().getNombre()));
				} else if (rutas.getDestino().getEstado().equals("noVisitado")) {
					t++;
					aux.addAll(DFS_Visit(rutas.getDestino(), destino, t, distancia, aeropuerto));
					t--;
				} 

				distancia -= rutas.getDistancia();
			}
			origen.setEstado("visitado");
		}
		return aux;
	}

	public ArrayList<String> listarVuelos(ArrayList<Vuelo> vuelos, Integer t, Double distancia,String aeropuerto) {
		ArrayList<String> aux = new ArrayList<>();
		for (Vuelo vuelo : vuelos) { 
			if (vuelo.hayCapacidad()) {
				aux.add("Desde: "+aeropuerto+" - Aerolinea: "+vuelo.getNombreAerolinea() + "- escalas: "+t+" total kilometros: "+distancia+"\n");
			}
		}
		return aux;
	}



	public String servicio3(String paisOrigen, String paisDestino) { 
		ArrayList<String> aux = new ArrayList<>();
		for (Aeropuerto aeropuerto : aeropuertos) {
			if (aeropuerto.getPais().equals(paisOrigen)) {
				aux.addAll(DFS(aeropuerto, paisDestino));
			}
		}
		if (!aux.isEmpty()) {
			return aux.toString();
		} else {
			return("No existen vuelos  entre "+paisOrigen+ " y "+paisDestino+" disponibles ");
		}
	}	


	public ArrayList<String> DFS(Aeropuerto paisOrigen, String paisDestino){
		ArrayList<String> aux = new ArrayList<>();
		for (Ruta rutas : paisOrigen.getRutas()) {
			if (rutas.getDestino().getPais().equals(paisDestino)) {
				for (Vuelo vuelos : rutas.getVuelos()) {
					if(vuelos.hayCapacidad()){
						aux.add("Aeropuerto origen: "+paisOrigen+
								" - Aeropuerto destino: "+rutas.getDestino().getNombre()+
								" - Aerolinea disponible: "+vuelos.getNombreAerolinea()+""
								+ " - Kilometros del vuelo: "+rutas.getDistancia()+"\n");
					}
				}
			}
		}
		return aux;
	}


}