import java.util.*;

/**
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections
 */
public class MapGraph {

    private int numVertices, numEdges;
    private Map<GeographicPoint, MapNode> vertexMap;
    private Map<MapNode, MapNode> parentMap;

    /**
     * Create a new empty MapGraph
     */
    public MapGraph() {
        parentMap = new HashMap<>();
        vertexMap = new HashMap<>();
        numEdges = 0;
        numVertices = 0;
    }

    public static void main(String[] args) {
        System.out.print("Making a new map...");
        MapGraph theMap = new MapGraph();
        System.out.print("DONE. \nLoading the map...");
        GraphLoader.loadRoadMap("simpletest.map", theMap);
        System.out.println("DONE.");
        System.out.println("Num nodes: " + theMap.getNumVertices());
        System.out.println("Num edges: " + theMap.getNumEdges());
        System.out.println(theMap.bfs(new GeographicPoint(1.0, 1.0), new GeographicPoint(8, -1)));

        // uncomment for part 2
        System.out.println(theMap.dijkstra(new GeographicPoint(1.0, 1.0), new GeographicPoint(8, -1)));
        System.out.println(theMap.aStarSearch(new GeographicPoint(1.0, 1.0), new GeographicPoint(8, -1)));

    }

    /**
     * Get the number of vertices (road intersections) in the graph
     *
     * @return The number of vertices in the graph.
     */
    public int getNumVertices() {
        return numVertices;
    }

    /**
     * Return the intersections, which are the vertices in this graph.
     *
     * @return The vertices in this graph as GeographicPoints
     */
    public Set<GeographicPoint> getVertices() {
        return vertexMap.keySet();
    }

    /**
     * Get the number of road segments in the graph
     *
     * @return The number of edges in the graph.
     */
    public int getNumEdges() {
        return numEdges;
    }

    /**
     * Add a node corresponding to an intersection at a Geographic Point
     * If the location is already in the graph or null, this method does
     * not change the graph.
     *
     * @param location The location of the intersection
     * @return true if a node was added, false if it was not (the node
     * was already in the graph, or the parameter is null).
     */
    public boolean addVertex(GeographicPoint location) {
        if (location == null || parentMap.containsKey(location)) {
            return false;
        }

        MapNode node = new MapNode(location);
        vertexMap.put(location, node);
        numVertices++;
        return true;
    }

    /**
     * Adds a directed edge to the graph from pt1 to pt2.
     * Precondition: Both GeographicPoints have already been added to the graph
     *
     * @param from     The starting point of the edge
     * @param to       The ending point of the edge
     * @param roadName The name of the road
     * @param roadType The type of the road
     * @param length   The length of the road, in km
     * @throws IllegalArgumentException If the points have not already been
     *                                  added as nodes to the graph, if any of the arguments is null,
     *                                  or if the length is less than 0.
     */
    public void addEdge(GeographicPoint from, GeographicPoint to, String roadName, String roadType, double length) throws IllegalArgumentException {
        MapNode n1 = vertexMap.get(from);
        MapNode n2 = vertexMap.get(to);
        if (n1 == null)
            throw new NullPointerException("addEdge: from:" + from + "is not in graph");
        if (n2 == null)
            throw new NullPointerException("addEdge: to:" + to + "is not in graph");
        addEdge(n1, n2, roadName, roadType, length);
    }

    private void addEdge(MapNode n1, MapNode n2, String roadName, String roadType, double length) {
        MapEdge edge = new MapEdge(n1.getGP(), n2.getGP(), roadName, roadType, length);
        n1.addEdge(edge);
        numEdges++;
    }

    public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {

        Set<MapNode> visited = new HashSet<>();
        Queue<MapNode> queue = new LinkedList<>();
        Map<MapNode, MapNode> parent = new HashMap<>();


        boolean pathFound = false;

        MapNode startNode = vertexMap.get(start);
        MapNode goalNode = vertexMap.get(goal);

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            MapNode currentNode = queue.poll();


            if (currentNode.toString().equals(goalNode.toString())) {
                pathFound = true;
                break;
            }

            for (MapNode neighbor : getNeighborNodes(currentNode)) {

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, currentNode);
                    queue.add(neighbor);
                }
            }

        }

        return getPath(startNode, goalNode, parent, pathFound);

    }

    private List<MapNode> getNeighborNodes(MapNode mn) {
        List<MapNode> listNodes = new ArrayList<>();
        for (GeographicPoint gp : mn.getNeighbors()) {
            listNodes.add(vertexMap.get(gp));
        }
        return listNodes;
    }

    private List<GeographicPoint> getPath(MapNode start, MapNode goal,
                                          Map<MapNode, MapNode> parent, boolean pathFound) {

        if (!pathFound) {
            return null;
        }

        List<GeographicPoint> path = new LinkedList<GeographicPoint>();
        MapNode currNode = goal;

        while (!currNode.toString().equals(start.toString())) {
            MapNode prevNode = parent.get(currNode);
            path.add(currNode.getGP());
            currNode = prevNode;
        }

        path.add(start.getGP());
        Collections.reverse(path);
        return path;
    }

    public List<GeographicPoint> dijkstra(GeographicPoint start,
                                          GeographicPoint goal) {

        Set<MapNode> visited = new HashSet<MapNode>();
        PriorityQueue<MapNode> priorityQueue = new PriorityQueue<>();
        parentMap = new HashMap<MapNode, MapNode>();
        boolean pathFound = false;
        MapNode startNode = vertexMap.get(start);
        MapNode goalNode = vertexMap.get(goal);

        for (GeographicPoint gp : vertexMap.keySet()) {
            vertexMap.get(gp).setDistance(Double.POSITIVE_INFINITY);
        }

        vertexMap.get(start).setDistance(0.0);
        priorityQueue.add(startNode);

        int count = 0;
        while (!priorityQueue.isEmpty()) {
            MapNode currNode = priorityQueue.poll();
            count++;
            if (currNode.toString().equals(goalNode.toString())) {
                pathFound = true;
                break;
            }

            for (MapNode neighbor : getNeighborNodes(currNode)) {
                if (!visited.contains(neighbor)) {
                    double distance = currNode.getDistance() + currNode.getDistance();
                    if (distance < neighbor.getDistance()) {
                        neighbor.setDistance(distance);
                        parentMap.put(neighbor, currNode);
                        priorityQueue.add(neighbor);
                    }
                }
            }
            visited.add(currNode);
        }
        System.out.println("Dijkstra count: " + count);
        List<GeographicPoint> path = getPath(startNode, goalNode, parentMap, pathFound);
        return path;
    }

    private void printPath(List<GeographicPoint> path) {
        String str = "";
        for (GeographicPoint gp : path) {
            str += " " + gp.x + ":" + gp.getY();
        }
        System.out.println("Path: " + str);
    }

    private void printDistMap(HashMap<MapNode, Double> distanceMap) {
        for (MapNode node : distanceMap.keySet()) {
            System.out.println("Node: " + node.getGP() + " cost: " + distanceMap.get(node));
        }
        System.out.println("-------");
        for (MapNode node : parentMap.keySet()) {
            System.out.println("Node: " + node + " parent: " + parentMap.get(node));
        }
    }

    public List<GeographicPoint> aStarSearch(GeographicPoint start,
                                             GeographicPoint goal) {
        HashSet<MapNode> visited = new HashSet<MapNode>();
        PriorityQueue<MapNode> priorityQueue = new PriorityQueue<>();
        parentMap = new HashMap<MapNode, MapNode>();

        boolean pathFound = false;
        MapNode startNode = vertexMap.get(start);
        MapNode goalNode = vertexMap.get(goal);

        for (GeographicPoint location : vertexMap.keySet()) {

            vertexMap.get(location).setDistance(Double.POSITIVE_INFINITY);

            vertexMap.get(location).setHeuristicDist(Double.POSITIVE_INFINITY);
        }
        startNode.setDistance(0.0);
        startNode.setHeuristicDist(0.0);

        priorityQueue.add(startNode);

        int cnt = 0;

        while (!priorityQueue.isEmpty()) {
            MapNode currentNode = priorityQueue.poll();
            cnt++;

            if (currentNode.toString().equals(goalNode.toString())) {
                pathFound = true;
                break;
            }

            for (MapNode neighbor : getNeighborNodes(currentNode)) {
                if (!visited.contains(neighbor)) {
                    double distance = currentNode.getDistance() + currentNode.getDistance();
                    if (distance < neighbor.getDistance()) {
                        neighbor.setDistance(distance);
                        neighbor.setHeuristicDist(distance + neighbor.getGP().distance(goal));
                        parentMap.put(neighbor, currentNode);
                        priorityQueue.add(neighbor);
                    }
                }
            }
            visited.add(currentNode);
        }
        System.out.println("Astar count: " + cnt);
        return getPath(startNode, goalNode, parentMap, pathFound);
    }
}

class MapNode implements Comparable<MapNode> {

    GeographicPoint gp;
    List<MapEdge> edges;
    Double distance;
    Double heuristicDist;

    public MapNode(GeographicPoint loc) {
        gp = loc;
        edges = new ArrayList<>();
        distance = null;
        heuristicDist = null;
    }

    public GeographicPoint getGP() {
        return gp;
    }

    public List<MapEdge> getEdges() {
        return edges;
    }

    public void addEdge(MapEdge edge) {
        edges.add(edge);
    }

    public List<GeographicPoint> getNeighbors() {
        List<GeographicPoint> neighborNodes = new ArrayList<>();
        for (MapEdge edge : edges) {
            neighborNodes.add(edge.end);
        }
        return neighborNodes;
    }

    @Override
    public boolean equals(Object obj) {
        MapNode mNode = (MapNode) obj;
        return gp.equals(mNode.getGP());
    }

    public Double getDistance() {
        return this.distance;
    }

    public void setDistance(Double DistVal) {
        this.distance = DistVal;
    }

    public Double getHeuristicDist() {
        return this.heuristicDist;
    }

    public void setHeuristicDist(Double cost) {
        heuristicDist = cost;
    }

    @Override
    public String toString() {
        return gp.toString();
    }

    @Override
    public int compareTo(MapNode other) {
        return getDistance().compareTo(other.getDistance());
    }

}

class MapEdge {
    GeographicPoint start;
    GeographicPoint end;
    String streetName, roadType;
    double distance;

    public MapEdge(GeographicPoint start, GeographicPoint end, String streetName, String roadType, double distance) {
        this.start = start;
        this.end = end;
        this.streetName = streetName;
        this.roadType = roadType;
        this.distance = distance;
    }

    public String toString() {
        return "" + start + " " + end;
    }
}