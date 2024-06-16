import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(instream.readLine());

        for (int i = 0; i < testCases; i++){
            PriorityQueue<Coordinates> priorityQueue = new PriorityQueue<>(new Comparator<Coordinates>() {
                @Override
                public int compare(Coordinates coordinate1, Coordinates coordinate2) {
                    return Integer.compare(coordinate2.x, coordinate1.x);
                }
            });
            int coordinatePairs = Integer.parseInt(instream.readLine());

            for (int j = 0; j < coordinatePairs; j++) {
                String[] splitString = instream.readLine().split(" ");
                priorityQueue.add(new Coordinates(Integer.parseInt(splitString[0]),Integer.parseInt(splitString[1])));
            }

            double highestPoint = 0;

            double previousX = 0;
            double previousY = 0;

            double totalDistance = 0;

            if (!priorityQueue.isEmpty()){
                Coordinates previousPoint = priorityQueue.poll();
                previousX = previousPoint.x;
                previousY = previousPoint.y;

            }
            int counter = 0;

            while (!priorityQueue.isEmpty()){
                Coordinates point = priorityQueue.poll();
                double x = point.x;
                double y = point.y;

                if (counter % 2 == 0){
                    double yIntersection = highestPoint;

                    if (yIntersection < y){
                        System.out.println(x + " x värde");
                        System.out.println(previousX + " previousX värde");
                        System.out.println(y + " y värde");
                        System.out.println(previousY + "previousY värde");
                        System.out.println(yIntersection + " y intersection");
                        double xIntersection = x + (previousX - x) * (yIntersection - y) / (previousY - y);

                        System.out.println(xIntersection + "xIntersection");
                        double distance = Math.sqrt(Math.pow(xIntersection - x, 2) + Math.pow(yIntersection - y, 2));

                        totalDistance += distance;
                    }

                    if (y > highestPoint){
                        highestPoint = y;
                    }
                }

                previousX = x;
                previousY = y;

                counter++;
            }

            System.out.printf("%.2f%n", totalDistance);
        }
    }

    static class Coordinates {
        int x;
        int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}