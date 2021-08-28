/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        //  StdOut.println("The value of k: " + k);
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            rq.enqueue(str);
        }
        //  Check that maximum size of any object created is equal to k

        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }

    }
}
