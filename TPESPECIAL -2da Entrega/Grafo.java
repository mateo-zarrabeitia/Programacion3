import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	public String recorridoGreedy (String Origen) {
		ArrayList<Aeropuerto> cSolucion = new ArrayList<>();
		double distancia = 0;
		for (Aeropuerto aeropuerto : aeropuertos) {
			if (aeropuerto.getNombre().equals(Origen)) {
				cSolucion.add(aeropuerto);
				return Greedy(aeropuerto, distancia, cSolucion, aeropuertos.size());
			}
		}
		return "No se encontro Aeropuerto Origen";
	}

	public String Greedy(Aeropuerto Origen, double distancia, ArrayList<Aeropuerto> cSolucion, int totalAeropuertos){
		String salida = "";
		if (Origen.getRutas() != null) {
			List<Ruta> aux = Origen.getRutas();
			listarDestinosPosibles(aux,cSolucion,Origen);
			Collections.sort(aux);
			for (Ruta ruta : aux) {
				if (!cSolucion.contains(ruta.getDestino())) {
					cSolucion.add(ruta.getDestino());
					distancia += ruta.getDistancia();
					System.out.println("Elijo "+ruta.getDestino()+" y mi distancia actual es: "+distancia);
					System.out.println("------------------------------");
					salida = Greedy(ruta.getDestino(), distancia, cSolucion,totalAeropuertos);
					break;
				} else if (ruta.getDestino().equals(cSolucion.get(0)) && cSolucion.size() == totalAeropuertos) {
					distancia += ruta.getDistancia();
					for (Aeropuerto a : cSolucion) {
						salida += " -> "+a.getNombre();
					}
					return salida+"\nLa distancia del recorrido es: "+distancia;
				}
			}
		}
		return salida;
	}
	public void listarDestinosPosibles(List<Ruta> destinos, ArrayList<Aeropuerto> cSolucion,Aeropuerto Origen) {
		boolean entro = false;
		System.out.println("Destinos posibles desde "+Origen.getNombre());
		for (Ruta ruta : destinos) {
			if (!cSolucion.contains(ruta.getDestino())) {
				System.out.print(ruta.getDestino()+" -> "+ruta.getDistancia()+"		");
				entro = true;
			}
		}
		if (!entro) {
			for (Ruta ruta : destinos) {
				if (ruta.getDestino().equals(cSolucion.get(0))) {
					System.out.print(ruta.getDestino()+" -> "+ruta.getDistancia()+"		");
					break;
				}
			}
		}
		System.out.println("\n ------------------------------");
	}

	public String recorridoBacktraking (String Origen) {
		ArrayList<Aeropuerto> cSolucion = new ArrayList<>();
		ArrayList<Object> solucionValida = new ArrayList<>();
		solucionValida.add(0,9999999.99);
		for (Aeropuerto aeropuerto : aeropuertos) {
			if (aeropuerto.getNombre().equals(Origen)) {
				solucionValida = Backtraking(aeropuerto, 0.0, cSolucion, solucionValida, aeropuertos.size(), aeropuerto);
				System.out.println("La solucion optima es: ");
				System.out.println(solucionValida.get(1));
				System.out.println("Su distancia total es: "+solucionValida.get(0));
			}
			
		}
		return "";
	}

	public ArrayList<Object> Backtraking(Aeropuerto Origen, double distancia, ArrayList<Aeropuerto> cSolucion, ArrayList<Object> solucionValida, int totalAeropuertos, Aeropuerto aeropuertoInicio){
		if (!cSolucion.isEmpty() && cSolucion.get(cSolucion.size()-1).equals(aeropuertoInicio) && cSolucion.size() == totalAeropuertos && distancia < (Double)solucionValida.get(0)) {
			System.out.println("------------------------------");
			System.out.println("Solucion valida: ");
			System.out.println(cSolucion+"con distancia: "+distancia);
			System.out.println("------------------------------");
			solucionValida.remove(0);
			solucionValida.add(0,distancia);
			solucionValida.add(1,cSolucion.clone());
		} else {
			if (Origen.getRutas() != null) {
				for (Ruta ruta : Origen.getRutas()) {
					if (!cSolucion.contains(ruta.getDestino()) && !ruta.getDestino().equals(aeropuertoInicio) && distancia < (Double)solucionValida.get(0)) {
						cSolucion.add(ruta.getDestino());
						distancia += ruta.getDistancia();
						System.out.println(cSolucion+" -> distancia actual: "+distancia);
						Backtraking(ruta.getDestino(), distancia, cSolucion, solucionValida, totalAeropuertos, aeropuertoInicio);
						distancia -= ruta.getDistancia();
						cSolucion.remove(cSolucion.size()-1);
					} else if (cSolucion.size() == totalAeropuertos - 1 && ruta.getDestino().equals(aeropuertoInicio)) {
						cSolucion.add(ruta.getDestino());
						distancia += ruta.getDistancia();
						solucionValida =Backtraking(ruta.getDestino(), distancia, cSolucion, solucionValida, totalAeropuertos, aeropuertoInicio);
						distancia -= ruta.getDistancia();
						cSolucion.remove(cSolucion.size()-1);

					}
				}
			}
		}
		return solucionValida;
	}

	public boolean verificarSolucion(ArrayList<Aeropuerto> cSolucion) {
		return true;
	}



}