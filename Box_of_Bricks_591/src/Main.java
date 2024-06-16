// Problem 591

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));
        int numberOfStacks = Integer.parseInt(instream.readLine());
        int set = 1;
        do {
            int totalBricks = 0;
            int[] bricks = new int[numberOfStacks];
            String[] pair = instream.readLine().split(" ");
            for (int i = 0; i < numberOfStacks; i++){
                totalBricks += Integer.parseInt(pair[i]);
                bricks[i] = Integer.parseInt(pair[i]);
            }
            int height = totalBricks/numberOfStacks;
            int totalMoves = 0;
            for (int i = 0; i < bricks.length; i++){
                if (bricks[i] > height){
                    totalMoves = totalMoves + (bricks[i]-height);
                }
            }
            System.out.println("Set #" + set);
            System.out.println("The minimum number of moves is "+totalMoves+".");
            System.out.println();
            numberOfStacks = Integer.parseInt(instream.readLine());
            set++;
        }while (numberOfStacks != 0);
    }
}