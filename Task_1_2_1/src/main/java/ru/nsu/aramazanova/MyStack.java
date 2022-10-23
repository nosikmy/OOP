package ru.nsu.aramazanova;

import java.util.Arrays;
import java.util.Objects;

/**
 * My stack realization.
 */
public class MyStack<T> {

    private T[] array;
    private int count;
    private int size;

    /**
     * Initial stack.
     */
    public MyStack() {
        this.size = 1;
        this.array = (T[]) new Object[]{0};
        this.count = 0;
    }

    /**
     * returns array.
     *
     * @param array array
     */
    public void setArray(T[] array) {
        this.array = array;
    }

    public void setCount(int count) {
        this.count = count;
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
    public void push(T newElem) {
        if (count == size) {
            size *= 2;
            array = Arrays.copyOf(array, size);
        }
        array[count++] = newElem;
    }

    /**
     * add other stack in existing stack.
     *
     * @param newStack stack with new elements for existing stack
     */
    public void pushStack(MyStack<T> newStack) {
        int l = newStack.count();
        for (int i = 0; i < l; i++) {
            push(newStack.array[i]);
        }
    }

    /**
     * delete last element and return it.
     *
     * @return deleted element
     */
    public T pop() {
        if (count == 0) {
            return null;
        }
        count--;
        return array[count];
    }

    /**
     * delete count of elements and return stack of them.
     *
     * @param length count elements for deleting
     * @return new stack with deleted elements
     */
    public MyStack<T> popStack(int length) {
        MyStack<T> poppedStack = new MyStack<T>();
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
        MyStack<?> test = (MyStack<?>) o;
        if (count != test.count()) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (array[i] != test.array[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * return hashcode.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(count);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}