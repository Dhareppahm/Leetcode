package leetcode.hard;

import java.util.Stack;

/**
 84. Largest Rectangle in Histogram

 https://leetcode.com/problems/largest-rectangle-in-histogram/

 Ref: http://www.geeksforgeeks.org/largest-rectangle-under-histogram/

 Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 find the area of largest rectangle in the histogram.

 Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

 The largest rectangle is shown in the shaded area, which has area = 10 unit.

 For example,
 Given heights = [2,1,5,6,2,3],
 return 10.
 -----------------------------------------------------------------------------------------------------------------------
 Algorithm:
 # Key to solve this problem is to maintain a Stack to store bars indexes. The stack only keeps the increasing bars.
 1) Create an empty stack.

 2) Start from first bar, and do following for every bar ‘heights[i]’ where ‘i’ varies from 0 to n-1.
    a) If stack is empty or heights[i] is greater than the bar at top of stack, then push ‘i’ to stack.
    b) If this bar is smaller than the top of stack, then keep removing the top of stack while top of the stack is greater.
       Let the removed bar be heights[topIndex]. Calculate area of rectangle with:
            Height = heights[topIndex] and
            Width = currentIndex - previous topIndex - 1
            i.e For heights[topIndex], the ‘left index’ is previous (previous to topIndex) item in stack
            and ‘right index’ is ‘i’ (current index).

 3) If the stack is not empty, then one by one remove all bars from stack and do step 2.b for every removed bar.
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        LargestRectangleInHistogram obj = new LargestRectangleInHistogram();
        int[] heights = {2,4,6,8,10,12};//{2,1,5,6,2,3};
        int maxArea = obj.largestRectangleArea(heights);
        System.out.println(maxArea);
    }

    public int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();       //vvv imp: maintain stack to store bars indexes

        int i=0, maxArea = 0;

        while(i < heights.length){

            //push index to stack when the current height is larger than the height of top index of stack
            if(stack.isEmpty() || heights[i] > heights[stack.peek()]){
                stack.push(i);
                i++;
            }else{
                //calculate max value when the current height is less than the previous one
                int topIndex = stack.pop();
                int h = heights[topIndex];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;     //Imp to find width

                maxArea = Math.max(h * w, maxArea);
            }
        }

        //If stack no empty, follow above step 2b (case when all increasing bars)
        while (!stack.isEmpty()){
            int topIndex = stack.pop();
            int h = heights[topIndex];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1;

            maxArea = Math.max(h * w, maxArea);
        }

        return maxArea;
    }
}
