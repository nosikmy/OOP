package ru.nsu.aramazanova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * testing stack realization.
 */
public class MyStackTest {

    @Test
    public void exampleTests() {
        MyStack<Integer> expectedStack = new MyStack<Integer>();
        expectedStack.setCount(1);
        Integer[] inputArray = new Integer[]{2};
        expectedStack.setArray(inputArray);
        MyStack<Integer> actualStack = new MyStack<Integer>();
        actualStack.push(2);
        Assertions.assertEquals(actualStack, expectedStack);

        expectedStack.setCount(2);
        inputArray = new Integer[]{2, 7};
        expectedStack.setArray(inputArray);
        actualStack.push(7);
        Assertions.assertEquals(actualStack, expectedStack);

        expectedStack.setCount(4);
        inputArray = new Integer[]{2, 7, 4, 8};
        expectedStack.setArray(inputArray);
        MyStack<Integer> inputStack = new MyStack<Integer>();
        inputStack.push(4);
        inputStack.push(8);
        actualStack.pushStack(inputStack);
        Assertions.assertEquals(actualStack, expectedStack);

        expectedStack.setCount(3);
        inputArray = new Integer[]{2, 7, 4};
        expectedStack.setArray(inputArray);
        Object popped = actualStack.pop();
        Assertions.assertEquals(popped, 8);
        Assertions.assertEquals(actualStack, expectedStack);

        expectedStack.setCount(2);
        inputArray = new Integer[]{4, 7};
        expectedStack.setArray(inputArray);
        MyStack<Integer> poppedStack = actualStack.popStack(2);
        Assertions.assertEquals(poppedStack, expectedStack);
        expectedStack.setCount(1);
        inputArray = new Integer[]{2};
        expectedStack.setArray(inputArray);
        Assertions.assertEquals(actualStack, expectedStack);
    }

    @Test
    public void nullStackTests() {
        MyStack<Integer> expectedStack = new MyStack<Integer>();
        MyStack<Integer> actualStack = new MyStack<Integer>();

        //pop from empty actualStack.
        expectedStack.setCount(0);
        Integer[] inputArray = new Integer[]{};
        expectedStack.setArray(inputArray);
        Integer popped = actualStack.pop();
        Assertions.assertNull(popped);
        Assertions.assertEquals(actualStack, expectedStack);

        //the count of elements in the deleting actualStack is greater than in main actualStack
        expectedStack.setCount(2);
        inputArray = new Integer[]{8, 4};
        expectedStack.setArray(inputArray);
        MyStack<Integer> input1 = new MyStack<Integer>();
        input1.push(4);
        input1.push(8);
        actualStack.pushStack(input1);
        MyStack<Integer> poppedStack = actualStack.popStack(3);
        Assertions.assertEquals(poppedStack, expectedStack);
        expectedStack.setCount(0);
        inputArray = new Integer[]{};
        expectedStack.setArray(inputArray);
        Assertions.assertEquals(actualStack, expectedStack);

        //push empty actualStack
        expectedStack.setCount(0);
        inputArray = new Integer[]{};
        expectedStack.setArray(inputArray);
        MyStack<Integer> input2 = new MyStack<Integer>();
        actualStack.pushStack(input2);
        Assertions.assertEquals(actualStack, expectedStack);
        actualStack.popStack(3);
        Assertions.assertEquals(actualStack, expectedStack);
    }

    @Test
    public void stringTypeTest() {
        MyStack<String> expectedStack = new MyStack<String>();
        expectedStack.setCount(2);
        String[] inputArray = new String[]{"two", "cats"};
        expectedStack.setArray(inputArray);
        MyStack<String> actualStack = new MyStack<String>();
        actualStack.push("two");
        actualStack.push("cats");
        Assertions.assertEquals(actualStack, expectedStack);

        expectedStack.setCount(3);
        inputArray = new String[]{"two", "cats", "sleep"};
        expectedStack.setArray(inputArray);
        actualStack.push("sleep");
        Assertions.assertEquals(actualStack, expectedStack);

        expectedStack.setCount(5);
        inputArray = new String[]{"two", "cats", "sleep", "in garden", "together"};
        expectedStack.setArray(inputArray);
        MyStack<String> input = new MyStack<String>();
        input.push("in garden");
        input.push("together");
        actualStack.pushStack(input);
        Assertions.assertEquals(actualStack, expectedStack);

        expectedStack.setCount(4);
        inputArray = new String[]{"two", "cats", "sleep", "in garden"};
        expectedStack.setArray(inputArray);
        String popped = actualStack.pop();
        Assertions.assertEquals(popped, "together");
        Assertions.assertEquals(actualStack, expectedStack);

        expectedStack.setCount(2);
        inputArray = new String[]{"in garden", "together"};
        expectedStack.setArray(inputArray);
        MyStack<String> poppedStack = actualStack.popStack(2);
        Assertions.assertEquals(poppedStack, expectedStack);
        expectedStack.setCount(2);
        inputArray = new String[]{"two", "cats"};
        expectedStack.setArray(inputArray);
        Assertions.assertEquals(actualStack, expectedStack);
    }

    @Test
    public void pushTest(){
        MyStack<Double> expectedStack = new MyStack<Double>();
        MyStack<Double> actualStack = new MyStack<Double>();
        expectedStack.setCount(2);
        Double[] inputArray = new Double[]{0.25d};
        expectedStack.setArray(inputArray);
        actualStack.push(0.25d);
        Assertions.assertEquals(actualStack, expectedStack);
    }

    @Test
    public void pushStackTest(){
        MyStack<Character> expectedStack = new MyStack<Character>();
        MyStack<Character> actualStack = new MyStack<Character>();
        expectedStack.setCount(4);
        Character[] inputArray = new Character[]{'a', 'Z'};
        expectedStack.setArray(inputArray);
        MyStack<Character> inputStack = new MyStack<Character>();
        inputStack.push('a');
        inputStack.push('Z');
        actualStack.pushStack(inputStack);
        Assertions.assertEquals(actualStack, expectedStack);
    }

    @Test
    public void popTest(){
        MyStack<Integer> expectedStack = new MyStack<Integer>();
        MyStack<Integer> actualStack = new MyStack<Integer>();
        expectedStack.setCount(3);
        Integer[] inputArray = new Integer[]{252, 54, 553};
        expectedStack.setArray(inputArray);
        actualStack.push(252);
        actualStack.push(54);
        actualStack.push(553);
        actualStack.push(8);
        Object popped = actualStack.pop();
        Assertions.assertEquals(popped, 8);
        Assertions.assertEquals(actualStack, expectedStack);
    }

    @Test
    public void popStackTest(){
        MyStack<Integer> expectedStack = new MyStack<Integer>();
        MyStack<Integer> actualStack = new MyStack<Integer>();
        expectedStack.setCount(3);
        Integer[] inputArray = new Integer[]{8, 553};
        expectedStack.setArray(inputArray);
        actualStack.push(252);
        actualStack.push(54);
        actualStack.push(553);
        actualStack.push(8);
        MyStack<Integer> poppedStack = actualStack.popStack(2);
        Assertions.assertEquals(poppedStack, expectedStack);
        expectedStack.setCount(1);
        inputArray = new Integer[]{252, 54};
        expectedStack.setArray(inputArray);
        Assertions.assertEquals(actualStack, expectedStack);
    }
}