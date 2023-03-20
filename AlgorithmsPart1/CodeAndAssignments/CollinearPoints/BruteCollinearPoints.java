package CollinearPoints;

import java.util.ArrayList;

public class BruteCollinearPoints {

    private ArrayList <LineSegment> segments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        
        if (points == null) { throwingArgument(); }
        for(int i = 0; i < points.length; i++) {
            throwingArgument(points[i]);
            for(int j = 0; j < points.length; j++) {
                throwingArgument(points[j]);
                if(j == i) 
                    continue;
                if (points[j] == points[i])
                    throwingArgument();
                
                double slope = points[i].slopeTo(points[j]);
                
                for(int k = 0; k < points.length; k++) {
                    throwingArgument(points[k]);
                    if(k == j) 
                        continue;
                    if (points[k] == points[j])
                        throwingArgument();

                    if(points[j].slopeTo(points[k]) == slope){    
                        for(int l = 0; l < points.length; l++) {
                            throwingArgument(points[l]);
                            if(l == k) 
                                continue;
                            if (points[l] == points[k])
                                throwingArgument();

                            if(points[k].slopeTo(points[l]) == slope)
                                segments.add(new LineSegment(points[i], points[l]));
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
        return (LineSegment[]) segments.toArray();
    }// the line segments

    private void throwingArgument() {throw new IllegalArgumentException();}
    private void throwingArgument(Point p) {
        if(p == null) 
            throw new IllegalArgumentException();
    }
}