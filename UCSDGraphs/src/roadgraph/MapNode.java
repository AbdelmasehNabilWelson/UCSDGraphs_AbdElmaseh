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

public class MapNode {
	private GeographicPoint vertexLocation; // locations of the node in the map
	private List<GeographicPoint> connectedNodes; // the edges of the current node
	private List<MapEdges> edges;
	
	
	/**
	 * Initializes the node with the given location
	 * @param location location of the node in the map
	 */
	public MapNode(GeographicPoint location) {
		vertexLocation = location;
		connectedNodes = new ArrayList<>();
		edges = new ArrayList<>();
	}
	
	/**
	 * allows to add the edges starting from current node
	 * @param edge the edge you need to add
	 */
	public void addNeighbourEdge(MapEdges edge) {
		this.connectedNodes.add(edge.getEnd());
		this.edges.add(edge);
	}
	
	/**
	 * returns the neighbors of current node represented as edges
	 * @return list of edges that are connected to our node
	 */
	public List<GeographicPoint> getNeibourNodes() {
		return connectedNodes;
	}
	
	/**
	 * returns the location of a vertex in a map
	 * @return the GeographicPoint of current node location in a map
	 */
	public GeographicPoint getVertexLocation() {
		return vertexLocation;
	}
	
	/**
	 * sets the location of current node
	 * @param vertexLocation location of the node
	 */
	public void setVertexLocation(GeographicPoint vertexLocation) {
		this.vertexLocation = vertexLocation;
	}
	
	/**
	 * returns the connected edges to current node
	 * @return the connected edges of our current node
	 */
	public List<MapEdges> getEdges() {
		return edges;
	}
}
