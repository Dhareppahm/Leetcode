package leetcode.hard;

import java.util.Stack;


/**
 85. Maximal Rectangle
 https://leetcode.com/problems/maximal-rectangle/

 Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 For example, given the following matrix:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0

 Return 6.
 -----------------------------------------------------------------------------------------------------------------------
 Algorithm:
 #This problem will use "Largest Rectangle in Histogram" method logic along with Dynamic Programming

 1. Create height matrix and keep 1st row as it is. From next row do below:
 2. If matrix[i][j] == 1
        height[i][j] += height[i-1][j] + 1;     //add 1 to value calculated in above cell
    Else
        Keep it 0
 3. Pass each row of height to "Find largest rectangle in histogram" and keep track of max area rectangle
 */
public class MaximumRectangle {

    public static void main(String[] args) {
        MaximumRectangle obj = new MaximumRectangle();
        char[][] matrix = {
                           // {'1', '0', '1', '0', '0'},
                           // {'1', '0', '1', '1', '1'},
                           // {'1', '1', '1', '1', '1'},
                           // {'1', '0', '0', '1', '0'}
                        };
        int maxRectangle = obj.maximalRectangle(matrix);
        System.out.println(maxRectangle);
    }

    public int maximalRectangle(char[][] matrix) {

        //check empty input
        if(matrix.length == 0)
            return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] heights = new int[rows][cols];      //maintain int[][] matrix so values can be easily added

        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                if(matrix[i][j] == '1'){
                    if(i > 0) {                     //check as we have i-1 condition
                        heights[i][j] += heights[i - 1][j] + 1;
                    }else{
                        heights[i][j] = 1;             //just put 1 as matrix has '1'
                    }
                }else{
                    heights[i][j] = 0;
                }
            }
        }

        //print heights to check - Debugging
        //print(heights);

        //Pass each row of height to - Find largest rectangle in histogram and keep track of maxArea
        int maxAreaRect = 0;
        for(int i=0; i < rows; i++){
            int[] histogram = heights[i];
            maxAreaRect = Math.max(maxAreaRect, largestAreaInHistogram(histogram));
        }

        return maxAreaRect;
    }


    /**
     * Same method for problem: Largest Rectangle in Histogram
     */
    private int largestAreaInHistogram(int[] histogram){
        int i=0, maxArea = 0;
        Stack<Integer> s = new Stack<>();       //store histogram bar index in stack

        int n = histogram.length;
        while(i < n){
            //If stack empty or current value > top value, push into stack and increment i
            if(s.isEmpty() || histogram[i] > histogram[s.peek()]){
                s.push(i++);
            }else{
                int topI = s.pop();
                int l = histogram[topI];
                int b = s.isEmpty() ? i : i - s.peek() - 1;
                maxArea = Math.max(l * b, maxArea);
            }
        }

        while (!s.isEmpty()){
            int topI = s.pop();
            int l = histogram[topI];
            int b = s.isEmpty() ? i : i - s.peek() - 1;

            if(l * b > maxArea){
                maxArea = l * b;
            }
        }

        return maxArea;
    }


    private void print(int[][] array){
        for(int i=0; i < array.length; i++){
            for(int j=0; j < array[0].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

}
