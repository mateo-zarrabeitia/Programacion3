package Entregable;

public class Main {

	public static void main(String[] args) {

		List lista1 = new List();
		List lista2 = new List();

		lista1.insertFront(1);
		lista1.insertFront(2);
		lista1.insertFront(3);
		lista1.insertFront(4);
		lista1.insertFront(5);
		lista1.insertFront(6);
		
		lista2.insertFront(2);
		lista2.insertFront(4);
		
		List resultado = mezcla(lista1,lista2);
		
		MiIterator iterador = resultado.iterator();
		
		System.out.println("Recorro la lista resultante");
		
		while(iterador.hasNext())
			System.out.println("Elemento: " + iterador.next());

	}
	
	public static List mezcla(List l1, List l2){
		List aux = new List();
		for (Object info: l1) {
			if (!l2.contains(info)){
				aux.insertFront(info);
			}
		}
		return aux;
	}
	
	

}
