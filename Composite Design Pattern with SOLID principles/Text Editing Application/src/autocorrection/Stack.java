/*
 * Matt Levan
 * CSC 331, Dr. Amlan Chatterjee
 * Data Structures
 *
 * Generic Stack implementation.
 * 
 * Must include four methods:
 * push, pop, peek, isEmpty
 *
 */
package autocorrection;

// Stack implemented using a primitive array

public class Stack<E> {
    // Variables

    private int count = 0; // Count of elements 
    private int top = -1; // Index of the top element 
    private int capacity; // Maximum capacity 
    private E[] stackArray;

    // Constructor

    @SuppressWarnings("unchecked")
	public Stack(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
            stackArray = (E[]) new Object[capacity]; 
        }
        else {
            this.capacity = capacity;
            stackArray = (E[]) new Object[100];
        }
    }
    

    // Methods

    public boolean push(E obj) {
        if (top == capacity - 1) { // Check if top is larger than capacity
            System.out.println("Push of " + obj + " failed! Stack overflow.");
            return false; // Push failed
        }
        else {
            top++;
            count++;
            stackArray[top] = obj; // obj assigned to top of stack
            return true; // Push succeeded
        }
    }

    public E pop() {
        E returnValue = null;

        if (!isEmpty()) { // If stack is NOT empty
            returnValue = stackArray[top];
            stackArray[top] = null; // Remove element at top of stack
            top--;
            count--;
        }
        else {
            System.out.println("Pop failed! Stack underflow.");
        }

        return returnValue;
    }

    public E peek() {
        if (isEmpty()) {
            System.out.println("Peek failed! Stack is empty.");
            return null;
        }

        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }
}