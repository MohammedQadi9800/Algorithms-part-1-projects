import java.util.ArrayList;

public class BruteCollinearPoints {

    private ArrayList <LineSegment> segments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        
        checkValidity(points);
        for(int i = 0; i < points.length; i++) {
            throwingArgument(points[i]);
            for(int j = 0; j < points.length; j++) {
                if(j == i) 
                    continue;
                
                double slope = points[i].slopeTo(points[j]);
                
                for(int k = 0; k < points.length; k++) {
                    if( k == i|| k == j ) 
                        continue;

                    if(points[j].slopeTo(points[k]) == slope){    
                        for(int l = 0; l < points.length; l++) {
                            if(l == i || l ==j || l == k) 
                                continue;

                            if(points[k].slopeTo(points[l]) == slope) {
                                LineSegment line = new LineSegment(points[i], points[l]);
                                if(!segments.contains(line))
                                    segments.add(line);
                            }
                        }
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
        throwingArgument(array);
        for(int i = 0; i < array.length -1; i++) {
            throwingArgument(array[i]);
            if (array[i].compareTo(array[i + 1]) == 0)
                throwingArgument();
        }
    }
}
