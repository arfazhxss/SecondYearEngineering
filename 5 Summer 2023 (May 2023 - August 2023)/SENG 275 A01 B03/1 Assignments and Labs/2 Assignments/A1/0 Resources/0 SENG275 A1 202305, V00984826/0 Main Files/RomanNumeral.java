package assignment1;
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
        if (s==null || s.length() == 0 || s.trim().isEmpty()) { return 0; }
        s=s.trim().toUpperCase(); // Mixed Case Test
        int convertedNumber = 0;
        int prev = 3999;
        int repeat = 0; // count repetitive cases of I's, X's, C's before a big number
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) { return 0; } // One Letter Test
            else if (i+1 < s.length() && (!map.containsKey(s.charAt(i+1)))) { return 0; } // Invalid Combinations
            int next = (i + 1 < s.length()) ? map.get(s.charAt(i + 1)) : 0;
            int currentNumber = map.get(s.charAt(i));
            if ((prev == currentNumber) && (prev == 5 || prev == 50 || prev == 500)) { return 0; } // Invalid Subtractive Combinations Test
            boolean RepeatState = ((prev == currentNumber) && (prev == 1 || prev == 10 || prev == 100 || prev == 1000)); // if current number == I/X/C == previous number <-- state: true
            if ((repeat = RepeatState ? ++repeat : 0) >= 3) { return 0; } // InValid Repetitive Test (If more than 3 I's/X's/C's <-- return 0) //  Maximum Roman Numeral Test
            if (currentNumber < next && (RepeatState)) { return 0; } // additive combination test addition
            else if (currentNumber >= next) { convertedNumber += currentNumber; }
            else if (currentNumber == 1 || currentNumber == 10 || currentNumber == 100) { convertedNumber -= currentNumber; } // substractive combinations test
            else { return 0; } // subtractive combinations test
            prev = currentNumber; // additive combination test
        }
        return convertedNumber;
    }
}
