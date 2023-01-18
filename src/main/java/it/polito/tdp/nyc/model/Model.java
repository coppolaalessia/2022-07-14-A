package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	private NYCDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	private List<Collegati> collegati;
	Double pesoMedio;

	public Model() {
		this.dao = new NYCDao();
	}
		
	public String creaGrafo(String borgo) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.collegati= new ArrayList<Collegati>();
		
		Map<String, String> ssid;
		int peso;
		
		Graphs.addAllVertices(this.grafo,this.dao.getVertici(borgo));
		
		for(String v1: this.grafo.vertexSet()) {
			for(String v2: this.grafo.vertexSet()) {
				if(!v1.equals(v2)) {
					ssid = new HashMap<>();
					ssid.putAll(this.dao.getListSSID(borgo, v1));
					ssid.putAll(this.dao.getListSSID(borgo, v2));
					
					peso = ssid.size();
					if(peso!=0) {
						Graphs.addEdge(this.grafo, v1, v2, peso);
						this.collegati.add(new Collegati(v1,v2,peso));
					}
				}
			}
		}
		return "Grafo creato con "+this.grafo.vertexSet().size()+
				" vertici e "+this.grafo.edgeSet().size()+" archi\n";
	}
	
	public List<Collegati> getCollegati(){
		return this.collegati;
	}
	
	public List<Collegati> getVerticiPesoMaggioreDiN(){
		List<Collegati> res = new ArrayList<Collegati>();
		List<Collegati> collegati = new ArrayList<Collegati>(this.getCollegati());
		int peso=0;
		this.pesoMedio=0.0;
		
		//calcolo peso medio
		for(Collegati c: collegati) {
			peso+= c.getPeso();
		}
		pesoMedio= (double)peso/collegati.size();
		
		
		//aggiungo vertici con peso > peso medio
		for(Collegati c: collegati) {
			if(c.getPeso()>pesoMedio) {
				res.add(new Collegati(c.getVertice1(),c.getVertice2(),c.getPeso()));
			}
		}
		return res;
		
	}
	
		
	public List<String> getVertici(){
		List<String> res = new ArrayList<String>();
		res.addAll(this.grafo.vertexSet());
		
		return res;
	}
	
	public List<String> getBorghi(){
		return this.dao.getBorghi();
	}
	
	public Double getPesoMedio() {
		return pesoMedio;
	}

	public void setPesoMedio(Double pesoMedio) {
		this.pesoMedio = pesoMedio;
	}
}
