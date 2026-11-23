// Queue Lab. IntQueue.java holds the queue (Steps 1-3). This file holds
// Steps 4-6, which use the queue, and main, which checks everything.
//
//     javac *.java
//     java QueueLab
//
// main prints what your code gives (got) next to what it should give
// (expected). Work one step at a time: finish a step, run, and move on
// when that step says OK. Change only the methods marked TODO.

public class QueueLab {

    // Step 4: return a new array with the items of a, using an IntQueue:
    // enqueue every item of a, then dequeue them into the new array from
    // index 0 up. Before you run it, write your guess in the comment below:
    // will the new array be in the same order as a, or reversed?
    // My guess:
    static int[] throughQueue(int[] a) {
        int[] result = new int[a.length];
        IntQueue q = new IntQueue();
        for (int i = 0; i < a.length; i++) {
            q.enqueue(a[i]);
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = q.dequeue();
        }
       
        return result;
    }

    // Step 5: k times, take the front item off and put it at the back.
    // (The person at the front goes to the end of the line.)
    // Use only dequeue and enqueue.
    static void rotate(IntQueue q, int k) {
       if (q.isEmpty()) {
           return;
    }
        for (int i = 0; i < k; i++) {
            q.enqueue(q.dequeue());
        }
    }

    // Step 6 (challenge): hot potato. Players 1, 2, ..., n stand in a line
    // (a queue, 1 at the front). The front player holds the potato.
    // Repeat until one player is left:
    //   - pass the potato k times (each pass: front player goes to the back;
    //     rotate already does this)
    //   - the player now at the front is out (dequeue, and do not put back)
    // Return the last player left.
    static int hotPotato(int n, int k) {
       IntQueue q = line(n);
        while (q.size() > 1) {
            rotate(q, k); 
            q.dequeue();
        }
        return q.dequeue();
    }

    // ---------- checks: do not change below this line ----------

    static int ok = 0;
    static int total = 0;

    static void check(String what, Object got, Object expected) {
        total = total + 1;
        boolean same = String.valueOf(got).equals(String.valueOf(expected));
        if (same) {
            ok = ok + 1;
        }
        System.out.println(what);
        System.out.println("   got:      " + got);
        System.out.println("   expected: " + expected + (same ? "   OK" : "   <-- not yet"));
    }

    static String show(int[] a) {
        String s = "";
        for (int i = 0; i < a.length; i++) {
            s = s + a[i] + " ";
        }
        return "[" + s.trim() + "]";
    }

    static String dequeueOrError(IntQueue q) {
        try {
            return String.valueOf(q.dequeue());
        } catch (IllegalStateException e) {
            return "IllegalStateException: " + e.getMessage();
        } catch (NullPointerException e) {
            return "NullPointerException";
        }
    }

    static String peekOrError(IntQueue q) {
        try {
            return String.valueOf(q.peek());
        } catch (IllegalStateException e) {
            return "IllegalStateException: " + e.getMessage();
        }
    }

    static IntQueue line(int n) {
        IntQueue q = new IntQueue();
        for (int i = 1; i <= n; i++) {
            q.enqueue(i);
        }
        return q;
    }

    static String rotated(int n, int k) {
        IntQueue q = line(n);
        rotate(q, k);
        return q.toString();
    }

    public static void main(String[] args) {
        IntQueue q = new IntQueue();
        System.out.println("== In class: enqueue, peek, isEmpty");
        check("new queue isEmpty", q.isEmpty(), true);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        check("enqueue 10, 20, 30, then the queue, front first", q, "10 20 30");
        check("peek", peekOrError(q), 10);
        check("isEmpty", q.isEmpty(), false);

        System.out.println();
        System.out.println("== Step 1: dequeue");
        check("dequeue", dequeueOrError(q), 10);
        check("the queue after it", q, "20 30");
        check("dequeue", dequeueOrError(q), 20);
        check("peek", peekOrError(q), 30);

        System.out.println();
        System.out.println("== Step 2: the empty queue");
        check("dequeue the last one", dequeueOrError(q), 30);
        check("isEmpty", q.isEmpty(), true);
        check("dequeue on an empty queue", dequeueOrError(q), "IllegalStateException: queue is empty");
        q.enqueue(7);
        check("enqueue 7 after the queue was empty, then the queue", q, "7");

        System.out.println();
        System.out.println("== Step 3: size");
        IntQueue s = new IntQueue();
        check("size of a new queue", s.size(), 0);
        s.enqueue(4);
        s.enqueue(8);
        s.enqueue(15);
        check("size after enqueue 4, 8, 15", s.size(), 3);
        dequeueOrError(s);
        check("size after one dequeue", s.size(), 2);

        System.out.println();
        System.out.println("== Step 4: throughQueue");
        check("throughQueue {4, 8, 15}", show(throughQueue(new int[] {4, 8, 15})), "[4 8 15]");
        check("throughQueue {9}", show(throughQueue(new int[] {9})), "[9]");
        check("throughQueue {} (empty array)", show(throughQueue(new int[] {})), "[]");

        System.out.println();
        System.out.println("== Step 5: rotate");
        check("1 2 3 4 5, rotate 2", rotated(5, 2), "3 4 5 1 2");
        check("1 2 3 4 5, rotate 7", rotated(5, 7), "3 4 5 1 2");
        check("1 2 3 4 5, rotate 0", rotated(5, 0), "1 2 3 4 5");

        System.out.println();
        System.out.println("== Step 6 (challenge): hotPotato");
        check("hotPotato(5, 2)", hotPotato(5, 2), 4);
        check("hotPotato(6, 0)", hotPotato(6, 0), 6);
        check("hotPotato(1, 3)", hotPotato(1, 3), 1);

        System.out.println();
        System.out.println(ok + " of " + total + " checks OK");
    }
}
