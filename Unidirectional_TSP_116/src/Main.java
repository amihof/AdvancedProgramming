import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));

        String input = instream.readLine();

        while (input != null){
            StringTokenizer token  = new StringTokenizer(input);
            int r = Integer.parseInt(token.nextToken());
            int c = Integer.parseInt(token.nextToken());

            int[][] intArray = new int[r][c];

            for (int i = 0; i < r; i++) {
                if (!token.hasMoreTokens()){
                    input = instream.readLine();
                    token = new StringTokenizer(input);
                }
                for (int j = 0; j < c; j++) {
                    if (token.hasMoreTokens()) {
                        intArray[i][j] = Integer.parseInt(token.nextToken());
                    }
                    else if(!token.hasMoreTokens()){
                        input = instream.readLine();
                        token = new StringTokenizer(input);
                        intArray[i][j] = Integer.parseInt(token.nextToken());
                    }
                }
            }

            int[][] directions = {
                    {-1, -1},
                    { 0, -1},
                    { 1, -1}
            };

            int[][] distanceArray = new int[r][c];

            for (int i = 0; i < distanceArray.length; i++) {
                Arrays.fill(distanceArray[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < intArray.length; i++) {
                distanceArray[i][c-1] = intArray[i][c-1];
            }

            //create the distanceArray
            for (int j = distanceArray[0].length-1; j >= 1; j--){
                for (int i = 0; i < distanceArray.length; i++){
                    for (int[] direction : directions){

                        int newX = (i + direction[0] + r) % r;
                        int newY = (j + direction[1] + c) % c;

                        if (newX >= 0 && newX < r && newY >= 0 && newY < c){
                            if (distanceArray[i][j] + intArray[newX][newY] < distanceArray[newX][newY]){
                                distanceArray[newX][newY] = distanceArray[i][j] + intArray[newX][newY];
                            }
                        }
                    }
                }
            }

            //find the shortest node in the first column
            Node shortestNode = new Node(Integer.MAX_VALUE, new Point(-100,-100));
            for (int i = 0; i < r; i++) {
                if (distanceArray[(i + r) % r][0] < shortestNode.value){
                    shortestNode = new Node(distanceArray[(i + r) % r][0], new Point((i + r) % r,0));

                }else if(distanceArray[(i + r) % r][0] == shortestNode.value){
                    if ((i+r) % r < shortestNode.point.x){
                        shortestNode = new Node(distanceArray[(i + r) % r][0], new Point((i + r) % r,0));
                    }
                }
            }

            int[] testPathInt = new int[c];
            testPathInt[0] = shortestNode.point.x;

            int[] path = test(shortestNode, distanceArray, testPathInt);

            StringBuilder sb = new StringBuilder();

            int totalValue = 0;
            for (int i = 0; i < c; i++){
                if (i == c-1){
                    sb.append(path[i]+1);
                }
                else {
                    sb.append(path[i] + 1).append(" ");
                }
                totalValue += intArray[path[i]][i];
            }
            System.out.println(sb);
            System.out.println(totalValue);

            input = instream.readLine();
        }
    }


    private static int[] test(Node node, int[][] distanceArray, int[] path){
        int r = distanceArray.length;
        node.point.y++;

        //Gå igenom listan från vänster till höger
        // kolla vilken som är minst av de tre till höger. Nodens x värde -1, +1 och 0.
        //Om det finns flera, välj den med minst x värde

        for (int j = node.point.y; j < distanceArray[0].length; j++) {
            Node shortestNode = new Node(Integer.MAX_VALUE, new Point(-100,-100));

            for (int i = node.point.x-1; i <= node.point.x+1 ; i++) {
                if (distanceArray[(i + r) % r][j] < shortestNode.value){
                    shortestNode = new Node(distanceArray[(i + r) % r][j], new Point((i + r) % r,j));
                }else if(distanceArray[(i + r) % r][j] == shortestNode.value){
                    if ((i+r) % r < shortestNode.point.x){
                        shortestNode = new Node (distanceArray[(i + r) % r][j], new Point((i + r) % r,j));
                    }
                }

                if (j == distanceArray.length-1){
                    path[j] = shortestNode.point.x; // hämta första i listan för den har minst x coordinate
                }
                if (j > 0){
                    path[j] = shortestNode.point.x;

                }

            }
            node = shortestNode;
        }

        return path;
    }

    static class Node{
        private int value;
        private Point point;

        public Node(int value, Point point){
            this.value = value;
            this.point = point;
        }
    }

}
