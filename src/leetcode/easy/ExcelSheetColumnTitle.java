package leetcode.easy;

/**
 168. Excel Sheet Column Title
 https://leetcode.com/problems/excel-sheet-column-title/

 Given a positive integer, return its corresponding column title as appear in an Excel sheet.

 For example:

 1 -> A
 2 -> B
 3 -> C
 ...
 26 -> Z
 27 -> AA
 28 -> AB
 --------------------------------------------------------------------------------------------------------
 Instead of 1 -> A, 26 -> Z, we can assume that 0 -> A, 25 -> Z, and then here comes the base 26 representation,
 it's similar when you convert a number from base 10 to base 2

 */
public class ExcelSheetColumnTitle {
    public static void main(String[] args) {
        ExcelSheetColumnTitle obj = new ExcelSheetColumnTitle();
        int colNo = 2133;
        String colTitle = obj.convertToTitle(colNo);
        System.out.println(colTitle);
    }

    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n != 0){
            char ch = (char) ((n-1) % 26 + 'A');
            n = (n-1) / 26;
            sb.append(ch);
        }
        return sb.reverse().toString();
    }
}
