import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import java.util.concurrent.TimeUnit;

import gpdraw.*; // for DrawingTool

public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    // constructor
    public IrregularPolygon() {
    }

    // public methods
    public void add(Point2D.Double aPoint) {
        myPolygon.add(aPoint);
    }

    public double perimeter() {
        double perimeter = 0.0;
        int n = myPolygon.size();
        if(myPolygon.size() < 2){
            return 0.0;
        }
        for(int i = 0; i < n - 1; i++){
            perimeter += myPolygon.get(i).distance(myPolygon.get(i + 1));
        }
        perimeter += myPolygon.get(n - 1).distance(myPolygon.get(0));
        return perimeter;
    }

    public double area() {
        if (myPolygon.size() < 3) return 0.0; // A polygon needs at least 3 points
    
        double sum1 = 0.0, sum2 = 0.0;
        int n = myPolygon.size();
    
        for (int j = 0; j < n - 1; j++) {
            Point2D.Double p1 = myPolygon.get(j);
            Point2D.Double p2 = myPolygon.get(j + 1);
    
            sum1 += p1.getX() * p2.getY();
            sum2 += p1.getY() * p2.getX();
        }
    
        // Include the last-to-first point connection
        Point2D.Double first = myPolygon.get(0);
        Point2D.Double last = myPolygon.get(n - 1);
        sum1 += last.getX() * first.getY();
        sum2 += last.getY() * first.getX();
    
        return Math.abs(sum1 - sum2) / 2.0;
    }
    
    


    public void draw() {
        // Wrap the DrawingTool in a try/catch to allow development without need for
        // graphics.
        if(myPolygon.size() < 2){
            return;
        }
        try {
            // TODO: Draw the polygon.
            // Documents: https://pavao.org/compsci/gpdraw/html/gpdraw/DrawingTool.html
            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            for(int i = 0; i < myPolygon.size(); i ++){
                pen.move(myPolygon.get(i).getX(), myPolygon.get(i).getY());
            }
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}