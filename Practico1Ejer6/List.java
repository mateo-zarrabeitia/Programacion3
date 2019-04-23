package Entregable;

public class List implements Iterable<Object> {

	private Node first;
	private int size;
	
	public List() {
		this.first = null;
		this.size = 0;
	}
	
	public void insertFront(Object info) {
		Node nuevoNodo = new Node(info,this.first);
		this.first = nuevoNodo;
		this.size++;
	}
	
	public Object removeFront() {
		Object info = this.first.getInfo();
		this.first = this.first.getNext();
		this.size--;
		return info;
	}
	
	public Object get(int index) {
		int contador = 0; 
		Node temporal = this.first;
		while (contador < index) {
			contador++;
			temporal = temporal.getNext();
		}
		
		return temporal.getInfo();
	}
	public int count() {
		int contador = 0;
		
		Node temporal = this.first;
		while (temporal != null) {
			contador++;
			temporal = temporal.getNext();
		}
		
		return contador;
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return this.first == null;
	}
	
	public boolean contains(Object n) {
		Node aux = first;
		while(aux != null){
			if(aux.getInfo().equals(n)) {
				return true;
			}
			aux = aux.getNext();
		}
		return false;
	}

	@Override
	public MiIterator iterator() {
		return new MiIterator(this.first);
	}
	
}
