package Entregable2;

import java.util.ArrayList;

public class ArbolBinario {

	Integer valor;
	ArbolBinario left, right;
	
	public ArbolBinario() {
		valor = null;
		left = right = null;
	}
	
	public ArbolBinario(Object o) {
		Integer val = (Integer) o;
		valor = val;
		left = right = null;
	}
	
	public void insert(Object o) {
		Integer n = (Integer) o;
		if (valor == null ) {
			this.valor = n;
		} else if(n <= this.valor ) {
			if(this.left == null) {
				this.left = new ArbolBinario(n);
			} else {
				this.left.insert(n);
			}
		} else {
			if(this.right == null) {
				this.right = new ArbolBinario(n);
			} else {
				this.right.insert(n);
			}
		}
	}
	
	public ArrayList<ArbolBinario> getFrontera() {
		ArrayList<ArbolBinario> hojas = new ArrayList<ArbolBinario>();
		if(this.valor != null) {
			if (this.left != null) {
				hojas.addAll(this.left.getFrontera());
			} 
			if (this.right != null) {
				hojas.addAll(this.right.getFrontera());
			}
			if (this.left == null && this.right ==null) {
				hojas.add(this);
			}
		}
		return hojas;
	}
	
}
