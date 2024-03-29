/**
 * @author UCSD MOOC development team and Abdelmaseh Nabil
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	/**
	 * maps the GeographicPoint to its MapNode representation
	 */
	private HashMap<GeographicPoint, MapNode> vertices;
	
	// stores the number of the nodes in our map
	private int numOfVertices;
	
	// stores the number of edges in our map
	private int numOfEdges;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		this.vertices = new HashMap<>();
		numOfEdges = 0;
		numOfVertices = 0;
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		return numOfVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		return vertices.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		return numOfEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		if (location != null && !vertices.containsKey(location)) { 
			MapNode vertex = new MapNode(location);
			vertices.put(location, vertex);
			
			numOfVertices++;
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		
		if (length < 0) {
			throw new IllegalArgumentException("Invalid Length number");
		}
		
		if (from == null || to == null || roadName == null || roadType == null) {
			throw new IllegalArgumentException("null argument is invalid");
		}
		
		if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
			throw new IllegalArgumentException("from or to nodes must be added to the graph");
		}
		
		
		MapEdges edge = new MapEdges(from, to, roadName, length);
		vertices.get(from).addNeighbourEdge(edge);
		numOfEdges++;
	}
	
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		Queue<MapNode> toCheck = new LinkedList<>();
		HashSet<GeographicPoint> visidted = new HashSet<>();
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
		
		
		MapNode startNode = vertices.get(start);
		toCheck.add(startNode);

		while (!toCheck.isEmpty()) {
			MapNode curr = toCheck.poll();
			
			nodeSearched.accept(curr.getLocation());
			if (curr.getLocation().equals(goal)) {
				return this.graphPath(goal, start, parentMap);
			}
			
			for(GeographicPoint next : curr.getNeighbors()) {
				if (!visidted.contains(next)) {
					toCheck.add(vertices.get(next));
					visidted.add(next);
					parentMap.put(next, curr.getLocation());
				}
			}
		}
		return null;
	}
	
	
	/**
	 * finds the path from the goal to the start and reverse it and returns the
	 * path found between the start node and goal node
	 * 
	 * @param goal the node you want to reach 
	 * @param start starting node of your path
	 * @param map stores the edges between the nodes in the path but it stores
	 * the end of the path and relate it with its starting
	 * @return the path from the starting node to the goal node
	 */
	public List<GeographicPoint> graphPath(GeographicPoint goal, GeographicPoint start, HashMap<GeographicPoint, GeographicPoint> map) {
		
		if (map == null) {
			throw new NullPointerException("map cannot be null");
		}
		
		if (goal == null || start == null) {
			throw new NullPointerException("goal or start cannot be null");
		}
		List<GeographicPoint> list = new ArrayList<GeographicPoint>();
		list.add(goal);
		
		GeographicPoint curr = map.get(goal);
		

		while (!curr.equals(start)) {
			list.add(curr);
			curr = map.get(curr);
			//System.out.println(curr.toString() + " ");
		}
		list.add(start);
		
		Collections.reverse(list);
		return list;
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		initDistances(start, goal, false);
		PriorityQueue<MapNode> pq = new PriorityQueue<>();
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		
		
		pq.add(vertices.get(start));
		
		int count = 0;
		Boolean pathFound = false;
		while (!pq.isEmpty()) {
			MapNode curr = pq.poll();
			count++;
			nodeSearched.accept(curr.getLocation());
			
			
			if (!visited.contains(curr.getLocation())) {
				visited.add(curr.getLocation());
				
				
				if (curr.getLocation().equals(goal)) {
					pathFound = true;
					break;
				}
				
				for(MapEdges edge : curr.getEdges()) {
					double disAndCost = curr.getDistance() + edge.getLength();
					if (disAndCost < vertices.get(edge.getEnd()).getDistance()) {
						vertices.get(edge.getEnd()).setDistance(disAndCost);
						
						pq.add(vertices.get(edge.getEnd()));
						parentMap.put(edge.getEnd(), curr.getLocation());
					}
				}
			}
		}
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		System.out.println("Dijstkare total number of visited nodes: " + count);
		
		if (pathFound) {
			return this.graphPath(goal, start, parentMap);
		}
		
		return null;
	}

	private void initDistances(GeographicPoint start, GeographicPoint goal, Boolean isAstarAlgo) {
		
		for(GeographicPoint curr : vertices.keySet()) {
			MapNode node = vertices.get(curr);
						
			if (isAstarAlgo) {
				node.setAStar(isAstarAlgo);
				node.setHeuristicCost(vertices.get(goal));
			} else {
				node.setAStar(false);
			}
			
			if (curr.equals(start)) {
				node.setDistance(0);
			} else {
				node.setDistance(Double.MAX_VALUE);
			}
		}
			
	}
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		initDistances(start, goal, true);
		// TODO: Implement this method in WEEK 4
		PriorityQueue<MapNode> pq = new PriorityQueue<>();
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		
		
		pq.add(vertices.get(start));
		
		int count = 0;
		Boolean pathFound = false;
		
		while (!pq.isEmpty()) {
			MapNode curr = pq.poll();
			count++;
			nodeSearched.accept(curr.getLocation());
			
			if (!visited.contains(curr.getLocation())) {
				visited.add(curr.getLocation());
				
				if (curr.getLocation().equals(goal)) {
					pathFound = true;
					break;
				}
				
				for(MapEdges edge : curr.getEdges()) {
					MapNode next = vertices.get(edge.getEnd());
					
					double disAndCost = curr.getDistance() + edge.getLength();
					
					if (disAndCost < next.getDistance()) {
						next.setDistance(disAndCost);
						
						
						pq.add(next);
						parentMap.put(edge.getEnd(), curr.getLocation());
					}						
				}
			}
		}		
		
		System.out.println("A start total number of visited nodes: " + count);
		
		if (pathFound) {
			return this.graphPath(goal, start, parentMap);
		}

		return null;
	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		/*
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		*/
		
		
		/* Use this code in Week 3 End of Week Quiz */
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		
		
	}
	
}
