package ru.nsu.aramazanova;

/**
 * My stack realization.
 */
public class MyStack {
    public Object[] arr;
    public int count;
    public int size;

    /**
     * initial stack.
     */
    public MyStack() {
        size = 1000;
        arr = new Object[size];
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
    public void push(Object newElem) {
        arr[count++] = newElem;;
    }

    /**
     * add other stack in existing stack.
     *
     * @param newStack stack with new elements for existing stack
     */
    public void pushStack(MyStack newStack) {
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
    public Object pop() {
        if (count == 0) {
            return null;
        }
        count--;
        return arr[count];
    }

    /**
     * delete count of elements and return stack of them.
     *
     * @param length count elements for deleting
     * @return new stack with deleted elements
     */
    public MyStack popStack(int length) {
        if(length < 0){
            return null;
        }
        MyStack poppedStack = new MyStack();
        int poppedSize = Math.min(length, count);
        for (int i = 0; i < poppedSize; i++) {
            poppedStack.push(pop());
        }
        return poppedStack;
    }

    /**
     * stack comparison function.
     *
     * @param test expected stack
     * @return true if stacks are equal, else false
     */
    public boolean check(MyStack test) {
        if (count != test.count) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (arr[i] != test.arr[i]) {
                return false;
            }
        }
        return true;
    }
}