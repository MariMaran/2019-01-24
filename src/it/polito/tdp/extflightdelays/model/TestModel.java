package it.polito.tdp.extflightdelays.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		model.creaGrafo();
		System.out.println(model.grafo.edgeSet().size());
		System.out.println(model.grafo.vertexSet().size());
		//System.out.println(model.getStatiAlfabetico());
		System.out.println(model.getConfinanti("AK"));
		model.chiamaSimulatore("AK");
	}

}
