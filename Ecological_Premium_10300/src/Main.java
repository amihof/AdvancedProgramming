// Problem 10300

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(instream.readLine());
        for (int i = 0; i < testCases; i++) {
            int farmers = Integer.parseInt(instream.readLine());
            int totalMoney = 0;
            for (int j = 0; j < farmers; j++) {
                String[] pair = instream.readLine().split(" ");
                int farmSize = Integer.parseInt(pair[0]);
                int degree = Integer.parseInt(pair[2]);
                totalMoney = totalMoney + (farmSize * degree);
            }
            System.out.println(totalMoney);
        }
    }
}