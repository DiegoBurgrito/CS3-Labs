import java.util.*;

/**
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections
 */
public class MapGraph {

    public int numVertices, numEdges;
    public Map<GeographicPoint, MapNode> vertexMap;
    public Map<MapNode, MapNode> parentMap;

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

        System.out.println(theMap.dijkstra(new GeographicPoint(1.0, 1.0), new GeographicPoint(8, -1)));
        System.out.println(theMap.aStarSearch(new GeographicPoint(1.0, 1.0), new GeographicPoint(8, -1)));

    }

    public int getNumVertices() {
        return numVertices;
    }

    public Set<GeographicPoint> getVertices() {
        return vertexMap.keySet();
    }

    public int getNumEdges() {

        return numEdges;
    }


    public boolean addVertex(GeographicPoint location) {
        if (location == null || parentMap.containsKey(location)) {
            return false;
        }

        MapNode node = new MapNode(location);
        vertexMap.put(location, node);
        numVertices++;
        return true;
    }

    public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
                        String roadType, double length) throws IllegalArgumentException {


        MapNode n1 = vertexMap.get(from);
        MapNode n2 = vertexMap.get(to);

        if (n1 == null)
            throw new NullPointerException("addEdge: from:" + from + "is not in graph");
        if (n2 == null)
            throw new NullPointerException("addEdge: to:" + to + "is not in graph");

        addEdge(n1, n2, roadName, roadType, length);
    }

    private void addEdge(MapNode n1, MapNode n2, String roadName,
                         String roadType, double length) {
        MapEdge edge = new MapEdge(n1.getGP(), n2.getGP(), roadName, roadType, length);
        n1.addEdge(edge);
        numEdges++;
    }

    public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
        Set<MapNode> visited = new HashSet<MapNode>();
        Queue<MapNode> queue = new LinkedList<MapNode>();
        Map<MapNode, MapNode> parent = new HashMap<MapNode, MapNode>();

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

        HashSet<MapNode> visited = new HashSet<MapNode>();
        PriorityQueue<MapNode> priorityQueue = new PriorityQueue<>();
        parentMap = null;
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

            MapNode currentNode = priorityQueue.poll();
            count++;

            if (currentNode.toString().equals(goalNode.toString())) {
                pathFound = true;
                break;
                //continue;
            }
            if (!visited.contains(currentNode)) {
                visited.add(currentNode);
                for (MapEdge edge : currentNode.getEdges()) {
                    MapNode neighbor = vertexMap.get(edge.end);
                    if (!visited.contains(neighbor)) {
                        Double minDist = currentNode.getDistance() + edge.distance;
                        if (minDist < neighbor.getDistance()) {
                            neighbor.setDistance(minDist);
                            parentMap.put(neighbor, currentNode);
                            priorityQueue.add(neighbor);
                        }

                    }
                }
            }

        }
        System.out.println("Dijkstra count: " + count);
        //printDistMap(distanceMap);
        List<GeographicPoint> path = getPath(startNode, goalNode, parentMap, pathFound);
        //printPath(path);
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

        int count = 0;

        while (!priorityQueue.isEmpty()) {
            MapNode currentNode = priorityQueue.poll();
            count++;

            if (!visited.contains(currentNode)) {
                visited.add(currentNode);

                if (currentNode.equals(goalNode)) {
                    pathFound = true;
                    break;
                }

                for (MapEdge edge : currentNode.getEdges()) {
                    MapNode neighbor = vertexMap.get(edge.end);
                    if (!visited.contains(neighbor)) {
                        double heurDist = currentNode.getHeuristicDist() + edge.distance;
                        double minDist = heurDist + (neighbor.getGP()).distance(goalNode.getGP());
                        if (minDist < neighbor.getDistance()) {
                            neighbor.setHeuristicDist(heurDist);
                            neighbor.setDistance(minDist);
                            parentMap.put(neighbor, currentNode);
                            priorityQueue.add(neighbor);
                        }
                    }
                }
            }

        }

        System.out.println("Astar count: " + count);

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