package leetcode.medium;

import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/
 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.

 Example:
 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
    1. Keep 2 stacks: One will hold all numbers and one for minimum numbers
    2. If a new element is larger than the current minimum, we do not need to push it on to the min stack.
    3. When we perform the pop operation, check if the popped element is the same as the current minimum.
       If it is, pop it off the min stack too.
 */
class MinStack {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()) {        //push to minStack, only if its empty or x is smaller than current min
            minStack.push(x);
        }
    }

    public void pop() {
        int x = stack.pop();
        if(x == minStack.peek()){                               //pop from minStack only if pop item is same as current min
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

public class MinStackDemo{
    public static void main(String[] args) {
        MinStack obj = new MinStack();
         obj.push(-2);
         obj.push(0);
         obj.push(-3);

         int min = obj.getMin();
         System.out.println(min);

         obj.pop();
         int top = obj.top();
         System.out.println(top);

         min = obj.getMin();
         System.out.println(min);
    }
}
