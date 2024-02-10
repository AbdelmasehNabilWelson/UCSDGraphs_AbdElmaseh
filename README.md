# UCSDGraphs_AbdElmaseh
Explore UCSDGraphs_AbdElmaseh, a project for graph algorithms. Uncover the power of BFS, Dijkstra's, and A* in modeling real-world data. User-friendly interface for seamless experimentation and understanding.

## This Project is part of [Advanced Data Structures in Java Course introduced by UCSanDiego](https://www.coursera.org/learn/advanced-data-structures)

## Prject Demo

## What I have Implemented in this project

### Class name: [Graph.java](/UCSDGraphs/src/basicgraph/Graph.java)
- I implemented the degreeSequence method
- I Implement the getDistance2 method in both [GraphAdjList](/UCSDGraphs/src/basicgraph/GraphAdjList.java) and [GraphAdjMatrix](/UCSDGraphs/src/basicgraph/GraphAdjMatrix.java) implementations

#### Class name: [MapGraph](/UCSDGraphs/src/roadgraph/MapGraph.java)
* Purpose and description of class:
A class which reprsents a graph of geographic locations Nodes in the graph are intersections between 

* Modifications made to MapGraph (what and why): 
    1. Added a new Fields
        * vertices: maps the GeographicPoint to its MapNode representation
        * numOfVertices: stores the number of the nodes in our map
        * numOfEdges: stores the number of edges in our map
    2. Added a new method
        * graphPath
    3. I have implemented the
        * the MapGraph constructor
        * bfs() method
        * getNumVertices()
        * getNumEdges()
        * addVertex(GeographicPoint location)
        * addEdge method

### Class name: [MapEdges](/UCSDGraphs/src/roadgraph/MapEdges.java)
Purpose and description of class: A class which the nodes connected to a specific node as an edge between every two connected nodes. 

* see the source code of the graph and you will fidnd the comments of each function implemnetaion

### Class name: [MapNode](/UCSDGraphs/src/roadgraph/MapNode.java)
Purpose and description of class: A class which reprsents a node of a graph 

## Note
I have only implmented the code I talked about it above, see this [file](/UCSDGraphs/README) to understand me 