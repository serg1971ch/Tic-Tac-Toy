package game.model;

import game.controller.GameController;

public class User {
    private volatile boolean isShoot;
    public Point point;
    private GameController controller;

    public void setPoint(Point point) {
        this.point = point;
    }

    public void existPoint(GameController controller) {
        this.controller = controller;
    }

    public Point getShootPoint() {
        isShoot = false;
        synchronized (controller.key) {
            while (isShoot == false) {
                try {
                    controller.key.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return point;
    }

    public void setIsShoot(boolean isShoot) {
        this.isShoot = isShoot;
    }
}
