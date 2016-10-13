package leetcode.hard;

/*
https://leetcode.com/problems/valid-number/
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front 
before implementing one.
-----------------------------------------------------------------------------------------
Approach:
Make sure you handle all the possible cases like:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"1 1" => false
"2e10" => true
"2e" => false
"e9" => false
"005047e-9" => true
"  005047e+9   " => true
"." => false
"1." => true
".1" => true
*/
public class ValidNumber {

	public static void main(String[] args){
		ValidNumber obj = new ValidNumber();

		String input = " 005047e-9";
		boolean isValid = obj.isNumber(input);
		System.out.println(isValid);
	}

	public boolean isNumber(String s) {
		int i = 0, n = s.length();
		while(i < n && Character.isWhitespace(s.charAt(i))) i++;
		
		// check if +/-
		if(i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;		

		boolean isValid = false;
        while(i < n && Character.isDigit(s.charAt(i))) {	//if digits set valid to true and proceed
        		isValid = true; 
        		i++;        	        	
        }

        //check '.'. There should not be just a '.' It should be '1.' or '.1'
        if(i < n && s.charAt(i) == '.'){
			i++;
			while(i < n && (Character.isDigit(s.charAt(i))) ){
        		isValid = true;
        		i++;
        	}
        }

        //check exponent
        if(i < n && (s.charAt(i) == 'e' || s.charAt(i) == 'E') && isValid){     //check isValid as there should be at least 1 digit before e
            i++;
            isValid = false;                                    //set it to false to check if there is digit after e

            // check if +/-
            if(i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
            while(i < n && (Character.isDigit(s.charAt(i))) ){
                isValid = true;
                i++;
            }
        }

        //trailing spaces
        while(i < n && Character.isWhitespace(s.charAt(i))) i++;

//        System.out.println(isValid + " " + i + " " + n);

        //i will not be equal to n, if any other character is there in between
        return isValid && i == n;
    } 

}