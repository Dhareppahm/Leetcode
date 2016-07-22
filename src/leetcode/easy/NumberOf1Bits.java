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
        while(n != 0){
            count = count + (n & 1);    //n in bit AND with 1 and if this operation result is 1, so last bit of n is 1.
                                        // Thus we add it to the 1s count.
            n = n >>> 1;                //Then we shift the input Integer by one on the right, to check for the next bit.
        }
        return count;
    }
}