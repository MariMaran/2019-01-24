package it.polito.tdp.extflightdelays.model;

public class Turista {
Integer id;
String stato;
public Turista(Integer id) {
	this.id = id;
}
public Integer getId() {
	return id;
}
public String getStato() {
	return stato;
}
public void setId(Integer id) {
	this.id = id;
}
public void setStato(String stato) {
	this.stato = stato;
}

}
