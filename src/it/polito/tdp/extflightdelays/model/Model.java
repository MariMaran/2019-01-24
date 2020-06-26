package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
Graph<String,DefaultWeightedEdge> grafo;
ExtFlightDelaysDAO dao;
List<String> stati;

public Model() {
	dao=new ExtFlightDelaysDAO();
}


public void creaGrafo() {
	grafo=new DirectedWeightedPseudograph(DefaultWeightedEdge.class);
	stati=dao.loadAllStates();
	for(String s: stati) {
		grafo.addVertex(s);
	}
	List<Arco> archi=dao.getArchi();
	for(Arco a: archi) {
		DefaultWeightedEdge d=grafo.addEdge(a.s1, a.s2);
		grafo.setEdgeWeight(d, (double)a.peso);
		
		}
}

public List<String> getStatiAlfabetico(){
	Collections.sort(stati);
	return stati;
}

public List<VerticeEPeso> getConfinanti(String s){
	List<VerticeEPeso> res=new ArrayList();
	for(DefaultWeightedEdge d: grafo.edgesOf(s)) {
		VerticeEPeso v=new VerticeEPeso(Graphs.getOppositeVertex(grafo, d, s),grafo.getEdgeWeight(d));
		res.add(v);
	}
	Collections.sort(res);
	return res;
}

public void chiamaSimulatore(String partenza) {
	Simulatore s=new Simulatore(grafo,this);
	s.init(partenza);
	s.simula();
}


}
