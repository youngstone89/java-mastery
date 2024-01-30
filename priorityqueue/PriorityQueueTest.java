package priorityqueue;

import java.util.PriorityQueue;

class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        pq.add(2);
        pq.add(1);
        pq.add(0);

        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());

    }
}