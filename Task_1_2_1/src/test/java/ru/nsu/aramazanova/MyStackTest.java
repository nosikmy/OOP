package ru.nsu.aramazanova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * testing stack realization.
 */
public class MyStackTest {

    @Test
    public void exampleTest() {
        MyStack ans = new MyStack();
        ans.count = 1;
        ans.arr = new Object[]{2};
        MyStack stack = new MyStack();
        stack.push(2);
        Assertions.assertTrue(stack.check(ans));

        ans.count = 2;
        ans.arr = new Object[]{2, 7};
        stack.push(7);
        Assertions.assertTrue(stack.check(ans));

        ans.count = 4;
        ans.arr = new Object[]{2, 7, 4, 8};
        MyStack input = new MyStack();
        input.push(4);
        input.push(8);
        stack.pushStack(input);
        Assertions.assertTrue(stack.check(ans));

        ans.count = 3;
        ans.arr = new Object[]{2, 7, 4};
        stack.pop();
        Assertions.assertTrue(stack.check(ans));

        ans.count = 1;
        ans.arr = new Object[]{2};
        stack.popStack(2);
        Assertions.assertTrue(stack.check(ans));
    }

    @Test
    public void myTest(){
        MyStack ans = new MyStack();
        MyStack stack = new MyStack();

        //pop from empty stack.
        ans.count = 0;
        ans.arr = new Object[]{};
        stack.pop();
        Assertions.assertTrue(stack.check(ans));

        //the count of elements in the deleting stack is greater than in main stack
        ans.count = 0;
        ans.arr = new Object[]{};
        MyStack input1 = new MyStack();
        input1.push(4);
        input1.push(8);
        stack.pushStack(input1);
        stack.popStack(3);
        Assertions.assertTrue(stack.check(ans));

        //push empty stack
        ans.count = 0;
        ans.arr = new Object[]{};
        MyStack input2 = new MyStack();
        stack.pushStack(input2);
        stack.popStack(3);
        Assertions.assertTrue(stack.check(ans));
    }
}