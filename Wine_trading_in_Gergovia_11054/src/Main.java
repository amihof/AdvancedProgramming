import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));

        int line = Integer.parseInt(instream.readLine());

        while (line != 0) {
            String[] stringArray = instream.readLine().split(" ");

            long cost = 0;
            int carrying = 0;

            for (int i = 0; i < stringArray.length; i++) {
                int oldCarrying = carrying;
                carrying = oldCarrying - Integer.parseInt(stringArray[i]);

                cost += Math.abs(carrying);
            }

            System.out.println(cost);

            line = Integer.parseInt(instream.readLine());
        }

    }
}