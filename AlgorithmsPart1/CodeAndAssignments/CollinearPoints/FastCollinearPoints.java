import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList <LineSegment> segments = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {
        throwingArgument(points);
        Point[] copy = Arrays.copyOf(points, points.length);
        checkValidity(copy);
        for(int i = 0; i < points.length; i++) {
            Arrays.sort(copy, points[i].slopeOrder());
            Point begin = points[i];
            Point end = null;
            int count = 0;
            for(int j = 0; j < copy.length - 1; j++) {
                if(copy[j].compareTo(begin) == 0)
                    continue;
                if(begin.slopeTo(copy[j]) == begin.slopeTo(copy[j + 1])) {
                    count++;
                    end = copy[j + 1];
                }
                else
                    count = 0;
                //checks if the line has 4 or 5 points
                if (count == 3){
                    if(j + 2 < copy.length && begin.slopeTo(copy[j]) == begin.slopeTo(copy[j + 2]))
                        end = copy[j + 2];
                    segments.add(new LineSegment(begin, end));
                }
            }
            
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
