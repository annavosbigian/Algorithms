/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class BruteCollinearPoints {
    private int count;
    private LineSegment[] lines;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points.length == 0) {
            throw new IllegalArgumentException("must include points");
        }
        count = 0;
        lines = new LineSegment[points.length];
        for (int i = 1; i < points.length - 2; i++) {
            double slope1 = points[0].slopeTo(points[i]);
            double slope2 = points[0].slopeTo(points[i + 1]);
            if (slope1 == slope2) {
                double slope3 = points[0].slopeTo(points[i + 2]);
                if (slope1 == slope3) {
                    lines[count++] = new LineSegment(points[0], points[i + 2]);
                }
            }
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return count;
    }

    public LineSegment[] segments() {
        // the line segments
        return lines;
    }

    public static void main(String[] args) {
        // read the n points from a file
        Point[] pointArray = new Point[5];
        pointArray[0] = new Point(1, 3);
        pointArray[1] = new Point(2, 4);
        pointArray[2] = new Point(3, 5);
        pointArray[3] = new Point(4, 6);
        pointArray[4] = new Point(1, 2);
        BruteCollinearPoints collinear = new BruteCollinearPoints(pointArray);
        System.out.println(collinear.segments());
        System.out.println(collinear.numberOfSegments());


    }
}
