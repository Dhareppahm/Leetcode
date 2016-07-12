package leetcode.medium;

/**
 https://leetcode.com/problems/unique-paths/
 Very Nice explanation: https://www.youtube.com/watch?v=GO5QHC_BmvM
 A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner
 of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?
 -----------------------------------------------------------------------------------------------------------------------
 Approach: (Explanation: https://www.youtube.com/watch?v=GO5QHC_BmvM)
    1. DP formula: (Top-down approach)
        T[i][j] = T[i-1][j] + T[i][j-1]
    2. Using this formula, the final answer will be in bottom right cell of T[][]
    3. Eg:
        4 x 4 matrix
        1   1   1   1
        1   2   3   4
        1   3   6   10
        1   4   10  20

        Result = 20
 */
public class UniquePaths {

    public static void main(String[] args) {
        UniquePaths up = new UniquePaths();

        int result = up.uniquePaths(4,4);
        System.out.println(result);
    }


    public int uniquePaths(int m, int n) {
        int[][] T = new int[m][n];

        //Fill first row and first column T[][] with 1 as there will be always 1 path - either from right or bottom
        for(int i=0; i < m; i++){
            T[i][0] = 1;                //fill 1st column
        }
        for(int i=0; i < n; i++){
            T[0][i] = 1;                //fill 1st row
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                T[i][j] = T[i-1][j] + T[i][j-1];
            }
        }

        return T[m-1][n-1];             //final answer will be in bottom-right corner
    }

}
