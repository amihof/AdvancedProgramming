import java.io.*;
import java.util.*;

class Main {
    public static ArrayList<ArrayList<Integer>> adjListArray;
    public static int N;
    public static int tempFriendGroup;
    public static int biggestFriendGroup;


    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));

        int testCases = Integer.parseInt(instream.readLine());

        for (int t = 0; t < testCases; t++){
            String[] splitString = instream.readLine().split(" ");

            N = Integer.parseInt(splitString[0]);
            int M = Integer.parseInt(splitString[1]);

            adjListArray = new ArrayList<>();

            for (int i = 0; i < N; i++){
                adjListArray.add(i, new ArrayList<>());
            }

            for (int i = 0; i < M; i++){
                String[] splitValues = instream.readLine().split(" ");
                addEdge(Integer.parseInt(splitValues[0])-1,Integer.parseInt(splitValues[1])-1);
            }

            connectedComponents();
        }
    }

    static void addEdge(int src, int dest)
    {
        if (!adjListArray.get(src).contains(dest)){
            // Add an edge from src to dest.
            adjListArray.get(src).add(dest);

            // Since graph is undirected, add an edge from dest
            // to src also
            adjListArray.get(dest).add(src);
        }
    }

    static void connectedComponents()
    {
        tempFriendGroup = 0;
        biggestFriendGroup = 0;
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[N];
        for (int v = 0; v < N; ++v) {
            if (!visited[v]) {
                // print all reachable vertices
                // from v
                tempFriendGroup = 0;
                DFSUtil(v, visited);

                if (biggestFriendGroup == 0){
                    biggestFriendGroup = tempFriendGroup;
                }
                else if (biggestFriendGroup < tempFriendGroup){
                    biggestFriendGroup = tempFriendGroup;
                }
            }
        }
        System.out.println(biggestFriendGroup);
    }

    static void DFSUtil(int v, boolean[] visited)
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        tempFriendGroup++;
        // Recur for all the vertices
        // adjacent to this vertex
        for (int x : adjListArray.get(v)) {
            if (!visited[x]) {
                DFSUtil(x, visited);
            }
        }
    }
}