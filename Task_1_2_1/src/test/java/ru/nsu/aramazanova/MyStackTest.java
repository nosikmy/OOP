package ru.nsu.aramazanova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * testing stack realization.
 */
public class MyStackTest {

    @Test
    public void exampleTests() {
        MyStack<Integer> ans = new MyStack<Integer>();
        ans.count = 1;
        ans.arr = new Integer[]{2};
        MyStack<Integer> stack = new MyStack<Integer>();
        stack.push(2);
        Assertions.assertEquals(stack, ans);

        ans.count = 2;
        ans.arr = new Integer[]{2, 7};
        stack.push(7);
        Assertions.assertEquals(stack, ans);

        ans.count = 4;
        ans.arr = new Integer[]{2, 7, 4, 8};
        MyStack<Integer> input = new MyStack<Integer>();
        input.push(4);
        input.push(8);
        stack.pushStack(input);
        Assertions.assertEquals(stack, ans);

        ans.count = 3;
        ans.arr = new Integer[]{2, 7, 4};
        Object p = stack.pop();
        Assertions.assertEquals(p, 8);
        Assertions.assertEquals(stack, ans);

        ans.count = 2;
        ans.arr = new Integer[]{4, 7};
        MyStack<Integer> popped = stack.popStack(2);
        Assertions.assertEquals(popped, ans);
        ans.count = 1;
        ans.arr = new Integer[]{2};
        Assertions.assertEquals(stack, ans);
    }

    @Test
    public void nullStackTests() {
        MyStack<Integer> ans = new MyStack<Integer>();
        MyStack<Integer> stack = new MyStack<Integer>();

        //pop from empty stack.
        ans.count = 0;
        ans.arr = new Integer[]{};
        Object p = stack.pop();
        Assertions.assertNull(p);
        Assertions.assertEquals(stack, ans);

        //the count of elements in the deleting stack is greater than in main stack
        ans.count = 2;
        ans.arr = new Integer[]{8, 4};
        MyStack<Integer> input1 = new MyStack<Integer>();
        input1.push(4);
        input1.push(8);
        stack.pushStack(input1);
        MyStack<Integer> popped = stack.popStack(3);
        Assertions.assertEquals(popped, ans);
        ans.count = 0;
        ans.arr = new Integer[]{};
        Assertions.assertEquals(stack, ans);

        //push empty stack
        ans.count = 0;
        ans.arr = new Integer[]{};
        MyStack<Integer> input2 = new MyStack<Integer>();
        stack.pushStack(input2);
        Assertions.assertEquals(stack, ans);
        stack.popStack(3);
        Assertions.assertEquals(stack, ans);
    }

    @Test
    public void stringTypeTest(){
        MyStack<String> ans = new MyStack<String>();
        ans.count = 2;
        ans.arr = new String[]{"two", "cats"};
        MyStack<String> stack = new MyStack<String>();
        stack.push("two");
        stack.push("cats");
        Assertions.assertEquals(stack, ans);

        ans.count = 3;
        ans.arr = new String[]{"two", "cats", "sleep"};
        stack.push("sleep");
        Assertions.assertEquals(stack, ans);

        ans.count = 5;
        ans.arr = new String[]{"two", "cats", "sleep", "in garden", "together"};
        MyStack<String> input = new MyStack<String>();
        input.push("in garden");
        input.push("together");
        stack.pushStack(input);
        Assertions.assertEquals(stack, ans);

        ans.count = 4;
        ans.arr = new String[]{"two", "cats", "sleep", "in garden"};
        Object p = stack.pop();
        Assertions.assertEquals(p, "together");
        Assertions.assertEquals(stack, ans);

        ans.count = 2;
        ans.arr = new String[]{"in garden", "sleep"};
        MyStack<String> popped = stack.popStack(2);
        Assertions.assertEquals(popped, ans);
        ans.count = 2;
        ans.arr = new String[]{"two", "cats"};
        Assertions.assertEquals(stack, ans);
    }
}