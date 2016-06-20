package leetcode.medium;

import java.util.Arrays;
/**
 * Given a non negative integer number num. For every numbers i in the range 0 <= i <= num calculate the number of 1's in
 * their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n), possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
Hint:

You should make use of what you have produced already.
Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
Or does the odd/even status of the number help you in calculating the number of 1s?
 */
public class CountingBits {

	public static void main(String[] args) {
		CountingBits cb = new CountingBits();
        int num = 32;

        int[] counts = cb.countBits_Naive(num);
		System.out.println(Arrays.toString(counts));

        int[] counts1 = cb.countBits(num);
        System.out.println(Arrays.toString(counts1));
    }
	
	/**
	 *  Improved Solution: 
	 * For number 2(10), 4(100), 8(1000), 16(10000), ..., the number of 1's is 1. 
	 * Any other number can be converted to be 2^m + x. 
	 * For example, 9=8+1, 10=8+2. The number of 1's for any other number is
	 *  1 + # of 1's in x.
     *---------------------------------------------------------------------------
     * Approach:
     * 1. Keep a results array from which we will resuse previous results
     * 2. If multiple of 2, then result[i] = 1. eg, 2,4,8,16
     *      Keep power variable to store next multiple of 2.
     *      Left shift power by 1 => multiply 2 and reset index for referring number from multiple of 2.
     *3. Else
     *      add 1 to previous result and increment index
     *
     *  Complexity: O(n) - we are reusing previously calculate result
	 */
	public int[] countBits(int num) {
	    int[] result = new int[num+1];
	    
	    int index = 1;              //index for referencing number from multiple of 2
	    int power = 1;
	    for(int i = 1; i <= num; i++) {
	        if(i == power){			//multiples of 2
	            result[i] = 1;
	            power = power << 1;
	            index = 1;          //reset index to 1
	        }else{					//2^n + x
	            result[i] = result[index] + 1;
	            index++;
	        }	 
	    }	 
	    return result;
	}


	//Naive Approach: Loop from 0 to num and find number of 1's in it. Store it in array
    //Complexity: O(n * no_of_set_bits_in_number)
	public int[] countBits_Naive(int num) {
	    int[] result = new int[num+1]; 
	    for(int i=0; i <= num; i++){
	        result[i] = countSetBits(i);
	    }	 
	    return result;
	}

	public int countSetBits(int num){
	    int count = 0;          // count accumulates the total bits set
	    while(num > 0){
	        count++;
            num = num & num-1;  // clear the least significant bit set
	    }	 
	    return count;
	}

}
