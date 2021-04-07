package leetcode;

public class DesignCircularQueue {
    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        System.out.println(myCircularQueue.enQueue(1));
        System.out.println(myCircularQueue.enQueue(2));
        System.out.println(myCircularQueue.enQueue(3));
        System.out.println(myCircularQueue.enQueue(4));

        System.out.println(myCircularQueue.Rear());
        System.out.println(myCircularQueue.isFull());
        System.out.println(myCircularQueue.deQueue());
        System.out.println(myCircularQueue.enQueue(4));
        System.out.println(myCircularQueue.Rear());

    }

    static class MyCircularQueue {

        Integer [] arr;
        int n;
        int count = 0;
        int front = 0;
        int rear = 0;

        public MyCircularQueue(int k) {
            this.arr = new Integer[k];
            this.n = k;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }

            count++;
            this.arr[rear++] = value;
            this.rear = this.rear % n;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }

            this.count--;
            this.arr[front++] = null;
            this.front = this.front % n;

            return true;
        }

        public int Front() {
            return count == 0 ? -1 : arr[this.front % n];
        }

        public int Rear() {
            int idx = (this.rear % n) - 1;

            if (idx == -1) {
                idx = n - 1;
            }

            return count == 0 ? -1 : arr[idx];
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == n;
        }
    }
}
