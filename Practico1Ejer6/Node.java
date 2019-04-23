package Entregable;


public class Node {

	private Object info;
	private Node next;

	public Node() {
		this(null,null);
	}
	
	public Node(Object info) {
		this(info,null);
	}
	
	public Node(Object info, Node next) {
		this.info = info;
		this.next = next;
	}
	
	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

}
