import java.io.*;
import java.util.*;

public class Main {
    private static HashMap<Character, Integer> romanNumerals;
    private static ArrayList<Integer> sumArray;

    private static int[] solutionCount;

    public static void main(String[] args) throws IOException {
        BufferedReader instream = new BufferedReader(new InputStreamReader(System.in));

        romanNumerals = new HashMap<>();

        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);
        romanNumerals.put('C', 100);
        romanNumerals.put('D', 500);
        romanNumerals.put('M', 1000);

        String input;
        while(!(input = instream.readLine()).equals("#")){
            sumArray = new ArrayList<>();

            String[] parts1 = input.split("\\+");
            String firstPart = parts1[0];
            String secondPartWithEquals = parts1[1];

            String[] parts2 = secondPartWithEquals.split("=");
            String secondPart = parts2[0];
            String thirdPart = parts2[1];

            String[] resultArray = new String[]{firstPart, secondPart, thirdPart};


            boolean valid = true;
            for (String s : resultArray) {
                if (checkRomanValid(s).equals("Incorrect")){
                    valid = false;
                }
            }

            if (valid){
                if (!(sumArray.get(0) + sumArray.get(1) == sumArray.get(2))){
                    valid = false;
                }
            }
            if (valid){
                System.out.print("Correct ");
            }
            else {
                System.out.print("Incorrect ");
            }
            sumArray = new ArrayList<>();

            HashMap<Character, Integer> arabicMap = new HashMap<>();

            ArrayList<Character> charArray = new ArrayList<>();


            int value = 0;
            for (String s : resultArray) {
                int size = s.length();
                for (int i = 0; i < size; i++) {
                    if (!arabicMap.containsKey(s.charAt(i))){
                        arabicMap.put(s.charAt(i), ++value);
                        charArray.add(s.charAt(i));
                    }
                }
            }

            checkRomanValid(resultArray[0], resultArray[1], resultArray[2]);

            if (solutionCount[0] == 0){
                System.out.print("impossible");
                System.out.println();
            }else if (solutionCount[0] == 1){
                System.out.print("valid");
                System.out.println();
            }else {
                System.out.print("ambiguous");
                System.out.println();
            }

        }
    }

    private static void checkRomanValid(String... strings) {
        Set<Character> uniqueChars = getUniqueChars(strings);
        Map<Character, Integer> charToDigit = new HashMap<>();
        solutionCount = new int[1];

        backtrack(uniqueChars, strings, charToDigit, 2);
    }


    private static boolean backtrack(Set<Character> remainingChars, String[] strings, Map<Character, Integer> charToDigit, int solutionsToFind) {
        if (remainingChars.isEmpty()) {
            if (checkEquation(strings, charToDigit)) {
                solutionCount[0]++;
                return solutionCount[0] == solutionsToFind;  //if 2 solutions are found then exit
            }
            return false;
        }

        char currentChar = remainingChars.iterator().next();
        remainingChars.remove(currentChar);

        int startValue = (currentChar == strings[0].charAt(0) || currentChar == strings[1].charAt(0) || (currentChar == strings[2].charAt(0))) ? 1 : 0;

        for (int i = startValue; i < 10; i++) {
            if (!charToDigit.containsValue(i)) {
                charToDigit.put(currentChar, i);
                if (backtrack(remainingChars, strings, charToDigit, solutionsToFind)) {
                    return true;
                }
                charToDigit.remove(currentChar);
            }
        }

        remainingChars.add(currentChar);
        return false;
    }


    private static boolean checkEquation(String[] strings, Map<Character, Integer> charToDigit) {
        int sum = 0;
        for (int i = 0; i < 2; i++) {
            String s = strings[i];
            int value = getValue(s, charToDigit);
            sum += value;
        }

        String targetString = strings[2];
        int targetValue = getValue(targetString, charToDigit);

        return sum == targetValue;
    }

    private static int getValue(String s, Map<Character, Integer> charToDigit) {
        int value = 0;
        int multiplier = 1;
        for (int j = s.length() - 1; j >= 0; j--) {
            char c = s.charAt(j);
            value += charToDigit.get(c) * multiplier;
            multiplier *= 10;
        }
        return value;
    }

    private static Set<Character> getUniqueChars(String... strings) {
        Set<Character> uniqueChars = new HashSet<>();
        for (String s : strings) {
            for (char c : s.toCharArray()) {
                uniqueChars.add(c);
            }
        }
        return uniqueChars;
    }

    private static String checkRomanValid(String s) {
        //1. A letter from the left column can never appear more than three times in a row, and there can never be more than one other occurrence of that letter.
        //2. A letter from the right column can never appear more than once
        //3. Once a letter has been used in a ‘negative’ position, all subsequent characters (apart from the one immediately following) may not be greater than that character.

        char[] charArray = s.toCharArray();

        int sum = 0;

        int sameLetterCount = 0;
        char lastChar = '-';
        boolean isFive = false;

        for (char c : charArray) {
            if (!romanNumerals.containsKey(c)) {
                return "Incorrect";
            }

            if (c == 'L' || c == 'V' || c == 'D') {
                if (isFive) {
                    return "Incorrect";
                }
                isFive = true;
            } else {
                isFive = false;
            }

            if (lastChar != '-'){
                if (romanNumerals.get(c) > romanNumerals.get(lastChar)) {
                    if (!isValidSubtractivePair(lastChar, c)) {
                        return "Incorrect";
                    }
                    sum += (romanNumerals.get(c) - 2 * romanNumerals.get(lastChar));
                } else if (romanNumerals.get(c).equals(romanNumerals.get(lastChar))) {
                    sameLetterCount++;
                    if (sameLetterCount > 3) {
                        return "Incorrect";
                    }
                    sum += romanNumerals.get(c);

                } else {
                    sameLetterCount = 1;
                    sum += romanNumerals.get(c);
                }
            }
            else {
                sum += romanNumerals.get(c);
            }

            lastChar = c;
        }

        sumArray.add(sum);

        return "Correct";
    }

    private static boolean isValidSubtractivePair(char smaller, char larger) {
        String subtractivePair = String.valueOf(smaller) + String.valueOf(larger);

        return subtractivePair.equals("IV") || subtractivePair.equals("IX") ||
                subtractivePair.equals("XL") || subtractivePair.equals("XC") ||
                subtractivePair.equals("CD") || subtractivePair.equals("CM");
    }
}