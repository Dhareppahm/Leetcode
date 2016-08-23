package leetcode.medium;

import java.util.Stack;

/**
 388. Longest Absolute File Path
 https://leetcode.com/problems/longest-absolute-file-path/

 http://qa.geeksforgeeks.org/6922/google-interview-question-question-google-interview-solution
 https://discuss.leetcode.com/topic/31565/the-longest-absolute-path-in-file-system/8

 Approach:
 1. Create a class to hold level where the directory is present and absolute length of that directory
 2. Split by \n and get all files and directories
 3. IMP: Maintain a stack (like DFS) to keep track of each level
 4. If File
 Check if current level same as stack top level, if yes then pop as file is not in that top dir,
 else
 calculate absolute length of file and compare it with maxLength
 5. If Dir
 Check if current level same as stack top level, if yes then pop till top level is less than current level ie reach parent dir
 Push the directory entry into stack
 6. Return maxLen;
 */

class Directory {
    String fileName;
    int level;
    int currLength;
}

public class LongestAbsoluteFilePath {
    public static void main(String[] args) {
        LongestAbsoluteFilePath obj = new LongestAbsoluteFilePath();
        String s = //"a\n\tb\n\t\tc\n\t\t\td\n\t\t\t\te.txt\n\t\t\t\talsdkjf.txt\n\t\tskdjfl.txtlsdkjflsdjflsajdflkjasklfjkasljfklas\n\tlskdjflkajsflj.txt";
        "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";//"a.txt";
        int maxLen = obj.lengthLongestPath(s);
        System.out.println(maxLen);
    }

    public int lengthLongestPath(String input) {

        String[] dirs = input.split("\n");
        if(dirs.length == 1 && input.contains(".")){        //if only file present without any directory
            return input.length();
        }

        Stack<Directory> stack = new Stack<>();              //Only push directories to stack
        int maxLen = 0;
        for(String dir : dirs) {

            int currLevel = 0;
            while (dir.charAt(currLevel) == '\t')            //calculate current level by counting spaces
                currLevel++;

            String name = dir.substring(currLevel);      //substring except space

            if (name.contains(".")) {      //File
                while(!stack.isEmpty() && stack.peek().level >= currLevel){        //checks if another dir found at same level, then remove previous dir
                    stack.pop();
                }
                int absLength = (stack.isEmpty() ? 0 : stack.peek().currLength + 1) + name.length();
                maxLen = Math.max(maxLen, absLength);       //update maxLen only if its less
            } else {      //Dir
                while (!stack.isEmpty() && stack.peek().level >= currLevel) {   //checks if another dir found at same level, then remove previous dir
                    stack.pop();
                }

                Directory directory = new Directory();
                directory.fileName = name;
                directory.level = currLevel;
                directory.currLength = (currLevel == 0 ? 0 : stack.peek().currLength + 1) + name.length();

                stack.push(directory);
            }
        }
        return maxLen;
    }
}
