package pl.stqa.pft.sandbox;

public class Point {

double x;
double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(Point p){
        return Math.sqrt(Math.pow((this.x-p.x),2)+Math.pow((this.y-p.y),2));
    }



    public static void main(String[] args) {
    Point p1 = new Point(0,1);
    Point p2 = new Point(2,-2);
        System.out.println(p1.distance(p2));
    }

}
