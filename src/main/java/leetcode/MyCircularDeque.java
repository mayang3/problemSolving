package leetcode;

public class MyCircularDeque {
    int [] arr;
    int n;
    int front = 0;
    int rear = 0;
    int count = 0;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.arr = new int[k];
        this.n = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }

        count++;

        if (front == 0 && rear == 0) {
            rear = (rear+1) % n;
        }

        arr[front] = value;
        front = (n+front-1) % n;

        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }

        count++;

        if (front == 0 && rear == 0) {
            front = (n+front-1) % n;
        }

        arr[rear] = value;
        rear = (rear+1) % n;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }

        count--;
        front = (front + 1) % n;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        count--;
        rear = (n + rear - 1) % n;

        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }

        return arr[(front + 1) % n];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }

        int idx = rear - 1;

        if (idx == -1) {
            idx = n - 1;
        }

        return arr[idx];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return count == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return count == n;
    }
}
