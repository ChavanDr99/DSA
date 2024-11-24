import java.util.*;

class AdjListNode {
    int dest;
    int weight;

    AdjListNode(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

class DijkstraOptimized {
    public int[] dijkstra(List<List<AdjListNode>> adjList, int source) {
        int n = adjList.size(); // Number of vertices
        int[] distance = new int[n]; // Stores shortest distances
        Arrays.fill(distance, Integer.MAX_VALUE); // Initialize all distances to infinity
        distance[source] = 0; // Distance to the source is 0

        // PriorityQueue to process the nodes with the shortest distance first
        PriorityQueue<AdjListNode> pq = new PriorityQueue<>((node1, node2) -> Integer.compare(node1.weight, node2.weight));
        pq.add(new AdjListNode(source, 0));

        while (!pq.isEmpty()) {
            AdjListNode currentNode = pq.poll();
            int currentVertex = currentNode.dest;
            int currentDistance = currentNode.weight;

            // If the current distance is greater than the recorded distance, skip processing
            if (currentDistance > distance[currentVertex]) continue;

            // Process all neighbors
            for (AdjListNode neighbor : adjList.get(currentVertex)) {
                int newDist = distance[currentVertex] + neighbor.weight;

                // If a shorter path is found
                if (newDist < distance[neighbor.dest]) {
                    distance[neighbor.dest] = newDist;
                    pq.add(new AdjListNode(neighbor.dest, newDist)); // Push the neighbor with the updated distance
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        List<List<AdjListNode>> adjList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the number of nodes N. Vertices will be [0,N-1]");
        int n = sc.nextInt(); // Number of vertices
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        System.out.println("Please enter the number of edges");
        int edges = sc.nextInt();
        System.out.println("Please enter each edge in the sequence <start> <end> <weight>");
        for (int i = 0; i < edges; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            adjList.get(start).add(new AdjListNode(end, weight));
        }

        System.out.println("Please enter source vertex");
        int source = sc.nextInt();

        DijkstraOptimized dijkstra = new DijkstraOptimized();
        int[] distances = dijkstra.dijkstra(adjList, source);

        System.out.println("Distances from source " + source + ":");
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Node " + i + " ---> Infinity");
            } else {
                System.out.println("Node " + i + " ---> " + distances[i]);
            }
        }
    }
}
