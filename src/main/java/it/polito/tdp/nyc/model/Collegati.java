package it.polito.tdp.nyc.model;

public class Collegati implements Comparable<Collegati> {
	private String vertice1;
	private String vertice2;
	private int peso;
	
	public String getVertice1() {
		return vertice1;
	}
	public void setVertice1(String vertice1) {
		this.vertice1 = vertice1;
	}
	public String getVertice2() {
		return vertice2;
	}
	public void setVertice2(String vertice2) {
		this.vertice2 = vertice2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public Collegati(String vertice1, String vertice2, int peso) {
		super();
		this.vertice1 = vertice1;
		this.vertice2 = vertice2;
		this.peso = peso;
	}
	
	
	  @Override public int compareTo(Collegati o) {
		  if(this.peso> o.getPeso()) {
			  return -1;
		  }
		  else if(this.peso<o.getPeso()) {
			  return 1;
		  }
		  else return 0;
	  }
	 
}
