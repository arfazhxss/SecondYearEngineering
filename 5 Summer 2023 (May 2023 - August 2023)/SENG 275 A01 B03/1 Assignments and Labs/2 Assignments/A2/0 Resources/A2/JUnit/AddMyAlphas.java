package assignment2;

import java.util.ArrayList;
import java.util.List;

public class AddMyAlphas {
    public int Add(String numbers) {
        if (numbers== null || numbers.isEmpty()) { return 0; }

        String delimiter = ",";
        String numbersPart = numbers;

        if (numbers.startsWith("//")) { // Check for custom delimiter
            int delimiterIndex = numbers.indexOf("\n"); // Find newline after delimiter
            delimiter = numbers.substring(2, delimiterIndex); // Extract custom delimiter
            numbersPart = numbers.substring(delimiterIndex + 1); // Extract numbers part
        }

        String[] nums = numbersPart.split("[,\n" + delimiter + "]"); // Split numbers using delimiter(s)
        int sumOfNumbers = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String num : nums) {
            int currentNumber = Integer.parseInt(num);
            if (currentNumber < 0) { negatives.add(currentNumber); }
            else if (currentNumber <= 1000) { sumOfNumbers += currentNumber; }
        }
        if (!negatives.isEmpty()) { throw new IllegalArgumentException("Negatives not allowed: " + negatives); }

        return sumOfNumbers;
    }
}

