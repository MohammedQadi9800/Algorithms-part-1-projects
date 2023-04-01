import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private ArrayList <LineSegment> segments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        throwingArgument(points);
        Point[] copy = Arrays.copyOf(points, points.length);
        checkValidity(copy);
        for(int i = 0; i < copy.length - 3; i++) {
            for(int j = i + 1; j < copy.length - 2; j++) {
                for(int k = j + 1; k < copy.length - 1; k++) {
                    for(int l = k + 1; l < copy.length; l++) {
                        if(copy[i].slopeTo(copy[j]) == copy[i].slopeTo(copy[k]) && copy[i].slopeTo(copy[k]) == copy[i].slopeTo(copy[l])) 
                            segments.add(new LineSegment(copy[i], copy[l]));
                        
                    }
                }
            }
        }

    }    // finds all line segments containing 4 points
    // the number of line segments
    public int numberOfSegments() {
        return segments().length;
    }        
    public LineSegment[] segments() {

        Object [] arr1 = segments.toArray();
        LineSegment [] arr2 = new LineSegment[arr1.length];
        int i = 0;
        for(Object o : arr1) {
            arr2[i++] = (LineSegment) o;
        }
        return arr2;
    }// the line segments

    private void throwingArgument() {throw new IllegalArgumentException("Duplicates exist");}
    private <T> void throwingArgument(T p) {
        if(p == null) 
            throw new IllegalArgumentException("something is null");
    }
    private void checkValidity(Point array[]) {
        //checking if there are any null elements
        for(int i = 0; i < array.length; i++) {
            throwingArgument(array[i]);
        }
        //checking for duplicates
        Arrays.sort(array);
        for(int i = 0; i < array.length -1; i++) {
            throwingArgument(array[i]);
            if (array[i].compareTo(array[i + 1]) == 0)
                throwingArgument();
        }
    }
}
