import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));

        int numberOfPoints = Integer.parseInt(instream.readLine());
        while (numberOfPoints != 0){
            Coordinate[] coordinateArray = new Coordinate[numberOfPoints];

            for (int i = 0; i < numberOfPoints; i++){

                String[] splitString = instream.readLine().split(" ");

                coordinateArray[i] = new Coordinate(Double.parseDouble(splitString[0]),Double.parseDouble(splitString[1]));
            }

            if (coordinateArray.length == 1){
                String printOut = "INFINITY";
                System.out.println(printOut);
            }
            else {
                Arrays.sort(coordinateArray, Comparator.comparingDouble(p -> p.x));
                psClosestPair(coordinateArray);
            }

            numberOfPoints = Integer.parseInt(instream.readLine());
        }

    }
    static void psClosestPair(Coordinate[] coordinatesArray) {
        TreeSet<Coordinate> T = new TreeSet<>(Comparator.comparingDouble(p -> p.y));
        Coordinate p = coordinatesArray[0];
        Coordinate q = coordinatesArray[1];
        int xPosition = 0;

        double dx = p.x - q.x;
        double dy = p.y - q.y;
        double distance = dx * dx + dy * dy;
        double d = Math.sqrt(distance);

        T.add(p);
        T.add(q);

        for (int i = 2; i < coordinatesArray.length; i++){
            Coordinate s = coordinatesArray[i];
            Coordinate u = coordinatesArray[xPosition];
            while (u != null && u.x <= s.x-d && xPosition < i){
                T.remove(u);
                xPosition++;
                u = coordinatesArray[xPosition];
            }

            Coordinate startRange = new Coordinate(0, s.y-d);
            Coordinate endRange = new Coordinate(0, s.y+d);

            SortedSet<Coordinate> coordsInRange = T.subSet(startRange, endRange);

            for (Coordinate t : coordsInRange) {
                double value1 = Math.pow(s.x-t.x,2);
                double value2 = Math.pow(s.y-t.y,2);

                d = Math.min(d, Math.sqrt(value1+value2));
            }
            T.add(s);
        }

        String printOut = "INFINITY";
        if (d < 10000){
            System.out.printf("%.4f%n", d);
        } else {
            System.out.println(printOut);
        }
    }

    public static class Coordinate{
        private double x;
        private double y;
        public Coordinate(double x, double y){
            this.x = x;
            this.y = y;
        }
    }
}