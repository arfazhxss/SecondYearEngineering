// number operations to convert str1 to str2
// First need to compile it in terminal: 
		//javac EditDist.java
// In order to run it in terminal: 
		//java EditDist str1 str2
class EditDist {
    static int min(int x, int y, int z) {
        if (x <= y && x <= z)
            return x;
        if (y <= x && y <= z)
            return y;
        else
            return z;
    }

    static int editDistDP(String str1, String str2, int m, int n) {
        // Create a table to store results of subproblems
        int D[][] = new int[m + 1][n + 1];

        // Step 1: Find the base subproblems (initialization)
        for (int i = 0; i <= m; i++)
            D[i][0] = i;
        for (int j = 0; j <= n; j++)
            D[0][j] = j;

        // Step 2: Update matrix D for bigger subproblems
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If their last characters are the same, no operation needed (diagonal value)
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    D[i][j] = D[i - 1][j - 1];
                else {
                    // If their last characters are different, find the minimum cost of operations
                    // (1 + minimum of (left, diagonal, above))
                    D[i][j] = 1 + min(D[i][j - 1], D[i - 1][j], D[i - 1][j - 1]);
                }
            }
        }

        // The value in the bottom-right cell represents the minimum edit distance
        return D[m][n];
    }

    public static void main(String args[]) {
        String str1 = args[0];
        String str2 = args[1];
        System.out.println(editDistDP(str1, str2, str1.length(), str2.length()));
    }
}


