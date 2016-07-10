package leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 https://leetcode.com/problems/spiral-matrix/
 54. Spiral Matrix
 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 For example,
 Given the following matrix:

 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 You should return [1,2,3,6,9,8,7,4,5].
 -----------------------------------------------------------------------------------------------------------------------
Approach:
 While (true)
        Initialize row = 0, col = -1;
     1. traverse all columns, first row. Decrement rows and check break condition
     2. traverse all rows, last col.     Decrement columns and check break condition
     3. traverse all columns, last row.  Decrement rows and check break condition
     4. traverse all rows, first col.    Decrement columns and check break condition

 */
public class SpiralMatrix {
    public static void main(String[] args) {
        SpiralMatrix sm = new SpiralMatrix();

        int[][] matrix = {  {1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9,  10, 11, 12},
                            {13, 14, 15, 16},
                            {17, 18, 19, 20},
                            {21, 22, 23, 24},
                            {25, 26, 27, 28}
                        };

        List<Integer> result = sm.spiralOrder(matrix);
        System.out.println(result.toString());

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        if(matrix.length == 0){         //empty matrix, return empty matrix
            return list;
        }
        int m = matrix.length;          //m = rows, n = columns
        int n = matrix[0].length;

        int row = 0, col = -1;          //IMP: Initialization to these values is IMP, to handle edge cases of ArrayIndexOutOfBounds
        while (true){

            for(int i = 0; i < n; i++) {         // I. traverse all columns, first row
                list.add(matrix[row][++col]);
            }

            if(--m == 0) break;                 //break check rows

            for(int i = 0; i < m; i++){         // II. traverse all rows, last col
                list.add(matrix[++row][col]);
            }

            if(--n == 0) break;                 //break check columns

            for(int i=0; i < n; i++){           // III. traverse all columns, last row
                list.add(matrix[row][--col]);
            }

            if(--m == 0) break;                 //break check rows

            for(int i=0; i < m; i++){           // IV. traverse all rows, first col
                list.add(matrix[--row][col]);
            }

            if(--n == 0) break;                 //break check columns

        }

        return list;
    }
}
