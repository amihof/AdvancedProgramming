import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = instream.readLine()) != null){
            String[] arrayInput = input.split(" ");
            String s = arrayInput[0];
            String t = arrayInput[1];
            String outPrint = "Yes";
            int lastCorrectLetter = 0;
            int nbrOfCorrectLetters = 0;
            boolean wordComplete = false;

            if (s.length() > t.length()){
                outPrint = "No";
            }
            else {
                for (int i = 0; i < s.length(); i++){
                    for (int j = lastCorrectLetter; j < t.length(); j++) {
                        if (s.charAt(i) == t.charAt(j)){
                            nbrOfCorrectLetters++;
                            if (nbrOfCorrectLetters == s.length()){
                                wordComplete = true;
                            }
                            lastCorrectLetter = j+1;
                            if (j == t.length()-1 && i < s.length()-1 && !wordComplete){
                                outPrint = "No";
                            }
                            break;
                        }
                    }
                }
            }
            if (!wordComplete){
                outPrint = "No";
            }

            System.out.println(outPrint);

        }

    }
}