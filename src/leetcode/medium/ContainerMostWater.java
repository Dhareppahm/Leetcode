package leetcode.medium;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * 11. Container With Most Water
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines
 * are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
 * forms a container, such that the container contains the most water.
 *
 * Approach:
 1. Need to find max area between different lines.
 2. Keep 3 variables:
 maxArea = keeps track of max area found so far
 left = left index
 right = right index
 3. Scan from both sides. If leftHeight < rightHeight, move right and find a value that is greater than leftHeight.
 4. Similarly, if leftHeight > rightHeight, move left and find a value that is greater than rightHeight.
 5. Additionally, keep tracking the max value.

 Note: You may not slant the container.
 */
public class ContainerMostWater{
    public static void main(String[] args){
        ContainerMostWater obj = new ContainerMostWater();
        int[] height = {2,3,15,10,5,1};
        int maxArea = obj.maxArea(height);

        System.out.println("Ans:"+maxArea);
    }

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int maxArea = 0;
        int left = 0;						//start from 0 index
        int right = height.length - 1;		//from end of array

        while(left < right){
            int area = (right - left) * Math.min(height[right], height[left]);      //Area = l * b, min as water can only be filled till one of min heights
            maxArea = Math.max(maxArea, area);	//Area = l * b, keep track of maxArea
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
            System.out.println(maxArea);
        }

        return maxArea;
    }
}
