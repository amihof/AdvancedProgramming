import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while (!(input = instream.readLine()).equals("0 0")){

            String[] inputArray = input.split(" ");

            int N = Integer.parseInt(inputArray[0]);
            int K = Integer.parseInt(inputArray[1]);

            int[][] dpArray = new int[N+1][K+1];

            for (int i = 0; i <= N; i++) {
                dpArray[i][1] = 1;
            }
            for (int i = 0; i <= K; i++){
                dpArray[1][i] = i;
            }

            for (int i = 2; i <= N; i++) {
                for (int j = 2; j <= K; j++) {
                    dpArray[i][j] = (dpArray[i-1][j] + dpArray[i][j-1]) % 1000000;
                }
            }

            System.out.println(dpArray[N][K]);
        }
    }
}