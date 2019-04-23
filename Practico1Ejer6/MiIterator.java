package Entregable;


import java.util.Iterator;

public class MiIterator implements Iterator<Object> {

	private Node nodo;
	
	public MiIterator(Node first) {
		this.nodo = first;
	}
	

	@Override
	public boolean hasNext() {
		return this.nodo != null;
	}

	
	@Override
	public Object next() {
		Object info = this.nodo.getInfo();
		this.nodo = this.nodo.getNext();
		return info;
	}

}
