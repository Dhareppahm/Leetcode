package leetcode.medium;

/**
 59. Spiral Matrix II
 https://leetcode.com/problems/spiral-matrix-ii/
 Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

 For example,
 Given n = 3,

 You should return the following matrix:
 [
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
 ]
-----------------------------------------------------------------------------------------------------------------------
 Approach:
 While (true)
 Initialize row = 0, col = -1;
 1. traverse all columns, first row. Decrement rows and check break condition
 2. traverse all rows, last col.     Decrement columns and check break condition
 3. traverse all columns, last row.  Decrement rows and check break condition
 4. traverse all rows, first col.    Decrement columns and check break condition

 In above traversing logic, keep a counter and increment it
 */
public class SpiralMatrix2 {

    public static void main(String[] args) {
        SpiralMatrix2 obj = new SpiralMatrix2();
        int n = 4;
        int[][] matrix = obj.generateMatrix(n);
        obj.displayMatrix(matrix);
    }

    public int[][] generateMatrix(int size) {
        int[][] matrix = new int[size][size];

        if(size == 0){
            return matrix;
        }

        int m = size;   //m = rows
        int n = size;   //n = cols

        //Initialize row and column
        int row = 0;
        int col = -1;

        int counter=1;      //counter to put values

        while(true){

            for(int i=0; i < n; i++){   //fill first row, all columns
                matrix[row][++col] = counter++;
            }
            if(--m == 0) break;         //check if rows end

            for(int i=0; i < m; i++){   //fill all rows, last column
                matrix[++row][col] = counter++;
            }
            if(--n == 0) break;         //check cols end

            for(int i=0; i < n; i++){   //fill last row, all columns
                matrix[row][--col] = counter++;
            }
            if(--m == 0) break;         //check rows end

            for(int i=0; i < m; i++){   //fill all rows, first column
                matrix[--row][col] = counter++;
            }
            if(--n == 0) break;         //check cols end

        }

        return matrix;
    }

    private void displayMatrix(int[][] matrix){
        for(int i=0; i < matrix.length; i++){
            for(int j=0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println();
        }
    }

}
