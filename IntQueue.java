// IntQueue: a queue of ints. First in, first out, like the line at a counter.
//
// Same Node boxes as the stack, but we keep track of both ends of the line:
//
//     front                                back
//       |                                    |
//       v                                    v
//     [ 10 | next ]---->[ 20 | next ]---->[ 30 | null ]
//
// enqueue puts a new item at the back (it waits its turn).
// dequeue takes the item at the front off and returns it (it is served).
//
// enqueue, peek, and isEmpty are done in class together.
// You write dequeue and size (Steps 1-3). Then run:
//
//     javac *.java
//     java QueueLab

public class IntQueue {
    private Node front;   // the next one to be served; null means the queue is empty
    private Node back;    // the last one that arrived

    // Done in class: the new item goes at the back of the line.
    // Because we keep back, there is no walk to the end (11-09 addLast walked).
    public void enqueue(int item) {
        Node newNode = new Node(item, null);
        if (isEmpty()) {
            front = newNode;
        } else {
            back.next = newNode;
        }
        back = newNode;
    }

    // Done in class: true when nobody is in line.
    public boolean isEmpty() {
        return front == null;
    }

    // Done in class: return the front item, but leave it in line.
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }
        return front.item;
    }

    // Step 1: take the front item off and return it.
    // The item behind it becomes the new front.
    // Step 2: an empty queue has nobody to serve:
    //     throw new IllegalStateException("queue is empty");
    public int dequeue() {
        if(isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }
        int item = front.item;
        front = front.next;
        if (front == null) { 
            back = null;
        }
        return item;
    }

    // Step 3: return how many items are in line.
    // Walk from front with current, like size in IntList. Empty: 0.
    public int size() {
        int count = 0;
        Node current = front; 
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Done for you: the items from front to back, like "10 20 30".
    public String toString() {
        String s = "";
        Node current = front;
        while (current != null) {
            s = s + current.item + " ";
            current = current.next;
        }
        return s.trim();
    }
}
