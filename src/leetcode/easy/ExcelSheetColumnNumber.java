package leetcode.easy;

/**
 171. Excel Sheet Column Number
 https://leetcode.com/problems/excel-sheet-column-number/

 Related to question Excel Sheet Column Title (https://leetcode.com/problems/excel-sheet-column-title/)

 Given a column title as appear in an Excel sheet, return its corresponding column number.

 For example:
 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28
 -----------------------------------------------------------------------------------------------------------------------
Approach:
 result = result * 26 + (subtract by 'A' to get value between 0-26)

 */
public class ExcelSheetColumnNumber {

    public int titleToNumber(String s) {
        int result = 0;
        for(int i=0; i < s.length(); i++){
            result = result * 26 + (((int)s.charAt(i) - 'A') + 1);    //result = result * 26 + (subtract from 'A' to get value between 0-26)
        }
        return result;
    }

    public static void main(String[] args) {
        ExcelSheetColumnNumber excel = new ExcelSheetColumnNumber();
        String col = "ZZ";
        int colNo = excel.titleToNumber(col);
        System.out.println("Column: "+ col + " value: "+ colNo);
    }
}
