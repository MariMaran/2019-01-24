package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {
	int turisti=10;
	int Giorni=10;
	List<Turista> listT;
	int giorno;
	int contatoreEventi;
	Map<String, Integer> mappa;
	
	PriorityQueue<Event> coda;
	
	Graph<String, DefaultWeightedEdge> grafo;
	Model model;
	
	public void setTuristi(int turisti) {
		this.turisti = turisti;
	}
	public void setGiorni(int giorni) {
		Giorni = giorni;
	}
	
	public Simulatore(Graph<String, DefaultWeightedEdge> grafo, Model model) {
		this.grafo=grafo;
		this.model=model;
		mappa=new TreeMap();
		List<String> stati=new ArrayList(grafo.vertexSet());
		for(String s: stati)
			mappa.put(s,0);
			
	}
	
	public void init(String partenza) {
		giorno=1;
		contatoreEventi=1;
		listT=new ArrayList();
		coda=new PriorityQueue();
		for(int i=0;i<turisti;i++) {
			listT.add(new Turista(i));
		}
		double p=0.0;
		double totale=0.0;
		List<VerticeEPeso> verPes=model.getConfinanti(partenza);
		List<StatoProb> sProb=new ArrayList();
		for(VerticeEPeso vp: verPes) {
			totale+=vp.peso;
		}
		for(VerticeEPeso vp: verPes) {
			p+=(vp.peso/totale);
			System.out.println(p);
			sProb.add(new StatoProb(vp.stato,p));
		}
		
		for(Turista t: listT) {
			double res=Math.random();
			double inf=0.0;
			for(StatoProb sp: sProb) {
				if(res>=inf&&res<sp.prob) {
				Event e =new Event(sp.stato,t,giorno);
				coda.add(e);
				}
				inf=sp.prob;
			}
		}
		
	}
	
	public void simula() {
		while(!coda.isEmpty()) {
			Event e=coda.poll();
			this.processEvent(e);
		}
	}
	
	
	private void processEvent(Event e) {
		if(e.giorno==10) {
			Integer n=mappa.get(e.stato);
			n++;
			mappa.put(e.stato,n);
			System.out.println(e.stato+" "+mappa.get(e.stato));
		}
		if(e.giorno<Giorni) {
			System.out.println(++contatoreEventi);
			System.out.println(e.stato+" "+e.t+" "+e.giorno);
		double p=0.0;
		double totale=0.0;
		List<VerticeEPeso> verPes=model.getConfinanti(e.stato);
		List<StatoProb> sProb=new ArrayList();
		for(VerticeEPeso vp: verPes) {
			totale+=vp.peso;
		}
		for(VerticeEPeso vp: verPes) {
			p+=(vp.peso/totale);
			sProb.add(new StatoProb(vp.stato,p));
		}
		double res=Math.random();
		double inf=0.0;
		for(StatoProb sp: sProb) {
			if(res>=inf&&res<sp.prob) {
			Event e2 =new Event(sp.stato,e.t,e.giorno+1);
			coda.add(e2);
			}
			inf=sp.prob;
		}
		
		}
		else return;
		
	}
	

}
