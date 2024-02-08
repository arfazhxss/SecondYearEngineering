package lab01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {
    @Test
    void sayHi() {
        System.out.println("\n\nThe Test Worked!\n\n");
    }

    // A sorted array
    @Test
    void sortedAAA() {
        int[] someArray = {1,2,3,4};       // arrange
        boolean someArraySorted = ArrayUtils.isSorted(someArray);  // act
        assertTrue(someArraySorted);       // assert
        sayHi();
    }

    // A sorted array - all at once
    @Test
    void sorted() {
        assertTrue(ArrayUtils.isSorted(new int[] {1,2,3,4}));
        sayHi();
    }

    // Empty arrays are sorted by definition
    @Test
    void emptyArr() {
        int[] someArray = {};
        boolean someArraySorted = ArrayUtils.isSorted(someArray);
        assertTrue(someArraySorted);
        sayHi();
    }
    // Arrays of one element are sorted by definition
    @Test
    void oneMemberArr() {
        int[] someArray = {76};
        boolean someArraySorted = ArrayUtils.isSorted(someArray);
        assertTrue(someArraySorted);
        sayHi();
    }
    // A partially sorted array (some elements are in sorted order, but some aren't)
    @Test
    void someSortedArr() {
        int[] someArray1 = {1,2,3,15,500,655,435,345};
        boolean someArraySorted = ArrayUtils.isSorted(someArray1);
        assertFalse(someArraySorted);
        int[] someArray2 = {3,1,4,2,6,7,8,9};
        someArraySorted = ArrayUtils.isSorted(someArray2);
        assertFalse(someArraySorted);
        sayHi();
    }
    // A completely unsorted array (no elements are in sorted order)
    @Test
    void fullySortedArr() {
        int[] someArray = {655,345,122,433,100,89,355};
        boolean someArraySorted = ArrayUtils.isSorted(someArray);
        assertFalse(someArraySorted);
        sayHi();
    }
    // An array with duplicate values (may be sorted or not depending on the values chosen)
    @Test
    void dupledArr() {
        int[] someArray = {655,345,433,433,345,89,655};
        boolean someArraySorted = ArrayUtils.isSorted(someArray);
        assertFalse(someArraySorted);
        sayHi();
    }
}



