package leetcode.medium;

/**
 https://leetcode.com/problems/unique-paths-ii/
 63. Unique Paths II
 Follow up for "Unique Paths":

 Now consider if some obstacles are added to the grids. How many unique paths would there be?

 An obstacle and empty space is marked as 1 and 0 respectively in the grid.

 For example,
 There is one obstacle in the middle of a 3x3 grid as illustrated below.

 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 The total number of unique paths is 2.

 Note: m and n will be at most 100.
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
    1. Same DP formula but if Obstacle present then T[i][j] = 0
    2. Trick here is filling the initial row and column to 1. Fill 1 only till there is no obstacle, as you can go till there
        After obstacle break, i.e keep it 0 as you cannot reach if there is obstacle

 eg. {  {0,0},
        {1,1},
        {0,0} };
  then initial row and column will be
        1  1
        0  0
        0  0    <- this cannot be reached as obstacle in middle, so break and don't fill it with 1.

 */
public class UniquePaths2 {

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,0},{1,1},{0,0}};
                //{{1,0}};
//                {{1}};
               /* {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
                    };*/
        UniquePaths2 up2 = new UniquePaths2();
        int result = up2.uniquePathsWithObstacles(obstacleGrid);
        System.out.println(result);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;        //rows
        int n = obstacleGrid[0].length;     //columns

        int[][] T = new int[m][n];          //create matrix with 1 extra row and column and add it with 1

        //Check a critical condition if no path as there is just obstacle
        if(obstacleGrid[0][0] == 1){
            return 0;
        }else{
            T[0][0] = 1;
        }

        //Fill 1st row and 1st column as 1 if there is no obstacle present
        for(int i=0; i < m; i++){
            if(obstacleGrid[i][0] == 0){    //Check no obstacle present then put 1 in initial column
                T[i][0] = 1;
            }else {
                break;                      //break so as it keep it 0 as it can't be reached
            }
        }

        for(int i=0; i < n; i++){
            if(obstacleGrid[0][i] == 0){    //Check obstacle condition while filling initial row
                T[0][i] = 1;
            }else {
                break;                      //break so as it keep it 0 as it can't be reached
            }
        }


        for(int i=1; i < m; i++){
            for(int j=1; j < n; j++){
                if(obstacleGrid[i][j] == 1){        //if obstacle present, add 0 else same formula
                    T[i][j] = 0;
                }else {
                    T[i][j] = T[i - 1][j] + T[i][j - 1];
                }
            }
        }

        return T[m-1][n-1];
    }
}
