import objects.GroupOfObjects;
import objects.Line;
import objects.Point;
import objects.Polygon;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Application extends JFrame {
    private final int WIDTH = 1000;
    private final int HEIGHT = 650;
    GroupOfObjects objects = new GroupOfObjects();

    public Application() {
        setTitle("Test");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setObjects(GroupOfObjects objects) {
        this.objects = objects;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawGrid(g2d);
        drawObject(g2d);
    }

    public void drawObject(Graphics2D g2d) {
        Point center = new Point(Math.floorDiv(WIDTH, 2), Math.floorDiv(HEIGHT, 2));
        Random r = new Random();
        int lineWidth = 3;
        int i = 0;
        while (i < objects.size()) {
            g2d.setPaint(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(60, 200)));
            if (objects.get(i) instanceof Point) {
                g2d.fillOval((int) (center.getX() - Math.floor(((Point) objects.get(i)).getX()) * 20) - lineWidth / 2, (int) (center.getY() - Math.floor(((Point) objects.get(i)).getY()) * 20) - lineWidth / 2, lineWidth, lineWidth);
            }
            if (objects.get(i) instanceof Line) {
                g2d.setStroke(new BasicStroke(lineWidth));
                g2d.drawLine((int) (center.getX() + ((Line) objects.get(i)).getX1() * 20),
                        (int) (center.getY() - ((Line) objects.get(i)).getY1() * 20),
                        (int) (center.getX() + ((Line) objects.get(i)).getX2() * 20),
                        (int) (center.getY() - ((Line) objects.get(i)).getY2() * 20));
            }
            if (objects.get(i) instanceof Polygon) {
                g2d.setStroke(new BasicStroke(lineWidth));
                double[] xPoints = ((Polygon) objects.get(i)).getxPoints();
                double[] yPoints = ((Polygon) objects.get(i)).getyPoints();

                int[] xPs = new int[xPoints.length];
                int[] yPs = new int[yPoints.length];
                for (int j = 0; j < xPoints.length; j++) {
                    xPs[j] = (int) (center.getX() + xPoints[j] * 20);
                }
                for (int j = 0; j < yPoints.length; j++) {
                    yPs[j] = (int) (center.getY() - yPoints[j] * 20);
                }
                g2d.drawPolygon(xPs, yPs, ((Polygon) objects.get(i)).getnPoints());
            }
            lineWidth++;
            i++;
        }
    }

    private void drawGrid(Graphics2D g2d) {
        Point center = new Point(Math.floorDiv(WIDTH, 2), Math.floorDiv(HEIGHT, 2));
        g2d.setPaint(new Color(163, 163, 163));

        for (int i = (int) center.getX(); i >= 0; i -= 20) {
            g2d.drawLine(i, 0, i, HEIGHT);
        }
        for (int i = (int) center.getX(); i < WIDTH; i += 20) {
            g2d.drawLine(i, 0, i, HEIGHT);
        }
        for (int i = (int) center.getY(); i >= 0; i -= 20) {
            g2d.drawLine(0, i, WIDTH, i);
        }
        for (int i = (int) center.getY(); i < HEIGHT; i += 20) {
            g2d.drawLine(0, i, WIDTH, i);
        }

        g2d.setPaint(Color.BLACK);
        g2d.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
        g2d.drawLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2);

        g2d.drawString("1", (int) center.getX() + 20, (int) center.getY() + 12);
        g2d.drawString("1", (int) center.getX(), (int) center.getY() + 30);
    }
}
