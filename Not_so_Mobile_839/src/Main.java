// Problem 839

import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));
        int value = Integer.parseInt(instream.readLine());

        for (int i = 0; i < value; i++){
            instream.readLine();

            int result = subMobile(instream);
            String stringResult;

            if (result == -1){
                stringResult = "NO";
            } else{
                stringResult = "YES";
            }
            System.out.println(stringResult);

            if (i < value - 1) {
                System.out.println();
            }
        }
    }
    public static boolean checkIfEquilibrium(int[] intArray){
        return (intArray[0] * intArray[1]) == (intArray[2] * intArray[3]);
    }

    public static int subMobile(BufferedReader instream) throws IOException {
        String line = instream.readLine();

        if (line == null || line.isEmpty()) {
            return 0;
        }

        String [] stringArray = line.split(" ");
        int [] intArray = Arrays.stream(stringArray)
                .mapToInt(Integer::parseInt)
                .toArray();

        if (intArray[0] == 0){
            intArray[0] = subMobile(instream);
        }

        if (intArray[2] == 0){
            intArray[2] = subMobile(instream);
        }

        if (intArray[0] != 0 && intArray[2] != 0){
            if (checkIfEquilibrium(intArray)){
                return (intArray[0] + intArray[2]);
            } else {
                return -1;
            }
        }
        return 0;
    }
}