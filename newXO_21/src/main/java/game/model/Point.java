package game.model;

import java.util.Random;

public class Point {
    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Point getRandomPoint(int size) {
        Random random = new Random();
        return new Point(random.nextInt(size - 1), random.nextInt(size - 1));
    }
}
