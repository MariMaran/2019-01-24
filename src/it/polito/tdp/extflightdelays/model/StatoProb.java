package it.polito.tdp.extflightdelays.model;

public class StatoProb {
String stato;
Double prob;
public StatoProb(String stato, Double prob) {
	
	this.stato = stato;
	this.prob = prob;
}
public String getStato() {
	return stato;
}
public Double getProb() {
	return prob;
}

}
