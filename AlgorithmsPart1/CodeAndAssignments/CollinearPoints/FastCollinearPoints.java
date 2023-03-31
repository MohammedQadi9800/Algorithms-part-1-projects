package CollinearPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class FastCollinearPoints {

    private ArrayList <LineSegment> segments = new ArrayList<LineSegment>();
    private HashSet <Point> pointSet = new HashSet<>();

    public FastCollinearPoints(Point[] points) {
        throwingArgument(points);
        for(int i = 0; i < points.length; i++) {
            throwingArgument(points[i]);
            if (!pointSet.contains(points[i]))
                pointSet.add(points[i]);
            else throwingArgument();
        }
        
        Point[] copy = Arrays.copyOf(points, points.length);
        for(int i = 0; i < points.length; i++) {
            Arrays.sort(copy, points[i].slopeOrder());
            Point begin = points[i];
            Point end = null;
            int count = 0;
            for(int j = 0; i < copy.length - 1; j++) {
                if(begin.slopeTo(copy[j]) == begin.slopeTo(copy[j++])) 
                    count++;
            }
            if (count >= 3)
                segments.add(new LineSegment(begin, end));
        }
    }    
    public  int numberOfSegments() {return segments().length; } 
    public LineSegment[] segments() {
        return (LineSegment[]) segments.toArray();
    }
    private void throwingArgument() {throw new IllegalArgumentException("Duplicates exist");}
    private <T> void throwingArgument(T p) {
        if(p == null) 
            throw new IllegalArgumentException("something is null");
    }
}
