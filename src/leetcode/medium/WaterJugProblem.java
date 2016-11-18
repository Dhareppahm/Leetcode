package leetcode.medium;

/**
 365. Water and Jug Problem
 You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available.
 You need to determine whether it is possible to measure exactly z litres using these two jugs.

 If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.

 Operations allowed:

 Fill any of the jugs completely with water.
 Empty any of the jugs.
 Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
 Example 1: (From the famous "Die Hard" example)

 Input: x = 3, y = 5, z = 4
 Output: True
 Example 2:

 Input: x = 2, y = 6, z = 5
 Output: False
 ------------------------------------------------------------------------------------------------------------------------
 ## Approach:

 Pure Math Problem:
 https://discuss.leetcode.com/topic/49238/math-solution-java-solution
 The basic idea is to use the property of Bézout's identity and check if z is a multiple of GCD(x, y)

 Wiki : https://en.wikipedia.org/wiki/B%C3%A9zout's_identity

 1. Find GCD(x, y) = d
 2. If z % d == 0 ? return true : return false;
 3. IMP: Also check for edge cases
 */
public class WaterJugProblem {

    public static void main(String[] args) {
        WaterJugProblem obj = new WaterJugProblem();
        int x = 3, y = 5, z = 4;
        boolean ans = obj.canMeasureWater(x,y,z);
        System.out.println(ans);
    }

    public boolean canMeasureWater(int x, int y, int z) {
        //Edge cases
        //Limit brought by the statement that water is finallly in one or both buckets
        if(x + y < z)  return false;

        //case x or y is zero
        if(x == z || y == z || z + y == z)  return true;

        //get GCD, then we can use the property of Bézout's identity
        int d = gcd(x,y);
        return z % d == 0;
    }

    private int gcd(int a, int b){
        while(b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
