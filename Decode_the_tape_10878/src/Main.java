// Problem 10878

import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringToPrint = new StringBuilder();

        instream.readLine();
        String rows;

        while (!(rows=instream.readLine()).equals("___________")){
            StringBuilder binaryString = new StringBuilder();
            for (int i = 1; i < rows.length()-1; i++) {
                if (String.valueOf(rows.charAt(i)).equals("o")) {
                    binaryString.append("1");
                } else if (String.valueOf(rows.charAt(i)).equals(" ")) {
                    binaryString.append("0");
                }
            }
            int binaryInt = Integer.parseInt(binaryString.toString(),2);
            String str = String.valueOf((char)binaryInt);
            stringToPrint.append(str);
        }
        System.out.print(stringToPrint);
    }
}