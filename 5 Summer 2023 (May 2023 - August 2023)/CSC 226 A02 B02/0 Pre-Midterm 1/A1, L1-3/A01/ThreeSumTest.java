import java.util.Arrays;

public class ThreeSumTest {
    public static int[][] threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); // Sorting the array in non-decreasing order

        int[][] triplets = new int[n * n][3];
        int count = 0;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {continue;} // Skip duplicates

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                if (currentSum == 0) {
                    triplets[count][0] = nums[i];
                    triplets[count][1] = nums[left];
                    triplets[count][2] = nums[right];
                    count++;

                    // Skip duplicates
                    while (left < right && nums[left] == nums[left + 1]) {left++;}
                    while (left < right && nums[right] == nums[right - 1]) {right--;}
                    left++;
                    right--;
                } 
                else if (currentSum < 0) {left++;} 
                else {right--;}
            }
        }

        return Arrays.copyOf(triplets, count);
    }

    public static void main(String[] args) {
        int[] nums = { -1, 0, 1, 2, -1, -4 };
        int[][] result = threeSum(nums);

        // Printing the result
        for (int[] triplet : result) {
            System.out.println(Arrays.toString(triplet));
        }
    }
}
