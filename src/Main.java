import objects.GroupOfObjects;
import objects.Line;
import objects.Point;
import objects.Polygon;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Polygon polygon = new Polygon(3, new double[]{0, 0, 3}, new double[]{0, 4, 0});
        Polygon polygon1 = new Polygon(4, new double[]{1, 1, 2, 2}, new double[]{1, 2, 2, 1});
        Polygon polygon2 = new Polygon(4, new double[]{2, 2, 3, 3}, new double[]{0, 1, 1, 0});
        Polygon polygon3 = new Polygon(4, new double[]{2, 2, 4, 4}, new double[]{-1, 2, 2, -1});
        System.out.println(polygon);
        System.out.println("area " + polygon.area());

        Point point = new Point(0, 1);

        Line line = new Line(new Point(-1, 1), new Point(3, 1));

        Line line1 = new Line(new Point(-1, 0), new Point(2, 3));
        Point point1 = new Point(1, 2);

        Point point2 = new Point(2, 2);
        List<Point> points = new ArrayList<>();
        points.add(point);
        points.add(point1);
        points.add(point2);

        Polygon polygon4 = new Polygon(points.size(), points);

        GroupOfObjects groupOfObjects = new GroupOfObjects(new ArrayList<>() {{
//            add(point);
//            add(line);
            add(polygon);
//            add(polygon1);
//            add(polygon4);
//            add(polygon2);
            add(polygon3);
        }});

        Application application = new Application();
        application.setObjects(groupOfObjects);

        System.out.println(groupOfObjects.testIntersection());

        System.out.println("intersect of lines " + line.intersection(line1));
        List<Point> res = line.computePointsOfIntersection(line1);
        System.out.println("point of intersect of lines " + res);

        System.out.println(groupOfObjects);

        System.out.println("objects intersect " + groupOfObjects.testIntersection());

        List<Point> pointsOfIntersection = groupOfObjects.computePointsOfIntersection();
        System.out.println("Points of intersection of objects: " + pointsOfIntersection);

        System.out.println("new polygon " + new Polygon(pointsOfIntersection.size(), pointsOfIntersection));

        System.out.println("Area of intersection " + groupOfObjects.computeIntersection());
        Polygon result = groupOfObjects.computeIntersection();
        groupOfObjects.clear();
        groupOfObjects.add(result);
        application.setObjects(groupOfObjects);
    }
}
