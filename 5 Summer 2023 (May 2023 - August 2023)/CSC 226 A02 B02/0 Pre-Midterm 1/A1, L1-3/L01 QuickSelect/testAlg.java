import java.util.Random;

public class testAlg {
    private static int pickRandomPivot(int left, int right) {
        int index = 0;
        Random rand = new Random();
        index = left + rand.nextInt(right - left + 1);
        return index;
    }

    public static void main(String[] args) {
        int left = 0, right = 10;
        for (int i = 0; i < 20; i++) {
            if ((left<right)&&(left>=0)&&(right<=10)) System.out.println("Left: " + left + " Right: " + right + " pickRandomPivot: " + pickRandomPivot(left, right));
            else {
                right = 10;
                left = 0;
            }
            ++left;
            --right;
        }
    }
}