package game.model;

import game.controller.GameController;

public class Computer {
    Point computerPoint;
    private Game game;
    private Field.Type type;
    private static Field.Type[][] fieldCells;
    private Field field;
    private GameController controller;

    public Computer(Game game) {
        this.game = game;
        type = Field.Type.O;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void setPoint() {
        int size = Field.getSize();
        if (GameController.SHOOT_COUNT < 3 && size % 2 == 1) {
            Point p = new Point(size / 2, size / 2);
            if (field.getType(p) == Field.Type.NONE) {
                computerPoint = p;
                return;
            }
        }

        Point bad = new Point(-1, -1);

        for (int i = 0; i < size; i++) {
            int x = 0, y = 0, n = 0;
            int n_j = 0;
            Field.Type xt = field.getType(i, 0);
            if (xt == Field.Type.NONE)
                continue;
            for (int j = 0; j < size; j++)
                if (field.getType(i, j) == Field.Type.X)
                    x++;
                else if (field.getType(i, j) == Field.Type.O)
                    y++;
                else {
                    n++;
                    n_j = j;
                }
            if (x == size - 1 && n == 1 && type == Field.Type.X) {
                computerPoint = new Point(i, n_j);
                return;
            } else if (x == size - 1 && n == 1 && type == Field.Type.O) {
                bad = new Point(i, n_j);
            } else if (y == size - 1 && n == 1 && type == Field.Type.O) {
                computerPoint = new Point(i, n_j);
                return;
            } else if (y == size - 1 && n == 1 && type == Field.Type.X) {
                bad = new Point(i, n_j);
            }
        }

        for (int j = 0; j < size; j++) {
            int x = 0, y = 0, n = 0;
            int n_i = 0;
            Field.Type yt = field.getType(0, j);
            if (yt == Field.Type.NONE)
                continue;
            for (int i = 0; i < size; i++)
                if (field.getType(i, j) == Field.Type.X)
                    x++;
                else if (field.getType(i, j) == Field.Type.O)
                    y++;
                else {
                    n++;
                    n_i = i;
                }
            if (x == size - 1 && n == 1 && type == Field.Type.X) {
                computerPoint = new Point(n_i, j);
                return;
            } else if (x == size - 1 && n == 1 && type == Field.Type.O) {
                if (bad.getX() != -1) {
                    computerPoint = bad;
                    return;
                }
                bad = new Point(n_i, j);
            } else if (y == size - 1 && n == 1 && type == Field.Type.O) {
                computerPoint = new Point(n_i, j);
                return;
            } else if (y == size - 1 && n == 1 && type == Field.Type.X) {
                if (bad.getX() != -1) {
                    computerPoint = bad;
                    return;
                }
                bad = new Point(n_i, j);
                return;
            }
        }

        int x = 0, y = 0, n = 0;
        int n_i = 0;
        Field.Type yt = field.getType(0, 0);
        if (yt != Field.Type.NONE) {
            for (int i = 0; i < size; i++) {
                if (field.getType(i, i) == Field.Type.X)
                    x++;
                else if (field.getType(i, i) == Field.Type.O)
                    y++;
                else {
                    n++;
                    n_i = i;
                }
            }
            if (x == size - 1 && n == 1 && type == Field.Type.X) {
                computerPoint = new Point(n_i, n_i);
                return;
            } else if (x == size - 1 && n == 1 && type == Field.Type.O) {
                if (bad.getX() != -1) {
                    computerPoint = bad;
                    return;
                }
                bad = new Point(n_i, n_i);
            } else if (y == size - 1 && n == 1 && type == Field.Type.O) {
                computerPoint = new Point(n_i, n_i);
                return;
            } else if (y == size - 1 && n == 1 && type == Field.Type.X) {
                if (bad.getX() != -1) {
                    computerPoint = bad;
                    return;
                }
                bad = new Point(n_i, n_i);
            }
        }

        x = 0;
        y = 0;
        n = 0;
        n_i = 0;
        yt = field.getType(0, size - 1);
        if (yt != Field.Type.NONE) {
            for (int i = size - 1; i > -1; i--) {
                if (field.getType(size - 1 - i, i) == Field.Type.X)
                    x++;
                else if (field.getType(size - 1 - i, i) == Field.Type.O)
                    y++;
                else {
                    n++;
                    n_i = i;
                }
            }
            if (x == size - 1 && n == 1 && type == Field.Type.X) {
                computerPoint = new Point(size - 1 - n_i, n_i);
                return;
            } else if (x == size - 1 && n == 1 && type == Field.Type.O) {
                if (bad.getX() != -1) {
                    computerPoint = bad;
                    return;
                }
                bad = new Point(size - 1 - n_i, n_i);
            } else if (y == size - 1 && n == 1 && type == Field.Type.O) {
                computerPoint = new Point(size - 1 - n_i, n_i);
                return;
            } else if (y == size - 1 && n == 1 && type == Field.Type.X) {
                if (bad.getX() != -1) {
                    computerPoint = bad;
                    return;
                }
                bad = new Point(size - 1 - n_i, n_i);
            }
        }

        if (bad.getX() != -1) {
            computerPoint = bad;
        } else if (field.getType(0, 0) == Field.Type.NONE) {
            computerPoint = new Point(0, 0);
        } else if (field.getType(0, size - 1) == Field.Type.NONE) {
            computerPoint = new Point(0, size - 1);
        } else if (field.getType(size - 1, 0) == Field.Type.NONE) {
            computerPoint = new Point(size - 1, 0);
        } else if (field.getType(size - 1, size - 1) == Field.Type.NONE) {
            computerPoint = new Point(size - 1, size - 1);
        } else {
            computerPoint = Point.getRandomPoint(size);
            while (field.getType(computerPoint) != Field.Type.NONE)
                computerPoint = Point.getRandomPoint(size);
        }
    }

//    public void setCleverMove(CleverMove cleverMove) {
//        this.cleverMove = cleverMove;
//    }

    public Point getComputerPoint() {
        return computerPoint;
    }

    public Game getGame() {
        return game;
    }
}
