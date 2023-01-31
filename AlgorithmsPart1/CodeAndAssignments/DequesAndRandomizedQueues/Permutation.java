import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> q = new RandomizedQueue<String>();
        for(int i = 0; i < k; i++){
            q.enqueue(StdIn.readString());
        }

        for(String s : q){
            StdOut.println(s);
        }

    }
}
