package game.model;

import game.controller.GameController;

public class Field {
    GameController gameController;
    public static final int SIZE = 3;
    private Point point;

    public Field(GameController controller) {
        this.gameController = controller;
    }

    public Point getPoint() {
        return point;
    }

    public Type getType(int i, int j) {
        return cells[i][j];
    }

    public enum Type {
        X, O, NONE;
    }

    Type[][] cells = new Type[SIZE][SIZE];

    public Type[][] getCells() {
        return cells;
    }

    public Type getType(Point point) {
        return cells[point.getX()][point.getY()];
    }

    public static int getSize() {
        return SIZE;
    }

    public void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = Type.NONE;
            }
        }
    }

    public void shoot(Point point, Type who) {
        if ((point.getX() <= SIZE) && (point.getX() >= 0) && (point.getY() <= SIZE) && (point.getY() >= 0))
            if (cells[point.getX()][point.getY()].equals(Type.NONE)) {
                cells[point.getX()][point.getY()] = who;
                this.point = point;
                gameController.showController(cells, point, who);
            }
    }
    public Type whoIsWinner() {
        if (checkWin(Type.X)) {
            return Type.X;
        }
        if (checkWin(Type.O)) {
            return Type.O;
        }
        return Type.NONE;
    }

    private boolean checkWin(Type t) {
//        по горизонтали
        if (cells[0][0] == t && cells[1][0] == t && cells[2][0] == t) {
            GameController.SHOOT_COUNT = 0;
            return true;
        }
        if (cells[0][1] == t && cells[1][1] == t && cells[2][1] == t) {
            GameController.SHOOT_COUNT = 0;
            return true;
        }
        if (cells[0][2] == t && cells[1][2] == t && cells[2][2] == t) {
            GameController.SHOOT_COUNT = 0;
            return true;
        }
//               по вертикали
        if (cells[0][0] == t && cells[0][1] == t && cells[0][2] == t) {
            GameController.SHOOT_COUNT = 0;
            return true;
        }
        if (cells[1][0] == t && cells[1][1] == t && cells[1][2] == t) {
            GameController.SHOOT_COUNT = 0;
            return true;
        }
        if (cells[2][0] == t && cells[2][1] == t && cells[2][2] == t) {
            GameController.SHOOT_COUNT = 0;
            return true;
        }
//        по диоганали
        if (cells[0][0] == t && cells[1][1] == t && cells[2][2] == t) {
            GameController.SHOOT_COUNT = 0;
            return true;
        }
        if (cells[2][0] == t && cells[1][1] == t && cells[0][2] == t) {
            GameController.SHOOT_COUNT = 0;
            return true;
        }
        return false;
    }

    boolean checkDraw() {
        if (cells[0][0] != Type.NONE && cells[1][0] != Type.NONE && cells[2][0] != Type.NONE && cells[0][1] != Type.NONE && cells[1][1] != Type.NONE && cells[2][1] != Type.NONE && cells[0][2] != Type.NONE && cells[1][2] != Type.NONE && cells[2][2] != Type.NONE) {
            GameController.SHOOT_COUNT = 0;
            System.out.println("Ничья");
            return true;
        }
        return false;
    }
}