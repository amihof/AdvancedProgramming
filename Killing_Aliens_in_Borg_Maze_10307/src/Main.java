import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));

        int testCases = Integer.parseInt(instream.readLine());

        for (int i = 0; i < testCases; i++) {
            ArrayList<Point> alienList = new ArrayList<>();

            ArrayList<Point> allNodesList = new ArrayList<>();

            Node startNode = null;
            String[] inputString = instream.readLine().split(" ");
            int y  = Integer.parseInt(inputString[0]);
            int x = Integer.parseInt(inputString[1]);

            Node[][] nodeArray = new Node[x][y];

            for (int r = 0; r < x; r++) {
                String newRow = instream.readLine();
                for (int c = 0; c < newRow.length(); c++) {
                    char type = newRow.charAt(c);
                    if (type == 'A'){
                        alienList.add(new Point(r,c));
                    }
                    else if (type == 'S'){
                        startNode = new Node(new Point(r,c), type);
                    }
                    nodeArray[r][c] = new Node(new Point(r,c), type);
                }
            }

            allNodesList.add(startNode.point); //för att startnoden alltid ska vara 0, så vi börjar från den i prims algoritm
            allNodesList.addAll(alienList);


            int[][] adjacencyMatrix = new int[allNodesList.size()][allNodesList.size()];

            for (int j = 0; j < allNodesList.size(); j++) {
                for (int k = 0; k < allNodesList.size(); k++) {
                    if (allNodesList.get(j) != allNodesList.get(k)){
                        adjacencyMatrix[j][k] = BFSToFindShortestPath(allNodesList.get(j), nodeArray, allNodesList.get(k));
                    }
                }
            }

            primMST(adjacencyMatrix);
        }
    }

    public static int BFSToFindShortestPath(Point startNode, Node[][] nodeArray, Point endPoint) {
        int[][] distanceArray = new int[nodeArray.length][nodeArray[0].length];

        int[][] directions = {
                {-1,0},
                {1, 0},
                {0,-1},
                {0, 1}
        };

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[nodeArray.length][nodeArray[0].length];

        queue.offer(startNode);
        visited[startNode.x][startNode.y] = true;
        distanceArray[startNode.x][startNode.y] = 0;

        while (!queue.isEmpty()){
            Point currentNode = queue.poll();
            for (int[] direction : directions) {
                int newX = currentNode.x + direction[0];
                int newY = currentNode.y + direction[1];

                if (nodeArray[newX][newY].type != '#' && !visited[newX][newY]) {
                    queue.offer(nodeArray[newX][newY].point);

                    distanceArray[newX][newY] = distanceArray[currentNode.x][currentNode.y] + 1;

                    visited[newX][newY] = true;

                    if (nodeArray[newX][newY].point.x == endPoint.x && nodeArray[newX][newY].point.y == endPoint.y){
                        return distanceArray[newX][newY];
                    }
                }
            }
        }

        return 0;
    }

    public static void primMST(int[][] adjacencyMatrix){
        int V = adjacencyMatrix.length;
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;

        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet, V);
            mstSet[u] = true;

            for (int v = 0; v < V; v++){
                if (adjacencyMatrix[u][v] != 0 && !mstSet[v] && adjacencyMatrix[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = adjacencyMatrix[u][v];
                }
            }
        }

        int shortestPath = 0;

        for (int i = 1; i < V; i++){
            shortestPath += adjacencyMatrix[i][parent[i]];
        }
        System.out.println(shortestPath);

    }

    public static int minKey(int[] key, boolean[] mstSet, int V)
    {
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < V; v++){
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        return min_index;
    }

    public static class Node{
        Point point;
        char type;

        public Node(Point point, char type){
            this.point = point;
            this.type = type;

        }

    }


}