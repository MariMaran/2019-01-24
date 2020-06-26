package it.polito.tdp.extflightdelays.model;

public class VerticeEPeso implements Comparable<VerticeEPeso> {
	String stato;
	Double peso;
	
	public VerticeEPeso(String stato, Double peso) {
		this.stato = stato;
		this.peso = peso;
	}

	@Override
	public int compareTo(VerticeEPeso arg0) {
		return -this.peso.compareTo(arg0.peso);
	}

	@Override
	public String toString() {
		return "VerticeEPeso [stato=" + stato + ", peso=" + peso + "]";
	}
	

}
