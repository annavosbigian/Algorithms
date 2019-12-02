/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] segmentArray;
    private int count;
    private Point[] pointsCopy;

    public FastCollinearPoints(Point[] points) {
        if (points.length == 0) {
            throw new IllegalArgumentException("must include points");
        }
        pointsCopy = Arrays.copyOf(points, points.length);
        segmentArray = new LineSegment[pointsCopy.length];
        count = 0;
        Point pointMin = pointsCopy[0];
        int minLocation = 0;
        for (int i = 1; i < pointsCopy.length - 1; i++) {
            if (pointMin.compareTo(pointsCopy[i]) == -1) {
                pointMin = pointsCopy[i];
                minLocation = i;
            }
        }
        Point swap = pointsCopy[0];
        pointsCopy[0] = pointsCopy[minLocation];
        pointsCopy[minLocation] = swap;
        Arrays.sort(pointsCopy, pointMin.slopeOrder());
        System.out.println(Arrays.toString(points));
        System.out.println(Arrays.toString(pointsCopy));
        int j = 0;
        while (j < pointsCopy.length - 4) {
            double slope1 = pointsCopy[j].slopeTo(pointsCopy[j + 1]);
            System.out.println(slope1);
            if (pointsCopy[j].slopeTo(pointsCopy[j + 1]) == pointsCopy[j]
                    .slopeTo(pointsCopy[j + 3])) {
                int k = 4;
                if (k < pointsCopy.length && pointsCopy[j]
                        .slopeTo(pointsCopy[j + 1]) == pointsCopy[j].slopeTo(pointsCopy[j + k])) {
                    k++;
                }
                else {
                    k--;
                }
                segmentArray[count++] = new LineSegment(pointsCopy[j], pointsCopy[k]);
                System.out.println("j is" + j);
                System.out.println("k is" + k);
                j = k;
                System.out.println("j is" + j);
            }
            else {
                j++;
            }
        }
        System.out.println("Complete!!!");
    }

    public int numberOfSegments() {
        // the number of line segments
        return count;
    }

    public LineSegment[] segments() {
        // finds all line segments containing 4 or more points
        return segmentArray;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            System.out.println(x);
            int y = in.readInt();
            System.out.println(y);
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
