package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private Graph<String,DefaultWeightedEdge> grafo;
	private GenesDao dao;
	public Model() {
		
		this.dao = new GenesDao();
	}
	
	public void creaGrafo ()
	{
		this.grafo= new SimpleWeightedGraph <>(DefaultWeightedEdge.class);
		if(this.grafo.vertexSet().size()==0) {
		Graphs.addAllVertices(this.grafo, dao.getVertici()); 
		
		for(Adiacenza a: dao.getArchi())
		{
			Graphs.addEdgeWithVertices(this.grafo, a.getL1(), a.getL2(), a.getPeso());
		}
		}
		
	}
	
	public List<String> getLocalizzazioni()
	{
		
		return dao.getVertici();
	}
	
	public int nVertivi()
	{
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi ()
	{
		return this.grafo.edgeSet().size();
	}
	
	public  List <Adiacenza>  getComponenteConnessa(String localizzazione)
	{	
		
		
		/*List<String> connessa = new ArrayList<>();
		connessa = Graphs.neighborListOf(this.grafo, localizzazione);*/
		//Map<String, Integer> map = new HashMap<String, Integer>();
		List<Adiacenza> archi = new ArrayList<Adiacenza>();
		
		for (DefaultWeightedEdge e : this.grafo.edgeSet()) {
			archi.add(new Adiacenza(this.grafo.getEdgeSource(e),
						this.grafo.getEdgeTarget(e), 
						(int) this.grafo.getEdgeWeight(e)));
		}
		return archi;
		
		
		
		

	}
	
	

}