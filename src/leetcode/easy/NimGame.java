package leetcode.easy;

public class NimGame {
	
	public static void main(String[] arg){
		System.out.println(canWinNim(15));
	}
	
	public static boolean canWinNim(int n) {
        if(n % 4 == 0){
            return false;
        }else{
            return true;
        }
    }
}
