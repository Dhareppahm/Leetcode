package leetcode.easy;

import java.util.Arrays;

/**
 * 6. ZigZag Conversion
 * https://leetcode.com/problems/zigzag-conversion/

 The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"
 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string text, int nRows);
 convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
 n=numRows

 Δ=2n-2    1                           2n-1                         4n-3
 Δ=        2                     2n-2  2n                    4n-4   4n-2
 Δ=        3               2n-3        2n+1              4n-5       .
 Δ=        .           .               .               .            .
 Δ=        .       n+2                 .           3n               .
 Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
 Δ=2n-2    n                           3n-2                         5n-4

 1. For 1st and last rows: Jump by step=2*n-2
 2. For middle rows:
     a. Once take step1 = 2 * (n-1-i)
     b. then take step2 = step-step1
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        ZigZagConversion zzc = new ZigZagConversion();
        String s = "PAYPALISHIRING";
        int numRows = 4;
        String output = zzc.convert(s, numRows);
        System.out.println(output);
    }

    public String convert(String s, int numRows) {
        if(numRows <= 1){       //edge case
            return s;
        }

        StringBuilder sb = new StringBuilder();
        //step: each step to jump                     //IMP
        int step = 2 * numRows - 2;

        for(int i=0; i < numRows; i++){

            if(i==0 || i == numRows - 1){           //first and last rows: jump by step
                for(int j=i; j < s.length(); j = j+step){
                    sb.append(s.charAt(j));
                }
            }else{                                //middle rows
                int j=i;
                int step1 = 2 * (numRows - 1 - i);      //step changes for next row
                int step2 = step - step1;
                boolean flag = true;

                while(j < s.length()){
                    sb.append(s.charAt(j));
                    if(flag)                            //once take step1 and then step2
                        j = j + step1;
                    else
                        j = j + step2;
                    flag = !flag;
                }
            }
        }

        return sb.toString();
    }

}
