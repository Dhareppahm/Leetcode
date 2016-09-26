package leetcode.medium;

/**
 Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

 Note:
 The length of num is less than 10002 and will be ≥ k.
 The given num does not contain any leading zero.
 Example 1:

 Input: num = "1432219", k = 3
 Output: "1219"
 Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 Example 2:

 Input: num = "10200", k = 1
 Output: "200"
 Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 Example 3:

 Input: num = "10", k = 2
 Output: "0"
 Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 -----------------------------------------------------------------------------------------------------------------------
 #Greedy Approach:
 1. Now we get the rules to delete digits to get the least remaining number:
 - If there is a digit which is greater than the next one, delete such first digit.
 - If all digits in the number are increasingly sorted, the last digit gets deleted.
 2. The process repeats until the required k digits are deleted.

 121198   n=2
 first digit greater than next digit = 2, so 11198, n=1
 first digit greater than next digit = 9, so 1118, n=0
 Note: For opposite (Find largest number) find and remove first digit smaller than next one

 #Using Stack:

 */
public class RemoveKDigits {

    public static void main(String[] args) {
        RemoveKDigits obj = new RemoveKDigits();
        String num = "10200";//"1432219";
        int k = 1;
        String result = obj.removeKdigits(num, k);
        System.out.println(result);
    }

    public String removeKdigits(String num, int k) {

        if(num.length() < k){
            return "0";
        }

        StringBuilder sb = new StringBuilder(num);
        while(k > 0){
            int deleteIndex = getDeleteIndex(sb.toString());            //delete index of first number greater than next one
            sb.deleteCharAt(deleteIndex);
            k--;
        }
        //System.out.println(sb.toString());
        int i=0;
        while(i < sb.length() && sb.charAt(i) == '0'){                  //get non-zero index and return substring from there
            i++;
        }
        String result = sb.toString().substring(i);
        if(result.length() == 0){
            return "0";
        }
        return result;
    }

    private int getDeleteIndex(String s){
        //Find and return index of first number greater than next one
        for(int i=1; i < s.length(); i++){
            int prev = s.charAt(i-1) - '0';
            int curr = s.charAt(i) - '0';
            if(prev > curr){
                return i-1;
            }
        }
        //If no such number exists return last digit to delete
        return s.length()-1;
    }
}
