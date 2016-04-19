package leetcode.easy;

/**
 * 258. Add Digits 
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
	For example:
	Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. 
	Since 2 has only one digit, return it.
 * 
 * Digital Roots :  digital root of a positive integer as the position it holds with respect to the largest multiple of 9 
 * less than it. For example, the digital root of 11 is 2, which means that 11 is the second number after 9.
 * Likewise, the digital root of 2035 is 1, which means that 2035 - 1 is a multiple of 9. 
 * If a number produces a digital root of exactly 9, then the number is a multiple of 9.
 * 
 * Formula :   dr(n) = 1 + ((n-1) % 9)
 */
public class AddDigits {

	public static void main(String[] args){
		int num = 12345;
		int sum1 = addDigits_BruteForce(num);
		System.out.println("Using Brute Force: "+sum1);

		int sum2 = addDigits_DigitalRoots(num);
		System.out.println("Using Digital Roots: "+sum2);
	}

	private static int addDigits_DigitalRoots(int num) {
		if(num > 9){
			return 1 + ((num-1) % 9);
		}else{
			return num;
		}
	}

	private static int addDigits_BruteForce(int num) {
		int sum = num;				//init sum to num, so if num < 9, it doesnt enter loop and returns same num
		while(num > 9){				//outer loop to again add digits if sum is > 9
			sum = 0;
			while(num > 0) {		//inner loop gets a digit one-by-one and returns sum
				sum += num % 10;
				num = num / 10;		
			}
			num = sum;
		}
		return sum;
	}
}
