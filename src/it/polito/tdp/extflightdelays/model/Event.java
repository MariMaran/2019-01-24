package it.polito.tdp.extflightdelays.model;

public class Event implements Comparable<Event> {
	String stato;
	Turista t;
	Integer giorno;

	public Event(String stato, Turista t, int i) {
		this.stato=stato;
		this.t=t;
		this.giorno=i;
	}

	@Override
	public int compareTo(Event o) {
		return this.giorno.compareTo(o.giorno);
	}

}
