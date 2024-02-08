package lab07;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;
import org.junit.jupiter.api.Test;

class PalindromeTest {

    @Property
    void reverseString (@ForAll("strings") String str) {
        String reversedStr = reverse(str);
        String palindrome = str + reversedStr;
        assertThat(Palindrome.isPalindrome(palindrome)).isTrue();
    }

    @Property
    void singleCharacterString (@ForAll("strings") String str, @ForAll char character) {
        String reversedStr = reverse(str);
        String palindrome = str + character + reversedStr;
        assertThat(Palindrome.isPalindrome(palindrome)).isTrue();
    }


    @Property
    void palindromeSetToUppercase (@ForAll("strings") String str) {
        String palindrome = str + reverse(str);
        String uppercasePalindrome = palindrome.toUpperCase();
        assertThat(Palindrome.isPalindrome(uppercasePalindrome)).isTrue();
    }

    @Provide
    Arbitrary<String> strings() {
        return Arbitraries.strings().ascii().ofMinLength(1);
    }

    static String reverse (String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }
}
