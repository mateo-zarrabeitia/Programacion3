import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SistemaAeropuerto {
	private static final String PATCH = "C:/Users/Mateo/eclipse-workspace/TPE-Programacion3/src/datasets/";
	
	public static void main(String[] args) {
			
		 	Grafo grafo = new Grafo();		 	
		 	ArrayList<Aeropuerto> aux = cargarAeropuertos();
		 	aux = cargarRutas(aux);
		 	grafo.addNodos(aux);
		 	grafo.setReservas(cargarReservas(aux));
		 	
		 	int numero = 1;


			//INTERFAZ
		 	while(numero !=0) {
					try {
						BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
						System.out.println ("Ingrese la opcion del servicio deseado\r\n"+
										 	"1. Listar todos los aeropuertos.\r\n" + 
											"2. Listar todas las reservas realizadas.\r\n" + 
											"3. Servicio 1: Verificar vuelo directo.\r\n" + 
											"4. Servicio 2: Obtener vuelos sin aerolínea.\r\n" + 
											"5. Servicio 3: Vuelos disponibles. \r\n" +
											"6. Recorrido Greedy. \r\n" +
											"7. Recorrido Backtraking. \r\n" +
											"Para detener ingrese 0");
						numero = new Integer(entrada.readLine());
						switch (numero) {
						case 1:
							System.out.println(grafo.listarAeropuertos());
							entrada.readLine();
							break;
						case 2:
							System.out.println(grafo.listarReservas());
							entrada.readLine();
							break;
						case 3:
							System.out.println("Ingrese nombre Aeropuerto Origen");
							String origen = entrada.readLine();
							System.out.println("Ingrese nombre Aeropuerto Destino");
							String destino = entrada.readLine();
							System.out.println("Ingrese nombre Aerolinea Deseada");
							String aerolinea = entrada.readLine();
							System.out.println(grafo.servicio1(origen, destino, aerolinea));
							entrada.readLine();
							break;
						case 4:
							System.out.println("Ingrese nombre Aeropuerto Origen");
							origen = entrada.readLine();
							System.out.println("Ingrese nombre Aeropuerto Destino");
							destino = entrada.readLine();
							System.out.println(grafo.servicio2(origen,destino));
							entrada.readLine();
							break;
						case 5:
							System.out.println("Ingrese nombre Pais Origen");
							origen = entrada.readLine();
							System.out.println("Ingrese nombre Pais Destino");
							destino = entrada.readLine();
							System.out.println(grafo.servicio3(origen, destino));
							entrada.readLine();
							break;
						case 6:
							System.out.println("Ingrese nombre Aeropuerto Origen");
							origen = entrada.readLine();
							System.out.println(grafo.recorridoGreedy(origen));
							entrada.readLine();
							break;
						case 7:
							System.out.println("Ingrese nombre Aeropuerto Origen");
//							origen = entrada.readLine();
							System.out.println(grafo.recorridoBacktraking("Jorge Newbery"));
							entrada.readLine();
							break;
						}
					}
					catch (Exception exc ) {
						System.out.println( exc );
						numero = 1;
					}
		 	}
		 	
	}
	
	
	
	
	
	public static ArrayList<Aeropuerto> cargarAeropuertos() {
		ArrayList<Aeropuerto> aux = new ArrayList<Aeropuerto>();
		String csvFile = PATCH+"Aeropuertos.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                String nombre = items[0];
                String ciudad = items[1];
                String pais = items[2];
                
                aux.add(new Aeropuerto(nombre, ciudad, pais));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aux;
	}
	
	public static ArrayList<Aeropuerto> cargarRutas(ArrayList<Aeropuerto> aeropuertos) {
		String csvFile = PATCH+"Rutas.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                String origen = items[0];
                String destino = items[1];
                String distancia = items[2];
                String cabotaje = items[3];
                Aeropuerto d = null;
                for (Aeropuerto a : aeropuertos) {
					if (a.getNombre().equals(destino)) {
						d = a;
					}
				}
                for (Aeropuerto a : aeropuertos) {
					if(a.getNombre().equals(origen)) {
						ArrayList<Vuelo> vuelos = cargarVuelos(a,d,items[4]);
						Ruta aux = new Ruta(a, d, Double.parseDouble(distancia), cabotaje);
						aux.setVuelos(vuelos);
						a.addRuta(aux);
					}
				}

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aeropuertos;
	}
	
	public static ArrayList<Vuelo> cargarVuelos(Aeropuerto o, Aeropuerto d, String vuelo) {
		
		ArrayList<Vuelo> v = new ArrayList<Vuelo>();
		String aux = vuelo.replace("{", "");
		aux = aux.replace("}", "");
        String[] aux2 = aux.split(",");
        
        for (String s : aux2) {
        	String[] aux3 = s.split("-");
        	int cantReserva = buscarReserva(o,d,aux3[0]);
        	v.add(new Vuelo(aux3[0], Integer.parseInt(aux3[1]),cantReserva));
        }
		return v;
	}
	
	public static Integer buscarReserva(Aeropuerto o, Aeropuerto d, String nAerolinea)  {
		String csvFile = PATCH+"Reservas.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                String origen = items[0];
                String destino = items[1];
                String nombreAerolinea = items[2];
                String cantidadReservas = items[3];
                Aeropuerto or = null;
                Aeropuerto des = null;
                if (origen.equals(o.getNombre()) && destino.equals(d.getNombre()) && nombreAerolinea.equals(nAerolinea)) {
					return Integer.parseInt(cantidadReservas);
				}

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return 0;
	}
	
	
	public static ArrayList<Reserva> cargarReservas(ArrayList<Aeropuerto> aeropuertos) {
		ArrayList<Reserva> aux = new ArrayList<Reserva>();
		String csvFile = PATCH+"Reservas.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                String origen = items[0];
                String destino = items[1];
                String nombreAerolinea = items[2];
                String cantidadReservas = items[3];
                Aeropuerto or = null;
                Aeropuerto des = null;
                for (Aeropuerto a : aeropuertos) {
					if (a.getNombre().equals(origen)) {
						or = a;
					}
					if (a.getNombre().equals(destino)) {
						des = a;
					}
				}
                aux.add(new Reserva(or, des, nombreAerolinea, Integer.parseInt(cantidadReservas)));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aux;
	}

}
