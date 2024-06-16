import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));

        int cornerPoints = Integer.parseInt(instream.readLine());

        while (cornerPoints != 0){
            Coordinates[] coordinateArray = new Coordinates[cornerPoints];
            boolean hasCriticalPoint = false;
            int firstTurn = 0;
            for (int i = 0; i < cornerPoints; i++){

                String[] splitString = instream.readLine().split(" ");

                coordinateArray[i] = new Coordinates(Integer.parseInt(splitString[0]),Integer.parseInt(splitString[1]));
            }

            for (int i = 0; i < coordinateArray.length; i++){
                Coordinates p1 = coordinateArray[i % coordinateArray.length];
                Coordinates p2 = coordinateArray[(i+1) % coordinateArray.length];
                Coordinates p3 = coordinateArray[(i+2) % coordinateArray.length];

                Coordinates u = new Coordinates(p2.x-p1.x, p2.y-p1.y);
                Coordinates v = new Coordinates(p3.x - p1.x, p3.y - p1.y);

                if (i == 0){
                    firstTurn = (u.x * v.y) - (u.y * v.x);
                }

                if (firstTurn > 0){ //first is a left turn
                    if ((u.x * v.y) - (u.y * v.x) < 0){
                        hasCriticalPoint = true;
                    }
                } else { //first is a right turn
                    if ((u.x * v.y) - (u.y * v.x) > 0){
                        hasCriticalPoint = true;
                    }
                }
            }

            String output;

            if (hasCriticalPoint){
                output = "Yes";
            }else {
                output = "No";
            }

            System.out.println(output);

            cornerPoints = Integer.parseInt(instream.readLine());
        }
    }

    static class Coordinates{
        int x;
        int y;
        public Coordinates(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}