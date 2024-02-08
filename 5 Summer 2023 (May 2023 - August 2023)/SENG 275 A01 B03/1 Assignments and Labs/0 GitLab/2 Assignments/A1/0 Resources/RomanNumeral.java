// package assignment1;

import java.util.*;

public class RomanNumeral {
    private static Map<Character, Integer> map;
    static {
        map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public static int convert(String s) {
        // Mixed Case Test
        s=s.toUpperCase(); 
        if (s.length() == 0 || s.trim().isEmpty()) { return 0; }
        int convertedNumber = 0;
        int prev = 3999;
        // count repeatitive cases of I's, X's, C's before a big number
        int repeat = 0; 
        for (int i = 0; i < s.length(); i++) {
            // One Letter Test
            if (!map.containsKey(s.charAt(i))) { 
                return 0; 
            } 

            // Invalid Combinations
            else if (i+1 < s.length() && (!map.containsKey(s.charAt(i+1)))) { 
                return 0; 
            } 

            // Finding the character next to the string (if there is one)
            int next = (i + 1 < s.length()) ? map.get(s.charAt(i + 1)) : 0;
            int currentNumber = map.get(s.charAt(i));

            // Invalid Subtractive Combinations Test
            if ((prev == currentNumber) && (prev == 5 || prev == 50 || prev == 500)) { 
                return 0; 
            } 

            // if current number == I/X/C == previous number <-- state: true
            boolean RepeatState = ((prev == currentNumber) && (prev == 1 || prev == 10 
            || prev == 100 || prev == 1000)); 

            // InValid Repetitive Test (If more than 3 I's/X's/C's <-- return 0) 
            // Maximum Roman Numeral Test
            if ((repeat = RepeatState ? ++repeat : 0) >= 3) { 
                return 0; 
            } 

            // additive combination test addition
            // substractive combinations test
            if (currentNumber < next && (RepeatState)) { 
                return 0; 
            } else if (currentNumber >= next) { 
                convertedNumber += currentNumber; 
            } else if (currentNumber == 1 || currentNumber == 10 || currentNumber == 100) { 
                convertedNumber -= currentNumber; 
            } else { 
                return 0; 
            }
            prev = currentNumber;
        }
        return convertedNumber;
    }
}
