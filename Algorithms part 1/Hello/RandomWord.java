import edu.princeton.cs.algs4.*;
public class RandomWord{
    public static void main(String[] args) {
        String winner = "0";
        float counter = 0f;
        while (!StdIn.isEmpty()) {
            if (winner.compareTo("0") == 0) {
                winner = StdIn.readString();
                counter++;
            }
            String loser = StdIn.readString();
            counter++;
            float probablity = 1/counter;
            if (Math.random() <= probablity)
                winner = loser;
        }
        StdOut.println(winner); 
    }
}