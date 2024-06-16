import java.awt.*;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));

        String[] input = instream.readLine().split(" ");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        int counter = 1;

        while (!input[0].equals("0") && !input[1].equals("0")){
            int[][] array = new int[x][y];
            ArrayList<Point> pointList = new ArrayList<>();

            for (int i = 0; i < array.length; i++){
                String[] row = instream.readLine().split("");

                for (int j = 0; j < array[0].length; j++){
                    if (row[j].equals("*")){
                        array[i][j] = 9;
                        pointList.add(new Point(i,j));
                    }
                    else if(row[j].equals(".")){
                        array[i][j] = 0;
                    }
                }
            }

            for (Point point : pointList) {
                if (point.x-1 >= 0){
                    if (point.y-1 >= 0){
                        array[point.x-1][point.y-1] += 1;  //uppe vänster
                    }
                    array[point.x-1][point.y] += 1; //till vänster

                    if (point.y+1 < array[0].length){
                        array[point.x-1][point.y+1] += 1; //nere vänster
                    }
                }
                if (point.x+1 < array.length){
                    if (point.y+1 < array[0].length){
                        array[point.x+1][point.y+1] += 1; // nere höger
                    }
                    array[point.x+1][point.y] +=1; //höger

                    if (point.y-1 >= 0){
                        array[point.x+1][point.y-1] += 1; //upp höger
                    }
                }
                if (point.y-1 >= 0){
                    array[point.x][point.y-1] += 1; // uppe
                }
                if (point.y+1 < array[0].length){
                    array[point.x][point.y+1] += 1; // nere
                }

            }
            if (counter != 1){
                System.out.println();
            }
            System.out.println("Field #" + counter + ":");
            for (int i = 0; i < array.length; i++){
                for (int j = 0; j < array[0].length; j++){
                    if (array[i][j] >= 9){
                        System.out.print("*");
                    }
                    else {
                        System.out.print(array[i][j]);
                    }
                }
                System.out.println();
            }

            input = instream.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            counter++;
        }

    }
}