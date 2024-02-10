/**
 * @author Abdelmaseh Nabil
 * 
 * A class which the nodes connected to a specific node as an edge 
 * between every two connected nodes. 
 */

package roadgraph;

import geography.GeographicPoint;

public class MapEdges {
	private GeographicPoint start; // start of the edge 
	private GeographicPoint end; // end of the edge
	
	private String streetName; // the name of the edge(street)
	private double length; // length of the road between the two nodes or the length of the edge
	
	/**
	 * initializes a mapEdge with the given parameters
	 * 
	 * note: we pass the GeoegraphicPoint of node to avoid passing 
	 * unnecessary fields of each node to make an edge
	 * 
	 * @param start node of our edge 
	 * @param end the end node in that edge
	 * @param streetName name of the street
	 * @param length of the edge
	 */
	public MapEdges(GeographicPoint start, GeographicPoint end, String streetName, double length) {
		this.setStart(start);
		this.setEnd(end);
		
		this.streetName = streetName;
		this.setLength(length);
	}
	
	/**
	 * returns the start node of an edge
	 * @return start of an edge
	 */
	public GeographicPoint getStart() {
		return start;
	}

	/**
	 * sets the start of the edge
	 * @param start starting node of the edge
	 */
	public void setStart(GeographicPoint start) {
		this.start = start;
	}

	/**
	 * returns the end of the edge
	 * @return edge's end
	 */
	public GeographicPoint getEnd() {
		return end;
	}

	/**
	 * sets the edge's end
	 * @param end of the edge
	 */
	public void setEnd(GeographicPoint end) {
		this.end = end;
	}
	
	/**
	 * gets the street name
	 * @return the street name
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * sets the street name
	 * @param streetName the name you want to change the street to
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * gets the length of the street 
	 * @return the street length
	 */
	
	public double getLength() {
		return length;
	}

	/**
	 * setes the 
	 * @param length
	 */
	public void setLength(double length) {
		this.length = length;
	}
}
