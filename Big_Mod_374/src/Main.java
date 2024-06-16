import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));

        String input = instream.readLine();
        while (input != null){
            long B = Integer.parseInt(input);
            long P = Integer.parseInt(instream.readLine());
            long M = Integer.parseInt(instream.readLine());

            long out = calculateExponent(B, P, M);

            System.out.println(out);

            instream.readLine();
            input = instream.readLine();
        }
    }
    public static long calculateExponent(long B, long P, long M) {
        if (P == 0){
            return 1;
        }

        long z = calculateExponent(B,P/2, M);
        if (P % 2 == 0){
            return (z * z) % M;
        }
        else{
            return (B * z * z) % M;
        }
    }
}