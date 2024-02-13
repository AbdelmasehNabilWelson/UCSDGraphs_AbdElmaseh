/**
 * @author Abdelmaseh Nabil
 * 
 * A class which reprsents a node of a graph 
 *
 */
package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

public class MapNode implements Comparable<MapNode> {
	private GeographicPoint location; // locations of the node in the map
	private List<GeographicPoint> neighbors; // the edges of the current node
	private List<MapEdges> edges;
	private double shortestDistance; // shortest distance of source node until this node
	private Boolean aStartFlag;
	private double heuristicCost;
	
	/**
	 * Initializes the node with the given location
	 * @param location location of the node in the map
	 */
	public MapNode(GeographicPoint location) {
		this.location = location;
		this.neighbors = new ArrayList<>();
		this.edges = new ArrayList<>();
		this.shortestDistance = 0;
		this.heuristicCost = 0;
		this.aStartFlag = false;
	}
	
	@Override
	public int compareTo(MapNode other) {
		if(aStartFlag) {
			Double currTotDist = this.getDistance() + this.getHeuristicCost();
			Double nextTotDist = other.getDistance() + other.getHeuristicCost();
			
			//System.out.println("A*");
			return currTotDist.compareTo(nextTotDist);
		}
		
		//System.out.println("Dij");
		return this.getDistance().compareTo(other.getDistance());
	}
	
	/**
	 * allows to add the edges starting from current node
	 * @param edge the edge you need to add
	 */
	public void addNeighbourEdge(MapEdges edge) {
		this.neighbors.add(edge.getEnd());
		this.edges.add(edge);
	}
	
	/**
	 * returns the neighbors of current node represented as edges
	 * @return list of edges that are connected to our node
	 */
	public List<GeographicPoint> getNeighbors() {
		return neighbors;
	}
	
	/**
	 * returns the location of a vertex in a map
	 * @return the GeographicPoint of current node location in a map
	 */
	public GeographicPoint getLocation() {
		return location;
	}
	
	/**
	 * sets the location of current node
	 * @param vertexLocation location of the node
	 */
	public void setLocation(GeographicPoint vertexLocation) {
		this.location = vertexLocation;
	}
	
	/**
	 * returns the connected edges to current node
	 * @return the connected edges of our current node
	 */
	public List<MapEdges> getEdges() {
		return edges;
	}

	public Double getDistance() {
		return shortestDistance;
	}

	public void setDistance(double shortestDistance) {
		this.shortestDistance = shortestDistance;
	}
	
	public void setAStar(Boolean flag) {
		this.aStartFlag = flag;
	}
	
	public double getHeuristicCost() {
		return this.heuristicCost;
	}
	
	public void setHeuristicCost(MapNode goal) {
		this.heuristicCost = this.getLocation().distance(goal.getLocation());
	}
}
