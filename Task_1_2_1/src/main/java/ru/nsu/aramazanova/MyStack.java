package ru.nsu.aramazanova;

import java.util.Arrays;
import java.util.Objects;

/**
 * My stack realization.
 */
public class MyStack<S> {
    public S[] arr;
    public int count;
    public int size;

    /**
     * initial stack.
     */
    public MyStack() {
        size = 1;
        arr = (S[]) new Object[size];
        count = 0;
    }

    /**
     * show count elements in the stack.
     *
     * @return count elements
     */
    public int count() {
        return count;
    }

    /**
     * add new element in stack.
     *
     * @param newElem new element for stack
     */
    public void push(S newElem) {
        if (count == size) {
            size *= 2;
            arr = Arrays.copyOf(arr, size);
        }
        arr[count++] = newElem;
    }

    /**
     * add other stack in existing stack.
     *
     * @param newStack stack with new elements for existing stack
     */
    public void pushStack(MyStack<S> newStack) {
        int l = newStack.count();
        for (int i = 0; i < l; i++) {
            push(newStack.arr[i]);
        }
    }

    /**
     * delete last element and return it.
     *
     * @return deleted element
     */
    public S pop() {
        if (count == 0) {
            return null;
        }
        count--;
        S popped = arr[count];
        arr[count] = null;
        return popped;
    }

    /**
     * delete count of elements and return stack of them.
     *
     * @param length count elements for deleting
     * @return new stack with deleted elements
     */
    public MyStack<S> popStack(int length) {
        if (length < 0) {
            return null;
        }
        MyStack<S> poppedStack = new MyStack<S>();
        int poppedSize = Math.min(length, count);
        for (int i = 0; i < poppedSize; i++) {
            poppedStack.push(pop());
        }
        return poppedStack;
    }

    /**
     * stack comparison function.
     *
     * @param o expected stack
     * @return true if stacks are equal, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyStack<S> test = (MyStack<S>) o;
        if (count != test.count()) {
            return false;
        }
        boolean ans = true;
        for (int i = 0; i < count; i++) {
            ans = ans & (arr[i] == test.arr[i]);
        }
        return ans;
    }

    /**
     * returns hashcode.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return 31 * (Objects.hash(count) + Arrays.hashCode(arr));
    }
}