
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class FastCollinearPoints {
    private ArrayList <LineSegment> segments = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {
        checkValidity(points);
        
        Point[] copy = Arrays.copyOf(points, points.length);
        for(int i = 0; i < points.length; i++) {
            Arrays.sort(copy, points[i].slopeOrder());
            Point begin = points[i];
            Point end = null;
            int count = 0;
            for(int j = 0; i < copy.length - 1; j++) {
                if(begin.slopeTo(copy[j]) == begin.slopeTo(copy[j++])) {
                    count++;
                    end = copy[j++];
                }
            }
            if (count >= 3)
                segments.add(new LineSegment(begin, end));
        }
    }    
    public  int numberOfSegments() {return segments().length; } 
    public LineSegment[] segments() {

        Object [] arr1 = segments.toArray();
        LineSegment [] arr2 = new LineSegment[arr1.length];
        int i = 0;
        for(Object o : arr1) {
            arr2[i++] = (LineSegment) o;
        }
        return arr2;
    }
    private void throwingArgument() {throw new IllegalArgumentException("Duplicates exist");}
    private <T> void throwingArgument(T p) {
        if(p == null) 
            throw new IllegalArgumentException("something is null");
    }
    private void checkValidity(Point array[]) {
        throwingArgument(array);
        for(int i = 0; i < array.length -1; i++) {
            throwingArgument(array[i]);
            if (array[i].compareTo(array[i + 1]) == 0)
                throwingArgument();
        }
    }

}
