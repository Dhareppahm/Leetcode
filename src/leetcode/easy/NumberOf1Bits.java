package leetcode.easy;

public class NumberOf1Bits {

	public static void main(String[] args) {
		NumberOf1Bits obj = new NumberOf1Bits();
		int n = 2147483646;
		int count = obj.hammingWeight(n);

        System.out.println("n = "+ n + " binary value = "+ Integer.toBinaryString(n));
        System.out.println("No. of 1's = "+count);
	}

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){      //Use n!=0 in the while condition, not n>0 as 2147483648 would correspond to -2147483648 in java
            //and the code would not enter the while if the condition is n>0 for n=2147483648.
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}